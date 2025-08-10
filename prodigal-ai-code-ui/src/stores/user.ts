import { defineStore } from 'pinia'
import { ref, computed } from 'vue'

export interface UserInfo {
  id: string
  username: string
  email: string
  avatar?: string
  nickname?: string
}

export const useUserStore = defineStore('user', () => {
  // 用户信息
  const userInfo = ref<UserInfo | null>(null)
  
  // 登录状态
  const isLoggedIn = computed(() => !!userInfo.value)
  
  // 用户昵称
  const nickname = computed(() => userInfo.value?.nickname || userInfo.value?.username || '用户')
  
  // 用户头像
  const avatar = computed(() => userInfo.value?.avatar || '/logo.svg')
  
  // 登录
  const login = (user: UserInfo) => {
    userInfo.value = user
    // 可以在这里保存到localStorage
    localStorage.setItem('userInfo', JSON.stringify(user))
  }
  
  // 登出
  const logout = () => {
    userInfo.value = null
    localStorage.removeItem('userInfo')
  }
  
  // 初始化用户信息（从localStorage恢复）
  const initUserInfo = () => {
    const savedUserInfo = localStorage.getItem('userInfo')
    if (savedUserInfo) {
      try {
        userInfo.value = JSON.parse(savedUserInfo)
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
    login,
    logout,
    initUserInfo
  }
}) 