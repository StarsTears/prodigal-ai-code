<template>
  <a-layout-header class="global-header">
    <div class="header-container">
      <!-- 左侧：Logo和标题 -->
      <div class="header-left">
        <div class="logo-container">
          <img src="/logo.svg" alt="Logo" class="logo" />
          <h1 class="site-title">Prodigal AI Code</h1>
        </div>
      </div>

      <!-- 中间：导航菜单 -->
      <div class="header-center">
        <a-menu
          v-model:selectedKeys="selectedKeys"
          mode="horizontal"
          :items="menuItems"
          class="header-menu"
          @click="handleMenuClick"
        />
      </div>

      <!-- 右侧：用户信息 -->
      <div class="header-right">
        <!-- 未登录状态 -->
        <a-button 
          v-if="!userStore.isLoggedIn" 
          type="primary" 
          @click="handleLogin"
        >
          <template #icon>
            <UserOutlined />
          </template>
          登录
        </a-button>
        
        <!-- 已登录状态 -->
        <div v-else class="user-info">
          <a-dropdown>
            <div class="user-avatar">
              <a-avatar :src="userStore.avatar" :size="32">
                <template #icon><UserOutlined /></template>
              </a-avatar>
              <span class="user-nickname">{{ userStore.nickname }}</span>
            </div>
            <template #overlay>
              <a-menu>
                <a-menu-item key="profile">
                  <template #icon><UserOutlined /></template>
                  个人资料
                </a-menu-item>
                <a-menu-item key="settings">
                  <template #icon><SettingOutlined /></template>
                  设置
                </a-menu-item>
                <a-menu-divider />
                <a-menu-item key="logout" @click="handleLogout">
                  <template #icon><LogoutOutlined /></template>
                  退出登录
                </a-menu-item>
              </a-menu>
            </template>
          </a-dropdown>
        </div>
      </div>
    </div>
  </a-layout-header>
</template>

<script setup lang="ts">
import { ref, reactive, h } from 'vue'
import { useRouter } from 'vue-router'
import { Layout, Menu, Button, Avatar, Dropdown } from 'ant-design-vue'
import { UserOutlined, LogoutOutlined, SettingOutlined } from '@ant-design/icons-vue'
import type { MenuProps } from 'ant-design-vue'
import { useUserStore } from '@/stores/user'

const { Header } = Layout

const router = useRouter()
const userStore = useUserStore()
const selectedKeys = ref<string[]>(['home'])

// 菜单配置
const menuItems = reactive<MenuProps['items']>([
  {
    key: 'home',
    label: '首页',
    icon: () => h('span', '🏠')
  },
  {
    key: 'about',
    label: '关于我们',
    icon: () => h('span', 'ℹ️')
  },
  {
    key: 'projects',
    label: '项目展示',
    icon: () => h('span', '📁')
  },
  {
    key: 'contact',
    label: '联系我们',
    icon: () => h('span', '📞')
  }
])

// 菜单点击处理
const handleMenuClick: MenuProps['onClick'] = ({ key }) => {
  selectedKeys.value = [key]
  switch (key) {
    case 'home':
      router.push('/')
      break
    case 'about':
      router.push('/about')
      break
    case 'projects':
      router.push('/projects')
      break
    case 'contact':
      router.push('/contact')
      break
  }
}

// 登录处理
const handleLogin = () => {
  router.push('/login')
}

// 登出处理
const handleLogout = () => {
  userStore.logout()
  router.push('/')
}
</script>

<style scoped>
.global-header {
  background: #fff;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 1000;
  height: 64px;
  line-height: 64px;
}

.header-container {
  display: flex;
  align-items: center;
  justify-content: space-between;
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 24px;
  height: 100%;
}

.header-left {
  display: flex;
  align-items: center;
}

.logo-container {
  display: flex;
  align-items: center;
  gap: 12px;
}

.logo {
  width: 32px;
  height: 32px;
  object-fit: contain;
}

.site-title {
  margin: 0;
  font-size: 18px;
  font-weight: 600;
  color: #1890ff;
}

.header-center {
  flex: 1;
  display: flex;
  justify-content: center;
}

.header-menu {
  border: none;
  background: transparent;
  line-height: 64px;
}

.header-menu :deep(.ant-menu-item) {
  height: 64px;
  line-height: 64px;
  padding: 0 20px;
}

.header-right {
  display: flex;
  align-items: center;
}

.user-info {
  display: flex;
  align-items: center;
}

.user-avatar {
  display: flex;
  align-items: center;
  gap: 8px;
  cursor: pointer;
  padding: 4px 8px;
  border-radius: 6px;
  transition: background-color 0.3s;
}

.user-avatar:hover {
  background-color: #f5f5f5;
}

.user-nickname {
  font-size: 14px;
  color: #333;
  max-width: 100px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .header-container {
    padding: 0 16px;
  }

  .site-title {
    font-size: 16px;
  }

  .header-menu {
    display: none;
  }

  .header-center {
    display: none;
  }
}

@media (max-width: 480px) {
  .logo-container {
    gap: 8px;
  }

  .logo {
    width: 28px;
    height: 28px;
  }

  .site-title {
    font-size: 14px;
  }
}
</style>
