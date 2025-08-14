<script setup lang="ts">
import { Card, Row, Col, Typography, Tag, Space, Button } from 'ant-design-vue'
import { GithubOutlined, LinkOutlined } from '@ant-design/icons-vue'

const { Title, Paragraph } = Typography

// 项目数据
const projects = [
  {
    id: 1,
    title: 'Prodigal Picture',
    description: '基于 Vue 3 + TypeScript + Ant Design Vue & Spring Boot/Spring Cloud & Alibaba 构建的现代化前后端分离项目',
    tags: ['Vue 3', 'TypeScript', 'Ant Design Vue','Spring boot 3','Maven','Redis','COS','Sa-Token'],
    github: 'https://github.com/StarsTears/prodigal-picture',
    demo: 'https://demo.prodigal.dev',
    image: '/logo.svg'
  },
  {
    id: 2,
    title: '智能代码生成器',
    description: '基于 Vue 3 + TypeScript + Ant Design Vue + AI 技术的智能代码生成工具，支持多种编程语言',
    tags: ['AI', 'Vue 3', 'TypeScript', 'Ant Design Vue'],
    github: 'ttps://github.com/StarsTears/prodigal-ai-code',
    demo: 'https://ai-code.prodigal.dev',
    image: '/logo.svg'
  },
  {
    id: 3,
    title: '项目管理平台',
    description: '现代化的项目管理解决方案，支持团队协作和任务跟踪',
    tags: ['Vue 3', 'Node.js', 'MongoDB'],
    github: 'https://github.com/prodigal/project-manager',
    demo: 'https://pm.prodigal.dev',
    image: '/logo.svg'
  }
]
</script>

<template>
  <div class="projects-container">
    <Row :gutter="[24, 24]">
      <Col :span="24">
        <Card>
          <Title level="2">项目展示</Title>
          <Paragraph>
            这里展示了我们团队开发的一些优秀项目，涵盖了前端、后端、AI等多个技术领域。
          </Paragraph>
        </Card>
      </Col>

      <Col
        v-for="project in projects"
        :key="project.id"
        :xs="24"
        :sm="12"
        :md="8"
      >
        <Card
          class="project-card"
          :body-style="{ padding: '20px' }"
          hoverable
        >
          <div class="project-header">
            <img :src="project.image" :alt="project.title" class="project-image" />
            <Title level="4" class="project-title">{{ project.title }}</Title>
          </div>

          <Paragraph class="project-description">
            {{ project.description }}
          </Paragraph>

          <div class="project-tags">
            <Tag
              v-for="tag in project.tags"
              :key="tag"
              color="blue"
            >
              {{ tag }}
            </Tag>
          </div>

          <div class="project-actions">
            <Space>
              <Button
                type="primary"
                size="small"
                @click="window.open(project.github, '_blank')"
              >
                <template #icon>
                  <GithubOutlined />
                </template>
                GitHub
              </Button>
              <Button
                size="small"
                @click="window.open(project.demo, '_blank')"
              >
                <template #icon>
                  <LinkOutlined />
                </template>
                演示
              </Button>
            </Space>
          </div>
        </Card>
      </Col>
    </Row>
  </div>
</template>

<style scoped>
.projects-container {
  max-width: 1200px;
  margin: 0 auto;
}

.project-card {
  height: 100%;
  transition: all 0.3s ease;
}

.project-card:hover {
  transform: translateY(-4px);
  box-shadow: 0 8px 25px rgba(0, 0, 0, 0.1);
}

.project-header {
  display: flex;
  align-items: center;
  gap: 12px;
  margin-bottom: 16px;
}

.project-image {
  width: 40px;
  height: 40px;
  object-fit: contain;
}

.project-title {
  margin: 0 !important;
  flex: 1;
}

.project-description {
  color: #666;
  margin-bottom: 16px;
  line-height: 1.6;
}

.project-tags {
  margin-bottom: 16px;
}

.project-actions {
  display: flex;
  justify-content: flex-end;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .project-header {
    flex-direction: column;
    text-align: center;
    gap: 8px;
  }

  .project-actions {
    justify-content: center;
  }
}
</style>
