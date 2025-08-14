package com.prodigal.aicode.model.dto.user;

import com.prodigal.aicode.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author Lang
 * @project prodigal-ai-code
 * @Version: 1.0
 * @description 用户查询请求
 * @since 2025/8/14
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserQueryDto extends PageRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String userName;
    private String userAccount;
    private String userEmail;
    private String userProfile;
    private String userRole;
}
