<template>
  <div class="user-management">
    <!-- 搜索区域 -->
    <a-card class="search-card" :bordered="false">
      <a-form layout="inline" :model="searchForm" @finish="handleSearch">
        <a-form-item label="用户名" name="userName">
          <a-input
            v-model:value="searchForm.userName"
            placeholder="请输入用户名"
            allow-clear
            style="width: 200px"
          />
        </a-form-item>
        <a-form-item label="用户账号" name="userAccount">
          <a-input
            v-model:value="searchForm.userAccount"
            placeholder="请输入用户账号"
            allow-clear
            style="width: 200px"
          />
        </a-form-item>
        <a-form-item label="邮箱" name="userEmail">
          <a-input
            v-model:value="searchForm.userEmail"
            placeholder="请输入邮箱"
            allow-clear
            style="width: 200px"
          />
        </a-form-item>
        <a-form-item label="用户角色" name="userRole">
          <a-select
            v-model:value="searchForm.userRole"
            placeholder="请选择用户角色"
            allow-clear
            style="width: 150px"
          >
            <a-select-option value="user">普通用户</a-select-option>
            <a-select-option value="admin">管理员</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item>
          <a-space>
            <a-button type="primary" html-type="submit" :loading="loading">
              <template #icon><SearchOutlined /></template>
              搜索
            </a-button>
            <a-button @click="handleReset">
              <template #icon><ReloadOutlined /></template>
              重置
            </a-button>
          </a-space>
        </a-form-item>
      </a-form>
    </a-card>

    <!-- 操作按钮区域 -->
    <a-card class="action-card" :bordered="false">
      <a-space>
        <a-button type="primary" @click="handleAdd">
          <template #icon><PlusOutlined /></template>
          新增用户
        </a-button>
        <a-button @click="handleRefresh">
          <template #icon><ReloadOutlined /></template>
          刷新
        </a-button>
      </a-space>
    </a-card>

    <!-- 表格区域 -->
    <a-card :bordered="false">
      <a-table
        :columns="columns"
        :data-source="tableData"
        :loading="loading"
        :pagination="pagination"
        row-key="id"
        @change="handleTableChange"
      >
        <
        <template #bodyCell="{ column, record }">
          <!-- id列 -->
          <template v-if="column.key === 'id'">
            <a-typography-paragraph
              :ellipsis="{ tooltip: record.id }"
              :content="record.id"
              class="single-line-ellipsis"
            />
          </template>
          <!-- 用户头像列 -->
          <template v-else-if="column.key === 'userAvatar'">
            <a-avatar :src="record.userAvatar || '/logo.svg'" :size="40">
              <template #icon><UserOutlined /></template>
            </a-avatar>
          </template>

          <!-- 用户名列 -->
          <template v-else-if="column.key === 'userName'">
            <a-typography-paragraph
              :ellipsis="{ tooltip: record.userName }"
              :content="record.userName"
              class="single-line-ellipsis"
            />
          </template>

          <!-- 用户账号列 -->
          <template v-else-if="column.key === 'userAccount'">
            <a-typography-paragraph
              :ellipsis="{ tooltip: record.userAccount }"
              :content="record.userAccount"
              class="single-line-ellipsis"
            />
          </template>

          <!-- 邮箱列 -->
          <template v-else-if="column.key === 'userEmail'">
            <a-typography-paragraph
              :ellipsis="{ tooltip: record.userEmail }"
              :content="record.userEmail"
              class="single-line-ellipsis"
            />
          </template>

          <!-- 用户角色列 -->
          <template v-else-if="column.key === 'userRole'">
            <a-tag :color="record.userRole === 'admin' ? 'red' : 'blue'">
              {{ record.userRole === 'admin' ? '管理员' : '普通用户' }}
            </a-tag>
          </template>

          <!-- 创建时间列 -->
          <template v-else-if="column.key === 'createTime'">
            {{ formatDate(record.createTime) }}
          </template>

          <!-- 用户简介列 -->
          <template v-else-if="column.key === 'userProfile'">
            <a-typography-paragraph
              :ellipsis="{ tooltip: record.userProfile || '暂无简介' }"
              :content="record.userProfile"
              class="single-line-ellipsis"
            />
          </template>

          <!-- 操作列 -->
          <template v-else-if="column.key === 'action'">
            <a-space>
              <a-button type="link" size="small" @click="handleEdit(record)">
                <template #icon><EditOutlined /></template>
                编辑
              </a-button>
              <a-button type="link" size="small" @click="handleView(record)">
                <template #icon><EyeOutlined /></template>
                查看
              </a-button>
              <a-popconfirm
                title="确定要删除这个用户吗？"
                ok-text="确定"
                cancel-text="取消"
                @confirm="handleDelete(record)"
              >
                <a-button type="link" size="small" danger>
                  <template #icon><DeleteOutlined /></template>
                  删除
                </a-button>
              </a-popconfirm>
            </a-space>
          </template>
        </template>
      </a-table>
    </a-card>

    <!-- 用户表单模态框 -->
    <a-modal
      v-model:open="modalVisible"
      :title="modalTitle"
      :confirm-loading="submitLoading"
      @ok="handleSubmit"
      @cancel="handleCancel"
      width="600px"
    >
      <!-- 初始密码提示 -->
      <div v-if="!isEditMode && !isViewMode" class="password-tip">
        <a-alert
          message="初始密码提示"
          description="新用户的初始密码为：123456"
          type="info"
          show-icon
          style="margin-bottom: 16px"
        />
      </div>

      <a-form
        ref="formRef"
        :model="formData"
        :rules="formRules"
        layout="vertical"
      >
        <a-row :gutter="16">
          <a-col :span="12">
            <a-form-item label="用户名" name="userName">
              <a-input
                v-model:value="formData.userName"
                placeholder="请输入用户名"
                :disabled="isViewMode"
              />
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="用户账号" name="userAccount">
              <a-input
                v-model:value="formData.userAccount"
                placeholder="请输入用户账号"
                :disabled="isViewMode || isEditMode"
              />
            </a-form-item>
          </a-col>
        </a-row>

        <a-row :gutter="16">
          <a-col :span="12">
            <a-form-item label="邮箱" name="userEmail">
              <a-input
                v-model:value="formData.userEmail"
                placeholder="请输入邮箱"
                :disabled="isViewMode"
              />
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="用户角色" name="userRole">
              <a-select
                v-model:value="formData.userRole"
                placeholder="请选择用户角色"
                :disabled="isViewMode"
              >
                <a-select-option value="user">普通用户</a-select-option>
                <a-select-option value="admin">管理员</a-select-option>
              </a-select>
            </a-form-item>
          </a-col>
        </a-row>

