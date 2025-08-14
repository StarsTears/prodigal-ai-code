package com.prodigal.aicode.aop;

import com.prodigal.aicode.annotation.AuthCheck;
import com.prodigal.aicode.enums.UserRoleEnum;
import com.prodigal.aicode.exception.BusinessException;
import com.prodigal.aicode.exception.ErrorCode;
import com.prodigal.aicode.model.entity.User;
import com.prodigal.aicode.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author Lang
 * @project prodigal-ai-code
 * @Version: 1.0
 * @description 权限校验
 * @since 2025/8/14
 */
@Slf4j
@Aspect
@Component
public class AuthInterceptor {
    @Resource
    private UserService userService;

    @Before("@annotation(authCheck)")
    public Object doInterceptor(ProceedingJoinPoint joinPoint,AuthCheck authCheck) throws Throwable {
        String mustRole = authCheck.mustRole();
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        //获取当前登录用户
        User loginUser = userService.getLoginUser(request);
        UserRoleEnum mustRoleEnum = UserRoleEnum.getEnumByValue(mustRole);
        //无需权限，直接放行
        if (mustRoleEnum == null) {
            joinPoint.proceed();
        }
        String userRole = loginUser.getUserRole();
        UserRoleEnum userRoleEnum = UserRoleEnum.getEnumByValue(userRole);
        if (userRoleEnum == null) {
            throw new BusinessException(ErrorCode.USER_NOT_AUTHORIZED);
        }
        //需要管理员权限，但是当前用户不是管理员
        if (!UserRoleEnum.ADMIN.equals(userRoleEnum) && UserRoleEnum.ADMIN.equals(mustRoleEnum)){
            throw new BusinessException(ErrorCode.USER_NOT_AUTHORIZED);
        }
        return joinPoint.proceed();
    }
}
