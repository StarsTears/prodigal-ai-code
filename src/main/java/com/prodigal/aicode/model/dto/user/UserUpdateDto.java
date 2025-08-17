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
 * @description 用户更新请求
 * @since 2025/8/14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto implements Serializable {
    @Schema(description = "id", example = "1")
    private Long id;

    @Schema(description = "用户昵称", example = "0")
    private String userName;

    @Schema(description = "用户邮箱", example = "prodigal@dev.com")
    private String userEmail;

    @Schema(description = "用户简介", example = "0")
    private String userProfile;

    @Schema(description = "用户角色", example = "user")
    private String userRole;

    @Schema(description = "用户头像", example = "0")
    private String userAvatar;
}