<!--        <a-form-item label="用户密码" name="userPassword" v-if="!isEditMode && !isViewMode">-->
<!--          <a-input-password-->
<!--            v-model:value="formData.userPassword"-->
<!--            placeholder="请输入用户密码"-->
<!--          />-->
<!--        </a-form-item>-->

        <a-form-item label="用户简介" name="userProfile">
          <a-textarea
            v-model:value="formData.userProfile"
            placeholder="请输入用户简介"
            :rows="3"
            :disabled="isViewMode"
          />
        </a-form-item>

        <a-form-item label="用户头像" name="userAvatar">
          <a-input
            v-model:value="formData.userAvatar"
            placeholder="请输入头像URL"
            :disabled="isViewMode"
          />
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted, computed } from 'vue'
import { message } from 'ant-design-vue'
import {
  SearchOutlined,
  ReloadOutlined,
  PlusOutlined,
  EditOutlined,
  DeleteOutlined,
  EyeOutlined,
  UserOutlined
} from '@ant-design/icons-vue'
import type { TablePaginationConfig } from 'ant-design-vue'
import {
  page,
  save,
  update,
  remove,
  getInfo
} from '@/api/userController'

// 表格列定义
const columns = [
  {
    title: 'ID',
    key: 'id',
    width: 80,
    align: 'center',
    ellipsis: true
  },
  {
    title: '头像',
    key: 'userAvatar',
    width: 80,
    align: 'center'
  },
  {
    title: '用户名',
    key: 'userName',
    width: 120
  },
  {
    title: '用户账号',
    key: 'userAccount',
    width: 120
  },
  {
    title: '邮箱',
    key: 'userEmail',
    width: 180
  },
  {
    title: '用户角色',
    key: 'userRole',
    width: 100,
    align: 'center'
  },
  {
    title: '用户简介',
    dataIndex: 'userProfile',
    key: 'userProfile'
  },
  {
    title: '创建时间',
    key: 'createTime',
    width: 160
  },
  {
    title: '操作',
    key: 'action',
    width: 200,
    fixed: 'right'
  }
]

