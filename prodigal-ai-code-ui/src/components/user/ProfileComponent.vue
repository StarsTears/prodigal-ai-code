<template>
  <div class="profile-container">
    <!-- 触发按钮 -->
<!--    <a-button type="primary" @click="showProfileModal">-->
<!--      <template #icon><UserOutlined /></template>-->
<!--      查看个人资料-->
<!--    </a-button>-->

    <!-- 个人资料模态框 -->
    <a-modal
      v-model:open="profileModalVisible"
      title="个人资料"
      width="800px"
      :footer="null"
      :destroyOnClose="true"
    >
      <a-card :bordered="false" class="profile-card">
        <a-row :gutter="24">
          <!-- 左侧：头像和基本信息 -->
          <a-col :span="8">
            <div class="profile-avatar-section">
              <a-avatar
                :src="userStore.avatar"
                :size="120"
                class="profile-avatar"
              >
                <template #icon><UserOutlined /></template>
              </a-avatar>
              <h2 class="user-name">{{ userStore.nickname }}</h2>
              <a-tag :color="getRoleColor(userStore.userInfo?.userRole)">
                {{ getRoleText(userStore.userInfo?.userRole) }}
              </a-tag>
            </div>
          </a-col>

          <!-- 右侧：详细信息 -->
          <a-col :span="16">
            <a-descriptions
              title="基本信息"
              :column="1"
              bordered
              size="middle"
            >
              <a-descriptions-item label="用户ID">
                {{ userStore.userInfo?.id || '暂无' }}
              </a-descriptions-item>
              <a-descriptions-item label="用户名">
                {{ userStore.userInfo?.userAccount || '暂无' }}
              </a-descriptions-item>
              <a-descriptions-item label="昵称">
                {{ userStore.userInfo?.userName || '暂无' }}
              </a-descriptions-item>
              <a-descriptions-item label="邮箱">
                {{ userStore.userInfo?.userEmail || '暂无' }}
              </a-descriptions-item>
              <a-descriptions-item label="个人简介">
                {{ userStore.userInfo?.userProfile || '暂无个人简介' }}
              </a-descriptions-item>
              <a-descriptions-item label="注册时间">
                {{ formatDate(userStore.userInfo?.createTime) }}
              </a-descriptions-item>
              <a-descriptions-item label="最后更新">
                {{ formatDate(userStore.userInfo?.updateTime) }}
              </a-descriptions-item>
            </a-descriptions>

            <!-- 操作按钮 -->
            <div class="profile-actions">
              <a-space>
                <a-button type="primary" @click="handleEdit">
                  <template #icon><EditOutlined /></template>
                  编辑资料
                </a-button>
                <a-button @click="handleRefresh">
                  <template #icon><ReloadOutlined /></template>
                  刷新信息
                </a-button>
              </a-space>
            </div>
          </a-col>
        </a-row>
      </a-card>
    </a-modal>

    <!-- 编辑资料对话框 -->
    <a-modal
      v-model:open="editModalVisible"
      title="编辑个人资料"
      @ok="handleSave"
      @cancel="handleCancel"
      :confirmLoading="saveLoading"
    >
      <a-form
        ref="formRef"
        :model="formData"
        :rules="formRules"
        layout="vertical"
      >
        <a-form-item label="昵称" name="userName">
          <a-input
            v-model:value="formData.userName"
            placeholder="请输入昵称"
            maxlength="20"
            show-count
          />
        </a-form-item>
        <a-form-item label="邮箱" name="userEmail">
          <a-input
            v-model:value="formData.userEmail"
            placeholder="请输入邮箱"
            type="email"
          />
        </a-form-item>
        <a-form-item label="个人简介" name="userProfile">
          <a-textarea
            v-model:value="formData.userProfile"
            placeholder="请输入个人简介"
            :rows="4"
            maxlength="200"
            show-count
          />
        </a-form-item>
        <a-form-item label="头像" name="userAvatar">
          <a-input
            v-model:value="formData.userAvatar"
            placeholder="请输入头像URL"
          />
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import {
  Button,
  Modal,
  Card,
  Row,
  Col,
  Avatar,
  Tag,
  Descriptions,
  Space,
  Form,
  Input,
  message,
  Textarea
} from 'ant-design-vue'
import {
  UserOutlined,
  EditOutlined,
  ReloadOutlined
} from '@ant-design/icons-vue'
import { useUserStore } from '@/stores/user.ts'
import { update } from '@/api/userController.ts'
import type { FormInstance } from 'ant-design-vue'

