package com.prodigal.aicode.model.dto.user;

import com.mybatisflex.annotation.Column;
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
    private String userAccount;
    /**
     * 邮箱
     */
    private String userEmail;
    /**
     * 名称
     */
    private String userName;
    /**
     * 密码
     */
    private String userPassword;

    /**
     * 用户简介
     */
    private String userProfile;

    /**
     * 用户角色：user/admin
     */
    private String userRole;

}
