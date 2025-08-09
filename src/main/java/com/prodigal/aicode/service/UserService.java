package com.prodigal.aicode.service;

import com.mybatisflex.core.service.IService;
import com.prodigal.aicode.model.dto.user.UserLoginDto;
import com.prodigal.aicode.model.dto.user.UserRegisterDto;
import com.prodigal.aicode.model.entity.User;
import com.prodigal.aicode.model.vo.LoginUserVO;
import jakarta.servlet.http.HttpServletRequest;

/**
 * 用户 服务层。
 *
 * @author Lang
 * @since 2025-08-09
 */
public interface UserService extends IService<User> {
    /**
     * 获取加密密码
     * @param userPassword
     * @return
     */
    String getEncryptPassword(String userPassword);

    /**
     * 用户注册
     * @param userRegisterDto
     * @return
     */
    long userRegister(UserRegisterDto userRegisterDto);

    LoginUserVO userLogin(UserLoginDto userLoginDto, HttpServletRequest request);

    User getLoginUser(HttpServletRequest request);

    boolean userLogout(HttpServletRequest request);

    LoginUserVO getLoginUserVO(User user);
}
