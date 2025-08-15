<script setup lang="ts">
// 使用Vue3组合式API
</script>

<template>
  <div class="contact-container">
    <a-card>
      <template #title>
        <h2>联系我们</h2>
      </template>

      <a-row :gutter="[24, 24]">
        <a-col :xs="24" :md="12">
          <a-card title="联系方式" :bordered="false">
            <a-descriptions :column="1">
              <a-descriptions-item label="邮箱">
                <a href="mailto:prodigal.lang@qq.com">prodigal.lang@qq.com</a>
              </a-descriptions-item>
              <a-descriptions-item label="GitHub">
                <a href="https://github.com/StarsTears/prodigal-ai-code" target="_blank">
                  https://github.com/StarsTears/prodigal-ai-code
                </a>
              </a-descriptions-item>
              <a-descriptions-item label="官方网站">
                <a href="https://prodigal.dev" target="_blank">
                  https://prodigal.dev
                </a>
              </a-descriptions-item>
            </a-descriptions>
          </a-card>
        </a-col>

        <a-col :xs="24" :md="12">
          <a-card title="留言表单" :bordered="false">
            <a-form layout="vertical">
              <a-form-item label="姓名" name="name">
                <a-input placeholder="请输入您的姓名" />
              </a-form-item>

              <a-form-item label="邮箱" name="email">
                <a-input placeholder="请输入您的邮箱" />
              </a-form-item>

              <a-form-item label="主题" name="subject">
                <a-input placeholder="请输入留言主题" />
              </a-form-item>

              <a-form-item label="留言内容" name="message">
                <a-textarea
                  placeholder="请输入留言内容"
                  :rows="4"
                />
              </a-form-item>

              <a-form-item>
                <a-button type="primary" block>
                  提交留言
                </a-button>
              </a-form-item>
            </a-form>
          </a-card>
        </a-col>
      </a-row>
    </a-card>
  </div>
</template>

<style scoped>
.contact-container {
  max-width: 1200px;
  margin: 0 auto;
}

.contact-container h2 {
  margin: 0;
  color: #1890ff;
}
</style>
