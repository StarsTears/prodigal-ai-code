import { defineStore } from 'pinia'
import { ref, computed } from 'vue'
import {getLoginUser} from "@/api/userController.ts";


export const useUserStore = defineStore('user', () => {
  // 用户信息
  const userInfo = ref<API.LoginUserVO | null>(null)

  // 登录状态 - 更严格的判断逻辑
  const isLoggedIn = computed(() => {
    return userInfo.value && 
           userInfo.value.userName && 
           userInfo.value.userName.trim() !== '' &&
           userInfo.value.id // 确保有用户ID
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
        const userData = res.data.data
        // 验证用户数据的完整性
        if (userData.userName && userData.userName.trim() !== '' && userData.id) {
          userInfo.value = userData
          // 保存到localStorage
          localStorage.setItem('userInfo', JSON.stringify(userData))
          return true
        } else {
          // 用户数据不完整，清除状态
          userInfo.value = null
          localStorage.removeItem('userInfo')
          return false
        }
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
    // 验证用户数据的完整性
    if (user.userName && user.userName.trim() !== '' && user.id) {
      userInfo.value = user
      // 保存到localStorage
      localStorage.setItem('userInfo', JSON.stringify(user))
    } else {
      console.error('用户数据不完整，无法登录')
      throw new Error('用户数据不完整')
    }
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
        if (parsedUserInfo && 
            parsedUserInfo.userName && 
            parsedUserInfo.userName.trim() !== '' &&
            parsedUserInfo.id) {
          userInfo.value = parsedUserInfo
          // 验证服务器端登录状态
          const isValid = await fetchLoginUser()
          if (!isValid) {
            // 如果服务器端验证失败，清除本地信息
            userInfo.value = null
            localStorage.removeItem('userInfo')
            console.log('服务器端验证失败，已清除本地用户信息')
          }
        } else {
          // 清除无效的用户信息
          userInfo.value = null
          localStorage.removeItem('userInfo')
          console.log('本地用户信息无效，已清除')
        }
      } catch (error) {
        console.error('Failed to parse user info:', error)
        userInfo.value = null
        localStorage.removeItem('userInfo')
      }
    } else {
      // 没有本地存储的用户信息，确保状态为空
      userInfo.value = null
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
