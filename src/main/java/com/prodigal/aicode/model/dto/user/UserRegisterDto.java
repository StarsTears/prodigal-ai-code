package com.prodigal.aicode.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Lang
 * @project prodigal-ai-code
 * @Version: 1.0
 * @description 用户注册请求
 * @since 2025/8/9
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDto implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 账号
     */
    private String userAccount;
    /**
     * 邮箱
     */
    private String userEmail;
    /**
     * 密码
     */
    private String userPassword;
    /**
     * 校验密码
     */
    private String checkPassword;

}
