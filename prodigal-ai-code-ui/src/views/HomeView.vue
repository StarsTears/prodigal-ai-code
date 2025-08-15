<script setup lang="ts">
import { Card, Row, Col, Typography, Space } from 'ant-design-vue'

const { Title, Paragraph } = Typography
</script>

<template>
  <div class="home-container">
    <Row :gutter="[24, 24]">
      <Col :span="24">
        <Card>
          <Title level="2">欢迎来到 Prodigal AI Code</Title>
          <Paragraph>
            这是一个基于 Spring boot 3 + Mybatis Flex + MySQL + Vue 3 + TypeScript + Ant Design Vue + Axios + Umijs 构建的现代化前后端分离项目。
            <br/>
           可在 vite.config.ts 中打开vueDevTools(),即开启调试环境
          </Paragraph>
          <Space direction="vertical" size="large">
            <div>
              <Title level="4">技术栈</Title>
              <ul>
                <li>Spring boot 3</li>
                <li>Mybatis Flex</li>
                <li>MySQL</li>
                <li>Maven (依赖管理)</li>
                <li>Knife4j 4  (接口文档管理)</li>
                <li>Vue 3 (Composition API)</li>
                <li>TypeScript</li>
                <li>Ant Design Vue</li>
                <li>Axios(请求工具)</li>
                <li>Umijs(openAPI工具)</li>
                <li>Vue Router</li>
                <li>Pinia (状态管理)</li>
                <li>Vite (构建工具)</li>
              </ul>
            </div>
            <div>
              <Title level="4">项目特性</Title>
              <ul>
                <li>响应式布局设计</li>
                <li>组件化开发</li>
                <li>TypeScript 类型支持</li>
                <li>现代化 UI 组件库</li>
                <li>路由管理</li>
              </ul>
            </div>
          </Space>
        </Card>
      </Col>
    </Row>
  </div>
</template>

<style scoped>
.home-container {
  max-width: 1200px;
  margin: 0 auto;
}

ul {
  margin: 0;
  padding-left: 20px;
}

li {
  margin-bottom: 8px;
}
</style>