// 响应式数据
const loading = ref(false)
const tableData = ref<API.UserVO[]>([])
const modalVisible = ref(false)
const submitLoading = ref(false)
const isViewMode = ref(false)
const isEditMode = ref(false)
const currentRecord = ref<API.UserVO | null>(null)

// 搜索表单
const searchForm = reactive<API.UserQueryDto>({
  current: 1,
  pageSize: 10,
  userName: '',
  userAccount: '',
  userEmail: '',
  userRole: ''
})

// 表单数据
const formData = reactive<API.UserVO & { id?: string }>({
  userName: '',
  userAccount: '',
  userEmail: '',
  userProfile: '',
  userRole: 'user',
  userAvatar: ''
})

// 表单验证规则
const formRules = {
  userName: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
    { min: 2, max: 20, message: '用户名长度在 2 到 20 个字符', trigger: 'blur' }
  ],
  userAccount: [
    { required: true, message: '请输入用户账号', trigger: 'blur' },
    { min: 4, max: 20, message: '用户账号长度在 4 到 20 个字符', trigger: 'blur' }
  ],
  userEmail: [
    { required: true, message: '请输入邮箱', trigger: 'blur' },
    { type: 'email', message: '请输入正确的邮箱格式', trigger: 'blur' }
  ],
  userRole: [
    { required: true, message: '请选择用户角色', trigger: 'change' }
  ]
}

// 分页配置
const pagination = reactive<TablePaginationConfig>({
  current: 1,
  pageSize: 10,
  total: 0,
  showSizeChanger: true,
  showQuickJumper: true,
  showTotal: (total, range) => `第 ${range[0]}-${range[1]} 条/共 ${total} 条`
})

// 计算属性
const modalTitle = computed(() => {
  if (isViewMode.value) return '查看用户'
  if (isEditMode.value) return '编辑用户'
  return '新增用户'
})

// 工具函数：过滤空的搜索条件
const filterEmptyParams = (params: API.UserQueryDto): API.UserQueryDto => {
  const filteredParams = { ...params }

  // 需要保留的分页和排序字段
  const keepFields = ['current', 'pageSize', 'sortField', 'sortOrder']

  Object.keys(filteredParams).forEach(key => {
    if (!keepFields.includes(key)) {
      const value = filteredParams[key]
      if (!value || value.toString().trim() === '') {
        delete filteredParams[key]
      }
    }
  })

  return filteredParams
}

// 方法
const fetchData = async () => {
  loading.value = true
  try {
    // 过滤掉空的搜索条件
    const searchParams = filterEmptyParams(searchForm)

    const res = await page(searchParams)
    if (res.data.code === 0 && res.data.data) {
      tableData.value = res.data.data.records || []
      pagination.total = res.data.data.totalRow || 0
      pagination.current = res.data.data.pageNumber || 1
      pagination.pageSize = res.data.data.pageSize || 10
    } else {
      message.error(res.data.msg || '获取数据失败')
    }
  } catch (error) {
    console.error('获取用户列表失败:', error)
    message.error('获取用户列表失败')
  } finally {
    loading.value = false
  }
}

const handleSearch = () => {
  searchForm.current = 1
  // 搜索时直接调用fetchData，会使用过滤逻辑
  fetchData()
}

