package com.prodigal.aicode.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Lang
 * @project prodigal-ai-code
 * @Version: 1.0
 * @description 用户登录请求
 * @since 2025/8/9
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto implements Serializable {
    private String userAccount;
    private String userPassword;
}
