package com.prodigal.aicode.enums;

import cn.hutool.core.util.ObjectUtil;
import lombok.Getter;

/**
 * @author Lang
 * @project prodigal-ai-code
 * @Version: 1.0
 * @description 用户角色枚举类
 * @since 2025/8/9
 */
@Getter
public enum UserRoleEnum {
    USER("用户","USER"),
    ADMIN("管理员","ADMIN");
    private String text;
    private String value;
    UserRoleEnum(String text, String value) {
        this.text =text;
        this.value = value;
    }
    public static UserRoleEnum getEnumByValue(String value) {
        if (ObjectUtil.isEmpty( value)) return null;
        for (UserRoleEnum item : UserRoleEnum.values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        return null;
    }
}
