package com.prodigal.aicode.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Lang
 * @project prodigal-ai-code
 * @Version: 1.0
 * @description 用户信息（脱敏）
 * @since 2025/8/14
 */
@Data
public class UserVO implements Serializable {
    /**
     * id
     */
    @Schema(description = "id", example = "1")
    private Long id;

    /**
     * 账号
     */
    @Schema(description = "账号", example = "0")
    private String userAccount;

    /**
     * 用户昵称
     */
    @Schema(description = "用户昵称", example = "0")
    private String userName;
    /**
     * 邮箱
     */
    @Schema(description = "用户邮箱", example = "prodigal@dev.com")
    private String userEmail;

    /**
     * 用户头像
     */
    @Schema(description = "用户头像", example = "0")
    private String userAvatar;

    /**
     * 用户简介
     */
    @Schema(description = "用户简介", example = "0")
    private String userProfile;

    /**
     * 用户角色：user/admin
     */
    @Schema(description = "用户角色", example = "user")
    private String userRole;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间", example = "2025-08-01 00:00:00")
    private LocalDateTime createTime;

    private static final long serialVersionUID = 1L;

}