const handleReset = () => {
  Object.assign(searchForm, {
    current: 1,
    pageSize: 10,
    userName: '',
    userAccount: '',
    userEmail: '',
    userRole: ''
  })
  // 重置后直接调用fetchData，会使用过滤逻辑
  fetchData()
}

const handleRefresh = () => {
  fetchData()
}

const handleTableChange = (pag: TablePaginationConfig) => {
  searchForm.current = pag.current || 1
  searchForm.pageSize = pag.pageSize || 10
  fetchData()
}

const resetForm = () => {
  Object.assign(formData, {
    userName: '',
    userAccount: '',
    userEmail: '',
    userPassword: '',
    userProfile: '',
    userRole: 'user',
    userAvatar: ''
  })
}

const handleAdd = () => {
  isViewMode.value = false
  isEditMode.value = false
  currentRecord.value = null
  resetForm()
  modalVisible.value = true
}

const handleEdit = async (record: API.UserVO) => {
  isViewMode.value = false
  isEditMode.value = true
  currentRecord.value = record

  try {
    const res = await getInfo({ id: record.id! })
    if (res.data.code === 0 && res.data.data) {
      Object.assign(formData, {
        id: res.data.data.id,
        userName: res.data.data.userName,
        userAccount: res.data.data.userAccount,
        userEmail: res.data.data.userEmail,
        userProfile: res.data.data.userProfile,
        userRole: res.data.data.userRole,
        userAvatar: res.data.data.userAvatar
      })
    }
  } catch (error) {
    console.error('获取用户详情失败:', error)
    message.error('获取用户详情失败')
  }

  modalVisible.value = true
}

const handleView = async (record: API.UserVO) => {
  isViewMode.value = true
  isEditMode.value = false
  currentRecord.value = record

  try {
    const res = await getInfo({ id: record.id! })
    if (res.data.code === 0 && res.data.data) {
      Object.assign(formData, {
        id: res.data.data.id,
        userName: res.data.data.userName,
        userAccount: res.data.data.userAccount,
        userEmail: res.data.data.userEmail,
        userProfile: res.data.data.userProfile,
        userRole: res.data.data.userRole,
        userAvatar: res.data.data.userAvatar
      })
    }
  } catch (error) {
    console.error('获取用户详情失败:', error)
    message.error('获取用户详情失败')
  }

  modalVisible.value = true
}

const handleDelete = async (record: API.UserVO) => {
  try {
    const res = await remove({ id: record.id! })
    if (res.data.code === 0) {
      message.success('删除成功')
      fetchData()
    } else {
      message.error(res.data.msg || '删除失败')
    }
  } catch (error) {
    console.error('删除用户失败:', error)
    message.error('删除用户失败')
  }
}

const handleSubmit = async () => {
  submitLoading.value = true
  try {
    let res
    if (isEditMode.value) {
      res = await update(formData as API.UserUpdateDto)
    } else {
      res = await save(formData as API.UserAddDto)
    }

    if (res.data.code === 0) {
      message.success(isEditMode.value ? '更新成功' : '创建成功')
      modalVisible.value = false
      fetchData()
    } else {
      message.error(res.data.msg || (isEditMode.value ? '更新失败' : '创建失败'))
    }
  } catch (error) {
    console.error('提交失败:', error)
    message.error('提交失败')
  } finally {
    submitLoading.value = false
  }
}

const handleCancel = () => {
  modalVisible.value = false
  resetForm()
}

const formatDate = (dateString?: string) => {
  if (!dateString) return '-'
  return new Date(dateString).toLocaleString('zh-CN')
}

// 生命周期
onMounted(() => {
  fetchData()
})
</script>

<style scoped>
.user-management {
  padding: 24px;
}

.search-card {
  margin-bottom: 16px;
}

.action-card {
  margin-bottom: 16px;
}

:deep(.ant-table-thead > tr > th) {
  background-color: #fafafa;
  font-weight: 600;
}

:deep(.ant-table-tbody > tr:hover > td) {
  background-color: #f5f5f5;
}

/* 单行省略样式 */
.single-line-ellipsis {
  margin: 0;
  max-width: 100%;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
</style>
