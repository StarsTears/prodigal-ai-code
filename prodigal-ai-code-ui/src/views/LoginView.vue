<script setup lang="ts">
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
import { Card, Form, Input, Button, Typography, Space, Divider, message } from 'ant-design-vue'
import { UserOutlined, LockOutlined, MailOutlined } from '@ant-design/icons-vue'
import { useUserStore } from '@/stores/user'
import {getLoginUser, login} from "@/api/userController.ts";

const { Title, Paragraph } = Typography
const router = useRouter()
const userStore = useUserStore()

// 表单数据
const formData = reactive<API.UserLoginDto>({
  userAccount: '',
  userPassword: ''
})

// 表单验证规则
const rules = {
  userAccount: [
    { required: true, message: '请输入用户名或邮箱' }
  ],
  userPassword: [
    { required: true, message: '请输入密码' },
    { min: 6, message: '密码长度不能少于6位' }
  ]
}

// 登录处理
const handleLogin = async (values: any) => {
  console.log('登录信息:', values)
  try {
    const res = await login(values)
    if (res.data.code === 0 && res.data.data) {
      // 登录成功后，获取并更新用户信息
      const success = await userStore.fetchLoginUser()
      if (success) {
        message.success('登录成功！')
        router.push('/')
      } else {
        message.error('登录失败，请重试')
      }
    } else {
      message.error('登录失败：' + (res.data.msg || '请检查用户名和密码'))
    }
  } catch (error) {
    console.error('登录错误:', error)
    message.error('登录失败，请重试')
  }
}

// 跳转到注册页
const goToRegister = () => {
  router.push('/register')
}
</script>

<template>
  <div class="login-container">
    <div class="login-card">
      <Card :body-style="{ padding: '40px' }">
        <div class="login-header">
          <img src="/logo.svg" alt="Logo" class="login-logo" />
          <Title level="2" class="login-title">欢迎回来</Title>
          <Paragraph class="login-subtitle">
            请登录您的账户以继续使用我们的服务
          </Paragraph>
        </div>

        <Form
          :model="formData"
          :rules="rules"
          @finish="handleLogin"
          layout="vertical"
          size="large"
        >
          <Form.Item
            label="用户名或邮箱"
            name="userAccount"
          >
<!--            <Input-->
<!--              v-model:value="formData.userAccount"-->
<!--              placeholder="请输入用户名或邮箱"-->
<!--              :prefix="UserOutlined"-->
<!--            />-->
            <Input
              v-model:value="formData.userAccount"
              placeholder="请输入用户名或邮箱"
            >
              <template #prefix>
                <UserOutlined />
              </template>
            </Input>
          </Form.Item>

          <Form.Item
            label="密码"
            name="userPassword"
          >
<!--            <Input.Password-->
<!--              v-model:value="formData.userPassword"-->
<!--              placeholder="请输入密码"-->
<!--              :prefix="LockOutlined"-->
<!--            />-->
            <Input.Password
              v-model:value="formData.userPassword"
              placeholder="请输入密码"
            >
              <template #prefix>
                <LockOutlined />
              </template>
            </Input.Password>
          </Form.Item>

          <Form.Item>
            <Button
              type="primary"
              html-type="submit"
              size="large"
              block
              class="login-button"
            >
              登录
            </Button>
          </Form.Item>
        </Form>

        <Divider>或者</Divider>

        <div class="login-footer">
          <Paragraph style="text-align: center; margin-bottom: 16px">
            还没有账户？
            <a @click="goToRegister" class="register-link">立即注册</a>
          </Paragraph>

          <Space direction="vertical" size="small" style="width: 100%">
            <Button block size="large" class="social-login-btn">
              <template #icon>
                <MailOutlined />
              </template>
              使用邮箱登录
            </Button>
          </Space>
        </div>
      </Card>
    </div>
  </div>
</template>

<style scoped>
.login-container {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  padding: 20px;
}

.login-card {
  width: 100%;
  max-width: 400px;
}

.login-header {
  text-align: center;
  margin-bottom: 32px;
}

.login-logo {
  width: 64px;
  height: 64px;
  margin-bottom: 16px;
}

.login-title {
  margin: 0 0 8px 0 !important;
  color: #1890ff;
}

.login-subtitle {
  color: #666;
  margin: 0;
}

.login-button {
  height: 48px;
  font-size: 16px;
  font-weight: 500;
}

.social-login-btn {
  height: 48px;
  border: 1px solid #d9d9d9;
  color: #666;
}

.register-link {
  color: #1890ff;
  cursor: pointer;
  text-decoration: none;
}

.register-link:hover {
  text-decoration: underline;
}

/* 响应式设计 */
@media (max-width: 480px) {
  .login-container {
    padding: 16px;
  }

  .login-card {
    max-width: 100%;
  }

  .login-header {
    margin-bottom: 24px;
  }

  .login-logo {
    width: 48px;
    height: 48px;
  }
}
</style>
