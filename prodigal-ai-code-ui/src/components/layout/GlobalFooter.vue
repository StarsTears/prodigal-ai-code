<template>
  <a-layout-footer class="global-footer">
    <div class="footer-container">
      <div class="footer-content">
        <p class="copyright">
          Copyright ©  程序员Prodigal
        </p>
      </div>
    </div>
  </a-layout-footer>
</template>

<script setup lang="ts">
import { Layout } from 'ant-design-vue'

const { Footer } = Layout
</script>

<style scoped>
.global-footer {
  background: #001529;
  color: #fff;
  text-align: center;
  padding: 0;
  height: 70px;
  line-height: 70px;
  position: relative;
  bottom: 0;
  width: 100%;
}

.footer-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 24px;
  height: 100%;
}

.footer-content {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100%;
}

.copyright {
  margin: 0;
  font-size: 14px;
  color: rgba(255, 255, 255, 0.85);
}

/* 响应式设计 */
@media (max-width: 768px) {
  .footer-container {
    padding: 0 16px;
  }

  .copyright {
    font-size: 12px;
  }
}
</style>
