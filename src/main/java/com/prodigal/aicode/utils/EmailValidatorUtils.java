package com.prodigal.aicode.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Lang
 * @project prodigal-ai-code
 * @Version: 1.0
 * @description 邮箱验证工具类
 * @since 2025/8/9
 */
public class EmailValidatorUtils {
    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
