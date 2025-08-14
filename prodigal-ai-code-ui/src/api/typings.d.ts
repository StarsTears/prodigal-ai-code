declare namespace API {
  type BaseResultBoolean = {
    /** 响应码 */
    code?: number
    /** 响应状态 */
    status?: boolean
    /** 响应消息 */
    msg?: string
    /** 响应数据 */
    data?: boolean
  }

  type BaseResultLoginUserVO = {
    /** 响应码 */
    code?: number
    /** 响应状态 */
    status?: boolean
    /** 响应消息 */
    msg?: string
    data?: LoginUserVO
  }

  type BaseResultLong = {
    /** 响应码 */
    code?: number
    /** 响应状态 */
    status?: boolean
    /** 响应消息 */
    msg?: string
    /** 响应数据 */
    data?: number
  }

  type BaseResultPageUserVO = {
    /** 响应码 */
    code?: number
    /** 响应状态 */
    status?: boolean
    /** 响应消息 */
    msg?: string
    data?: PageUserVO
  }

  type BaseResultString = {
    /** 响应码 */
    code?: number
    /** 响应状态 */
    status?: boolean
    /** 响应消息 */
    msg?: string
    /** 响应数据 */
    data?: string
  }

  type BaseResultUserVO = {
    /** 响应码 */
    code?: number
    /** 响应状态 */
    status?: boolean
    /** 响应消息 */
    msg?: string
    data?: UserVO
  }

  type DeleteRequest = {
    id?: number
  }

  type getInfoParams = {
    id: number
  }

  type LoginUserVO = {
    id?: number
    userAccount?: string
    userName?: string
    userEmail?: string
    userAvatar?: string
    userProfile?: string
    userRole?: string
    editTime?: string
    createTime?: string
    updateTime?: string
  }

  type PageUserVO = {
    records?: UserVO[]
    pageNumber?: number
    pageSize?: number
    totalPage?: number
    totalRow?: number
    optimizeCountQuery?: boolean
  }

  type User = {
    id?: number
    userAccount?: string
    userPassword?: string
    userName?: string
    userEmail?: string
    userAvatar?: string
    userProfile?: string
    userRole?: string
    editTime?: string
    createTime?: string
    updateTime?: string
    isDelete?: number
  }

  type UserAddDto = {
    userAccount?: string
    userEmail?: string
    userName?: string
    userPassword?: string
    userProfile?: string
    userRole?: string
  }

  type UserLoginDto = {
    userAccount?: string
    userPassword?: string
  }

  type UserQueryDto = {
    current?: number
    pageSize?: number
    sortField?: string
    sortOrder?: string
    id?: number
    userName?: string
    userAccount?: string
    userEmail?: string
    userProfile?: string
    userRole?: string
  }

  type UserRegisterDto = {
    userAccount?: string
    userEmail?: string
    userPassword?: string
    checkPassword?: string
  }

  type UserUpdateDto = {
    id?: number
    userName?: string
    userEmail?: string
    userProfile?: string
    userRole?: string
    userAvatar?: string
  }

  type UserVO = {
    id?: number
    userAccount?: string
    userName?: string
    userEmail?: string
    userAvatar?: string
    userProfile?: string
    userRole?: string
    createTime?: string
  }
}
