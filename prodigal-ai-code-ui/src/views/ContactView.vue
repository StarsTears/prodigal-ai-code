<script setup lang="ts">
import { Card, Row, Col, Typography, Form, Input, Button, Space, Divider } from 'ant-design-vue'
import { MailOutlined, PhoneOutlined, EnvironmentOutlined, GithubOutlined } from '@ant-design/icons-vue'

const { Title, Paragraph } = Typography
const { TextArea } = Input

// 联系信息
const contactInfo = [
  {
    icon: MailOutlined,
    title: '邮箱',
    content: 'prodigal.lang@qq.com',
    link: 'mailto:contact@prodigal.dev'
  },
  {
    icon: PhoneOutlined,
    title: '电话',
    content: '+86 138-0000-0000',
    link: 'tel:+8613800000000'
  },
  {
    icon: EnvironmentOutlined,
    title: '地址',
    content: '中国 · 深圳',
    link: null
  },
  {
    icon: GithubOutlined,
    title: 'GitHub',
    content: 'github.com/StarsTears',
    link: 'https://github.com/StarsTears/prodigal-ai-code'
  }
]

// 表单提交处理
const handleSubmit = (values: any) => {
  console.log('表单提交:', values)
  // 这里可以添加实际的表单提交逻辑
}
</script>

<template>
  <div class="contact-container">
    <Row :gutter="[24, 24]">
      <!-- 联系信息 -->
      <Col :span="24">
        <Card>
          <Title level="2">联系我们</Title>
          <Paragraph>
            如果您有任何问题或合作意向，欢迎通过以下方式与我们联系。
          </Paragraph>
        </Card>
      </Col>

      <Col :xs="24" :md="12">
        <Card title="联系信息" :body-style="{ padding: '24px' }">
          <Space direction="vertical" size="large" style="width: 100%">
            <div
              v-for="item in contactInfo"
              :key="item.title"
              class="contact-item"
            >
              <div class="contact-icon">
                <component :is="item.icon" style="font-size: 20px; color: #1890ff" />
              </div>
              <div class="contact-content">
                <Title level="5" style="margin: 0 0 4px 0">{{ item.title }}</Title>
                <Paragraph style="margin: 0">
                  <a
                    v-if="item.link"
                    :href="item.link"
                    target="_blank"
                    style="color: #1890ff; text-decoration: none"
                  >
                    {{ item.content }}
                  </a>
                  <span v-else>{{ item.content }}</span>
                </Paragraph>
              </div>
            </div>
          </Space>
        </Card>
      </Col>

      <!-- 联系表单 -->
      <Col :xs="24" :md="12">
        <Card title="发送消息" :body-style="{ padding: '24px' }">
          <Form
            layout="vertical"
            @finish="handleSubmit"
            :model="{}"
          >
            <Form.Item
              label="姓名"
              name="name"
              :rules="[{ required: true, message: '请输入您的姓名' }]"
            >
              <Input placeholder="请输入您的姓名" />
            </Form.Item>

            <Form.Item
              label="邮箱"
              name="email"
              :rules="[
                { required: true, message: '请输入您的邮箱' },
                { type: 'email', message: '请输入有效的邮箱地址' }
              ]"
            >
              <Input placeholder="请输入您的邮箱" />
            </Form.Item>

            <Form.Item
              label="主题"
              name="subject"
              :rules="[{ required: true, message: '请输入消息主题' }]"
            >
              <Input placeholder="请输入消息主题" />
            </Form.Item>

            <Form.Item
              label="消息内容"
              name="message"
              :rules="[{ required: true, message: '请输入消息内容' }]"
            >
              <TextArea
                placeholder="请输入您的消息内容"
                :rows="4"
              />
            </Form.Item>

            <Form.Item>
              <Button type="primary" html-type="submit" size="large" block>
                发送消息
              </Button>
            </Form.Item>
          </Form>
        </Card>
      </Col>
    </Row>
  </div>
</template>

<style scoped>
.contact-container {
  max-width: 1200px;
  margin: 0 auto;
}

.contact-item {
  display: flex;
  align-items: flex-start;
  gap: 16px;
  padding: 16px 0;
}

.contact-icon {
  flex-shrink: 0;
  width: 40px;
  height: 40px;
  display: flex;
  align-items: center;
  justify-content: center;
  background: #f0f8ff;
  border-radius: 8px;
}

.contact-content {
  flex: 1;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .contact-item {
    flex-direction: column;
    text-align: center;
    gap: 12px;
  }

  .contact-icon {
    align-self: center;
  }
}
</style>
