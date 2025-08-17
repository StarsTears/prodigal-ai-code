package com.prodigal.aicode.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "账号", example = "0")
    private String userAccount;
    /**
     * 邮箱
     */
    @Schema(description = "邮箱", example = "prodigal@dev.com")
    private String userEmail;
    /**
     * 密码
     */
    @Schema(description = "密码", example = "0")
    private String userPassword;
    /**
     * 校验密码
     */
    @Schema(description = "校验密码", example = "0")
    private String checkPassword;

}
