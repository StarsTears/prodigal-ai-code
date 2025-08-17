package com.prodigal.aicode.model.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

import java.io.Serial;

import com.mybatisflex.core.keygen.KeyGenerators;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 用户 实体类。
 *
 * @author Lang
 * @since 2025-08-09
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("user")
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Id(keyType = KeyType.Generator,value = KeyGenerators.snowFlakeId)
    @Schema(description = "id", example = "1")
    private Long id;

    /**
     * 账号
     */
    @Column("userAccount")
    @Schema(description = "账号", example = "0")
    private String userAccount;

    /**
     * 密码
     */
    @Column("userPassword")
    @Schema(description = "密码", example = "0")
    private String userPassword;

    /**
     * 用户昵称
     */
    @Column("userName")
    @Schema(description = "用户昵称", example = "0")
    private String userName;
    /**
     * 邮箱
     */
    @Column("userEmail")
    @Schema(description = "邮箱", example = "prodigal@dev.com")
    private String userEmail;

    /**
     * 用户头像
     */
    @Column("userAvatar")
    @Schema(description = "用户头像", example = "0")
    private String userAvatar;

    /**
     * 用户简介
     */
    @Column("userProfile")
    @Schema(description = "用户简介", example = "0")
    private String userProfile;

    /**
     * 用户角色：user/admin
     */
    @Column("userRole")
    @Schema(description = "用户角色：user/admin", example = "user")
    private String userRole;

    /**
     * 编辑时间
     */
    @Column("editTime")
    @Schema(description = "编辑时间", example = "2025-08-01 00:00:00")
    private LocalDateTime editTime;

    /**
     * 创建时间
     */
    @Column("createTime")
    @Schema(description = "创建时间", example = "2025-08-01 00:00:00")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Column("updateTime")
    @Schema(description = "更新时间", example = "2025-08-01 00:00:00")
    private LocalDateTime updateTime;

    /**
     * 是否删除
     */
    @Column(value = "isDelete", isLogicDelete = true)
    @Schema(description = "是否删除", example = "0")
    private Integer isDelete;

}
