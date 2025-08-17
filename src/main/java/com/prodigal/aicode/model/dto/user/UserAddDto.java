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
 * @description 用户创建请求
 * @since 2025/8/14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAddDto implements Serializable {
    /**
     * 账号
     */
    @Schema(description = "账号", example = "0")
    private String userAccount;
    /**
     * 邮箱
     */
    @Schema(description = "用户邮箱", example = "prodigal@dev.com")
    private String userEmail;
    /**
     * 名称
     */
    @Schema(description = "用户昵称", example = "0")
    private String userName;
    /**
     * 密码
     */
    @Schema(description = "密码", example = "0")
    private String userPassword;

    /**
     * 用户简介
     */
    @Schema(description = "用户简介", example = "0")
    private String userProfile;

    /**
     * 用户角色：user/admin
     */
    @Schema(description = "用户角色：user/admin", example = "user")
    private String userRole;

}
