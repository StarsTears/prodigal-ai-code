package com.prodigal.aicode.model.dto.user;

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
    private Long id;
    private String userName;
    private String userEmail;
    private String userProfile;
    private String userRole;
    private String userAvatar;
}
