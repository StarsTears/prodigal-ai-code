// @ts-ignore
/* eslint-disable */
import request  from '@/request'

/** 此处后端没有提供注释 GET /user/get/login */
export async function getLoginUser(options?: { [key: string]: any }) {
  return request<API.BaseResultLoginUserVO>('/user/get/login', {
    method: 'GET',
    ...(options || {}),
  })
}

/** 此处后端没有提供注释 GET /user/getInfo/${param0} */
export async function getInfo(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getInfoParams,
  options?: { [key: string]: any }
) {
  const { id: param0, ...queryParams } = params
  return request<API.BaseResultUserVO>(`/user/getInfo/${param0}`, {
    method: 'GET',
    params: { ...queryParams },
    ...(options || {}),
  })
}

/** 此处后端没有提供注释 POST /user/list */
export async function list(options?: { [key: string]: any }) {
  return request<API.User[]>('/user/list', {
    method: 'POST',
    ...(options || {}),
  })
}

/** 此处后端没有提供注释 POST /user/login */
export async function login(body: API.UserLoginDto, options?: { [key: string]: any }) {
  return request<API.BaseResultLoginUserVO>('/user/login', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  })
}

/** 此处后端没有提供注释 POST /user/logout */
export async function logout(options?: { [key: string]: any }) {
  return request<API.BaseResultBoolean>('/user/logout', {
    method: 'POST',
    ...(options || {}),
  })
}

/** 此处后端没有提供注释 POST /user/page/vo */
export async function page(body: API.UserQueryDto, options?: { [key: string]: any }) {
  return request<API.BaseResultPageUserVO>('/user/page/vo', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  })
}

/** 此处后端没有提供注释 POST /user/register */
export async function register(body: API.UserRegisterDto, options?: { [key: string]: any }) {
  return request<API.BaseResultLong>('/user/register', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  })
}

/** 此处后端没有提供注释 POST /user/remove */
export async function remove(body: API.DeleteRequest, options?: { [key: string]: any }) {
  return request<API.BaseResultBoolean>('/user/remove', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  })
}

/** 此处后端没有提供注释 POST /user/save */
export async function save(body: API.UserAddDto, options?: { [key: string]: any }) {
  return request<API.BaseResultLong>('/user/save', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  })
}

/** 此处后端没有提供注释 POST /user/update */
export async function update(body: API.UserUpdateDto, options?: { [key: string]: any }) {
  return request<API.BaseResultBoolean>('/user/update', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  })
}
