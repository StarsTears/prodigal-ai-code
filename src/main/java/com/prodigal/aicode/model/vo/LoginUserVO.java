package com.prodigal.aicode.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Lang
 * @project prodigal-ai-code
 * @Version: 1.0
 * @description 用户信息脱敏
 * @since 2025/8/9
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserVO implements Serializable {
    @Schema(description = "id", example = "1")
    private Long id;

    @Schema(description = "账号", example = "prodigal@dev.com")
    private String userAccount;

    @Schema(description = "用户昵称", example = "prodigal@dev.com")
    private String userName;

    @Schema(description = "用户邮箱", example = "prodigal@dev.com")
    private String userEmail;

    @Schema(description = "用户头像", example = "0")
    private String userAvatar;

    @Schema(description = "用户简介", example = "0")
    private String userProfile;

    @Schema(description = "用户角色", example = "user")
    private String userRole;

    @Schema(description = "编辑时间", example = "2025-08-01 00:00:00")
    private LocalDateTime editTime;

    @Schema(description = "创建时间", example = "2025-08-01 00:00:00")
    private LocalDateTime createTime;

    @Schema(description = "修改时间", example = "2025-08-01 00:00:00")
    private LocalDateTime updateTime;
}
