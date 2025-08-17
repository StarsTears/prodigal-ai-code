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
 * @description 用户登录请求
 * @since 2025/8/9
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto implements Serializable {
    @Schema(description = "账户", example = "111")
    private String userAccount;
    @Schema(description = "密码", example = "111")
    private String userPassword;
}
