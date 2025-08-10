<script setup lang="ts">
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
import { Card, Form, Input, Button, Typography, Space, Divider, message, Checkbox } from 'ant-design-vue'
import { UserOutlined, LockOutlined, MailOutlined, SafetyOutlined } from '@ant-design/icons-vue'
import { useUserStore } from '@/stores/user'

const { Title, Paragraph } = Typography
const router = useRouter()
const userStore = useUserStore()

// 表单数据
const formData = reactive({
  username: '',
  email: '',
  password: '',
  confirmPassword: '',
  agreeTerms: false
})

// 表单验证规则
const rules = {
  username: [
    { required: true, message: '请输入用户名' },
    { min: 3, message: '用户名长度不能少于3位' },
    { max: 20, message: '用户名长度不能超过20位' }
  ],
  email: [
    { required: true, message: '请输入邮箱地址' },
    { type: 'email', message: '请输入有效的邮箱地址' }
  ],
  password: [
    { required: true, message: '请输入密码' },
    { min: 6, message: '密码长度不能少于6位' },
    { 
      pattern: /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)/, 
      message: '密码必须包含大小写字母和数字' 
    }
  ],
  confirmPassword: [
    { required: true, message: '请确认密码' },
    {
      validator: (rule: any, value: string) => {
        if (value && value !== formData.password) {
          return Promise.reject('两次输入的密码不一致')
        }
        return Promise.resolve()
      }
    }
  ],
  agreeTerms: [
    {
      validator: (rule: any, value: boolean) => {
        if (!value) {
          return Promise.reject('请同意用户协议和隐私政策')
        }
        return Promise.resolve()
      }
    }
  ]
}

// 注册处理
const handleRegister = async (values: any) => {
  try {
    console.log('注册信息:', values)
    // 模拟注册成功并自动登录
    const userInfo = {
      id: Date.now().toString(),
      username: values.username,
      email: values.email,
      nickname: values.username,
      avatar: '/logo.svg'
    }
    
    userStore.login(userInfo)
    message.success('注册成功！已自动登录')
    router.push('/')
  } catch (error) {
    message.error('注册失败，请重试')
  }
}

// 跳转到登录页
const goToLogin = () => {
  router.push('/login')
}
</script>

<template>
  <div class="register-container">
    <div class="register-card">
      <Card :body-style="{ padding: '40px' }">
        <div class="register-header">
          <img src="/logo.svg" alt="Logo" class="register-logo" />
          <Title level="2" class="register-title">创建账户</Title>
          <Paragraph class="register-subtitle">
            请填写以下信息完成注册
          </Paragraph>
        </div>
        
        <Form 
          :model="formData"
          :rules="rules"
          @finish="handleRegister"
          layout="vertical"
          size="large"
        >
          <Form.Item 
            label="用户名" 
            name="username"
          >
            <Input 
              v-model:value="formData.username"
              placeholder="请输入用户名"
              :prefix="UserOutlined"
            />
          </Form.Item>
          
          <Form.Item 
            label="邮箱地址" 
            name="email"
          >
            <Input 
              v-model:value="formData.email"
              placeholder="请输入邮箱地址"
              :prefix="MailOutlined"
            />
          </Form.Item>
          
          <Form.Item 
            label="密码" 
            name="password"
          >
            <Input.Password 
              v-model:value="formData.password"
              placeholder="请输入密码"
              :prefix="LockOutlined"
            />
          </Form.Item>
          
          <Form.Item 
            label="确认密码" 
            name="confirmPassword"
          >
            <Input.Password 
              v-model:value="formData.confirmPassword"
              placeholder="请再次输入密码"
              :prefix="SafetyOutlined"
            />
          </Form.Item>
          
          <Form.Item 
            name="agreeTerms"
            :valuePropName="'checked'"
          >
            <Checkbox v-model:checked="formData.agreeTerms">
              我已阅读并同意
              <a href="#" target="_blank">用户协议</a>
              和
              <a href="#" target="_blank">隐私政策</a>
            </Checkbox>
          </Form.Item>
          
          <Form.Item>
            <Button 
              type="primary" 
              html-type="submit" 
              size="large" 
              block
              class="register-button"
            >
              注册
            </Button>
          </Form.Item>
        </Form>
        
        <Divider>或者</Divider>
        
        <div class="register-footer">
          <Paragraph style="text-align: center; margin-bottom: 16px">
            已有账户？
            <a @click="goToLogin" class="login-link">立即登录</a>
          </Paragraph>
          
          <Space direction="vertical" size="small" style="width: 100%">
            <Button block size="large" class="social-register-btn">
              <template #icon>
                <MailOutlined />
              </template>
              使用邮箱注册
            </Button>
          </Space>
        </div>
      </Card>
    </div>
  </div>
</template>

<style scoped>
.register-container {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  padding: 20px;
}

.register-card {
  width: 100%;
  max-width: 450px;
}

.register-header {
  text-align: center;
  margin-bottom: 32px;
}

.register-logo {
  width: 64px;
  height: 64px;
  margin-bottom: 16px;
}

.register-title {
  margin: 0 0 8px 0 !important;
  color: #1890ff;
}

.register-subtitle {
  color: #666;
  margin: 0;
}

.register-button {
  height: 48px;
  font-size: 16px;
  font-weight: 500;
}

.social-register-btn {
  height: 48px;
  border: 1px solid #d9d9d9;
  color: #666;
}

.login-link {
  color: #1890ff;
  cursor: pointer;
  text-decoration: none;
}

.login-link:hover {
  text-decoration: underline;
}

/* 响应式设计 */
@media (max-width: 480px) {
  .register-container {
    padding: 16px;
  }
  
  .register-card {
    max-width: 100%;
  }
  
  .register-header {
    margin-bottom: 24px;
  }
  
  .register-logo {
    width: 48px;
    height: 48px;
  }
}
</style> 