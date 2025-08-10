<script setup lang="ts">
import { Card, Row, Col, Typography, Avatar, Space } from 'ant-design-vue'

const { Title, Paragraph } = Typography
</script>

<template>
  <div class="about-container">
    <Row :gutter="[24, 24]">
      <Col :span="24">
        <Card>
          <Space direction="vertical" size="large" style="width: 100%">
            <div class="about-header">
              <Avatar size={64} style="background-color: #1890ff">
                <span style="font-size: 24px; font-weight: bold">P</span>
              </Avatar>
              <Title level="2">关于我们</Title>
            </div>

            <Paragraph>
              程序员Prodigal致力于提供高质量的软件开发解决方案，专注于前后端技术栈的现代化应用开发。
            </Paragraph>

            <div>
              <Title level="4">我们的使命</Title>
              <Paragraph>
                通过创新的技术方案和专业的开发服务，为客户创造价值，推动软件行业的进步。
              </Paragraph>
            </div>

            <div>
              <Title level="4">核心价值</Title>
              <ul>
                <li>技术创新 - 持续关注前沿技术发展</li>
                <li>质量至上 - 确保每个项目的代码质量</li>
                <li>用户体验 - 以用户为中心的设计理念</li>
                <li>团队协作 - 高效的团队协作模式</li>
              </ul>
            </div>

            <div>
              <Title level="4">联系方式</Title>
              <Paragraph>
                邮箱：prodigal.lang@qq.com<br>
                地址：中国 · 深圳
              </Paragraph>
            </div>
          </Space>
        </Card>
      </Col>
    </Row>
  </div>
</template>

<style scoped>
.about-container {
  max-width: 1200px;
  margin: 0 auto;
}

.about-header {
  display: flex;
  align-items: center;
  gap: 16px;
  margin-bottom: 24px;
}

ul {
  margin: 0;
  padding-left: 20px;
}

li {
  margin-bottom: 8px;
}
</style>