const userStore = useUserStore()

// 模态框相关
const profileModalVisible = ref(false)
const editModalVisible = ref(false)
const saveLoading = ref(false)
const formRef = ref<FormInstance>()

// 表单数据
const formData = reactive({
  id: 0,
  userName: '',
  userEmail: '',
  userProfile: '',
  userAvatar: ''
})

// 表单验证规则
const formRules = {
  userName: [
    { required: true, message: '请输入昵称', trigger: 'blur' },
    { min: 2, max: 20, message: '昵称长度在 2 到 20 个字符', trigger: 'blur' }
  ],
  userEmail: [
    { required: true, message: '请输入邮箱', trigger: 'blur' },
    { type: 'email', message: '请输入正确的邮箱格式', trigger: 'blur' }
  ],
  userProfile: [
    { max: 200, message: '个人简介不能超过 200 个字符', trigger: 'blur' }
  ]
}

// 显示个人资料模态框
const showProfileModal = async () => {
  if (!userStore.isLoggedIn) {
    message.error('请先登录')
    return
  }

  // 刷新用户信息
  await userStore.fetchLoginUser()
  profileModalVisible.value = true
}

// 暴露方法给父组件
defineExpose({
  showProfileModal
})

// 获取角色颜色
const getRoleColor = (role?: string) => {
  switch (role) {
    case 'admin':
      return 'red'
    case 'user':
      return 'blue'
    default:
      return 'default'
  }
}

// 获取角色文本
const getRoleText = (role?: string) => {
  switch (role) {
    case 'admin':
      return '管理员'
    case 'user':
      return '普通用户'
    default:
      return '未知'
  }
}

// 格式化日期
const formatDate = (dateStr?: string) => {
  if (!dateStr) return '暂无'
  try {
    return new Date(dateStr).toLocaleString('zh-CN')
  } catch (error) {
    return dateStr
  }
}

// 编辑资料
const handleEdit = () => {
  if (!userStore.userInfo) {
    message.error('用户信息不存在')
    return
  }

  // 填充表单数据
  formData.id = userStore.userInfo.id || 0
  formData.userName = userStore.userInfo.userName || ''
  formData.userEmail = userStore.userInfo.userEmail || ''
  formData.userProfile = userStore.userInfo.userProfile || ''
  formData.userAvatar = userStore.userInfo.userAvatar || ''

  editModalVisible.value = true
}

// 保存资料
const handleSave = async () => {
  try {
    await formRef.value?.validate()
    saveLoading.value = true

    const res = await update({
      id: formData.id,
      userName: formData.userName,
      userEmail: formData.userEmail,
      userProfile: formData.userProfile,
      userAvatar: formData.userAvatar
    })

    if (res.data.code === 0) {
      message.success('保存成功！')
      editModalVisible.value = false
      // 刷新用户信息
      await userStore.fetchLoginUser()
    } else {
      message.error('保存失败：' + (res.data.msg || '请重试'))
    }
  } catch (error) {
    console.error('保存资料错误:', error)
    message.error('保存失败，请重试')
  } finally {
    saveLoading.value = false
  }
}

// 取消编辑
const handleCancel = () => {
  editModalVisible.value = false
  formRef.value?.resetFields()
}

// 刷新信息
const handleRefresh = async () => {
  try {
    const success = await userStore.fetchLoginUser()
    if (success) {
      message.success('信息已刷新')
    } else {
      message.error('刷新失败，请重新登录')
    }
  } catch (error) {
    console.error('刷新用户信息错误:', error)
    message.error('刷新失败，请重试')
  }
}

// 页面加载时检查登录状态
onMounted(() => {
  // 组件加载时不需要自动获取用户信息，等用户点击按钮时再获取
})
</script>

<style scoped>
.profile-container {
  display: inline-block;
}

.profile-card {
  box-shadow: none;
  border-radius: 8px;
}

.profile-avatar-section {
  text-align: center;
  padding: 20px 0;
}

.profile-avatar {
  margin-bottom: 16px;
  border: 4px solid #f0f0f0;
}

.user-name {
  margin: 16px 0 8px;
  font-size: 24px;
  font-weight: 600;
  color: #333;
}

.profile-actions {
  margin-top: 24px;
  text-align: center;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .profile-avatar {
    width: 80px !important;
    height: 80px !important;
  }

  .user-name {
    font-size: 20px;
  }
}
</style>
