# Prodigal AI Code UI

一个基于 Vue 3 + TypeScript + Ant Design Vue 构建的现代化前端项目模板。

## 功能特性

### 🎨 全局布局
- **响应式设计**: 支持桌面端和移动端
- **上中下布局**: 使用 Ant Design Vue 的 Layout 组件实现
- **固定导航栏**: 顶部导航栏固定，支持滚动
- **底部版权**: 固定在底部的版权信息

### 🔐 用户认证
- **登录页面**: 支持用户名/邮箱登录
- **注册页面**: 完整的用户注册流程
- **状态管理**: 使用 Pinia 管理用户状态
- **持久化存储**: 登录状态保存到 localStorage

### 📱 页面组件
- **首页**: 项目介绍和技术栈展示
- **关于我们**: 团队信息和联系方式
- **项目展示**: 项目列表和详情
- **联系我们**: 联系表单和信息

### 🎯 技术栈
- **Vue 3**: 使用 Composition API
- **TypeScript**: 完整的类型支持
- **Ant Design Vue**: 现代化 UI 组件库
- **Vue Router**: 路由管理
- **Pinia**: 状态管理
- **Vite**: 快速构建工具

## 快速开始

### 安装依赖
```bash
npm install
```

### 开发模式
```bash
npm run dev
```

### 构建生产版本
```bash
npm run build
```

### 预览生产版本
```bash
npm run preview
```

## 项目结构

```
src/
├── components/          # 组件目录
│   └── layout/         # 布局组件
│       ├── GlobalHeader.vue    # 全局头部
│       └── GlobalFooter.vue    # 全局底部
├── layouts/            # 布局模板
│   └── BasicLayout.vue # 基础布局
├── views/              # 页面组件
│   ├── HomeView.vue    # 首页
│   ├── AboutView.vue   # 关于我们
│   ├── ProjectsView.vue # 项目展示
│   ├── ContactView.vue # 联系我们
│   ├── LoginView.vue   # 登录页
│   └── RegisterView.vue # 注册页
├── stores/             # 状态管理
│   └── user.ts         # 用户状态
├── router/             # 路由配置
│   └── index.ts        # 路由定义
└── App.vue             # 应用入口
```

## 自定义配置

### 菜单配置
在 `src/components/layout/GlobalHeader.vue` 中修改 `menuItems` 数组来配置导航菜单。

### 用户状态
在 `src/stores/user.ts` 中可以扩展用户状态管理功能。

### 样式定制
项目使用 Ant Design Vue 的主题系统，可以在 `src/App.vue` 中自定义全局样式。

## 开发说明

### 组件开发规范
- 使用 Vue 3 Composition API
- 使用 TypeScript 进行类型定义
- 遵循 Ant Design Vue 的设计规范
- 支持响应式设计

### 状态管理
- 使用 Pinia 进行状态管理
- 用户信息持久化到 localStorage
- 支持登录状态自动恢复

### 路由管理
- 使用 Vue Router 4
- 支持路由懒加载
- 完整的路由守卫机制

## 许可证

© CopyRight by 程序员Prodigal
