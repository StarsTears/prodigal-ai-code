import { defineStore } from 'pinia'
import { ref, computed } from 'vue'
import {getLoginUser} from "@/api/userController.ts";


export const useUserStore = defineStore('user', () => {
  // 用户信息
  const userInfo = ref<API.LoginUserVO | null>(null)

  // 登录状态 - 修复判断逻辑
  const isLoggedIn = computed(() => {
    return userInfo.value && userInfo.value.userName && userInfo.value.userName.trim() !== ''
  })

  // 用户昵称
  const nickname = computed(() => userInfo.value?.userName || '用户')

  // 用户头像
  const avatar = computed(() => userInfo.value?.userAvatar || '/logo.svg')

  // 获取登录用户信息
  async function fetchLoginUser() {
    try {
      const res = await getLoginUser()
      console.log('获取登录用户信息', res)
      if (res.data.code === 0 && res.data.data) {
        userInfo.value = res.data.data
        // 保存到localStorage
        localStorage.setItem('userInfo', JSON.stringify(res.data.data))
        return true
      } else {
        // 如果获取失败，清除本地存储的用户信息
        userInfo.value = null
        localStorage.removeItem('userInfo')
        return false
      }
    } catch (error) {
      console.error('获取用户信息失败:', error)
      userInfo.value = null
      localStorage.removeItem('userInfo')
      return false
    }
  }

  // 登录
  const login = (user: API.LoginUserVO) => {
    userInfo.value = user
    // 保存到localStorage
    localStorage.setItem('userInfo', JSON.stringify(user))
  }

  // 登出
  const logout = () => {
    userInfo.value = null
    localStorage.removeItem('userInfo')
  }

  // 初始化用户信息（从localStorage恢复）
  const initUserInfo = async () => {
    const savedUserInfo = localStorage.getItem('userInfo')
    if (savedUserInfo) {
      try {
        const parsedUserInfo = JSON.parse(savedUserInfo)
        // 验证用户信息是否有效
        if (parsedUserInfo && parsedUserInfo.userName && parsedUserInfo.userName.trim() !== '') {
          userInfo.value = parsedUserInfo
          // 验证服务器端登录状态
          const isValid = await fetchLoginUser()
          if (!isValid) {
            // 如果服务器端验证失败，清除本地信息
            userInfo.value = null
            localStorage.removeItem('userInfo')
          }
        } else {
          // 清除无效的用户信息
          localStorage.removeItem('userInfo')
        }
      } catch (error) {
        console.error('Failed to parse user info:', error)
        localStorage.removeItem('userInfo')
      }
    }
  }

  return {
    userInfo,
    isLoggedIn,
    nickname,
    avatar,
    fetchLoginUser,
    login,
    logout,
    initUserInfo
  }
})
