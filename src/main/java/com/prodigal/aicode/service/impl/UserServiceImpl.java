package com.prodigal.aicode.service.impl;

import cn.hutool.core.util.StrUtil;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.prodigal.aicode.constant.UserConstant;
import com.prodigal.aicode.enums.UserRoleEnum;
import com.prodigal.aicode.exception.BusinessException;
import com.prodigal.aicode.exception.ErrorCode;
import com.prodigal.aicode.exception.ThrowUtils;
import com.prodigal.aicode.model.dto.user.UserLoginDto;
import com.prodigal.aicode.model.dto.user.UserQueryDto;
import com.prodigal.aicode.model.dto.user.UserRegisterDto;
import com.prodigal.aicode.model.entity.User;
import com.prodigal.aicode.mapper.UserMapper;
import com.prodigal.aicode.model.vo.LoginUserVO;
import com.prodigal.aicode.model.vo.UserVO;
import com.prodigal.aicode.service.UserService;
import com.prodigal.aicode.utils.EmailValidatorUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户 服务层实现。
 *
 * @author Lang
 * @since 2025-08-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public String getEncryptPassword(String userPassword) {
        final String SALT = "prodigal";
        return DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
    }

    @Override
    public long userRegister(UserRegisterDto userRegisterDto) {
        String userAccount = userRegisterDto.getUserAccount();
        String userEmail = userRegisterDto.getUserEmail();
        String userPassword = userRegisterDto.getUserPassword();
        String checkPassword = userRegisterDto.getCheckPassword();
        //数据校验
        if (StrUtil.hasBlank(userAccount, userPassword, checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (StrUtil.isNotBlank(userEmail)) {
            ThrowUtils.throwIf(!EmailValidatorUtils.isValidEmail(userEmail), ErrorCode.PARAMS_ERROR, "邮箱格式错误");
        }
        ThrowUtils.throwIf(userAccount.length() < 4, ErrorCode.PARAMS_ERROR, "用户账号过短");
        ThrowUtils.throwIf(userPassword.length() < 6 || checkPassword.length() < 6, ErrorCode.PARAMS_ERROR, "用户密码过短");
        ThrowUtils.throwIf(!userPassword.equals(checkPassword), ErrorCode.PARAMS_ERROR, "两次输入密码不一致");
        //检查当前账户是否已存在，验重
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq(User::getUserAccount, userAccount);
        long count = this.mapper.selectCountByQuery(queryWrapper);
        ThrowUtils.throwIf(count > 0, ErrorCode.PARAMS_ERROR, "账号已存在");

        String encryptPassword = getEncryptPassword(userPassword);

        //插入数据
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserName(userAccount);
        if (StrUtil.isNotBlank(userEmail)) {
            user.setUserEmail(userEmail);
        }
        user.setUserPassword(encryptPassword);
        user.setUserRole(UserRoleEnum.USER.getValue());
        boolean isOk = this.save(user);
        ThrowUtils.throwIf(!isOk, ErrorCode.OPERATION_ERROR, "注册失败数据库错误");
        return user.getId();
    }

    @Override
    public LoginUserVO userLogin(UserLoginDto userLoginDto, HttpServletRequest request) {
        String userAccount = userLoginDto.getUserAccount();
        String userPassword = userLoginDto.getUserPassword();
        ThrowUtils.throwIf(StrUtil.hasBlank(userAccount, userPassword), ErrorCode.PARAMS_ERROR);
        ThrowUtils.throwIf(userAccount.length() < 4, ErrorCode.PARAMS_ERROR, "用户账号过短");
        ThrowUtils.throwIf(userPassword.length() < 6, ErrorCode.PARAMS_ERROR, "用户密码过短");
        //密码加密
        String encryptPassword = getEncryptPassword(userPassword);
        //查询 数据
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq(User::getUserAccount, userAccount).eq(User::getUserPassword, encryptPassword);
        User user = this.mapper.selectOneByQuery(queryWrapper);
        ThrowUtils.throwIf(user == null, ErrorCode.PARAMS_ERROR, "用户不存在或密码错误");
        //记录用户登录态
        request.getSession().setAttribute(UserConstant.USER_LOGIN_KEY, user);
        return this.getLoginUserVO(user);
    }

    @Override
    public User getLoginUser(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(UserConstant.USER_LOGIN_KEY);
        User currentUser = (User) userObj;
        if (currentUser == null || currentUser.getId() == null) {
            throw new BusinessException(ErrorCode.USER_NOT_LOGIN);
        }
        Long id = currentUser.getId();
        currentUser = this.getById(id);
        if (currentUser == null) {
            throw new BusinessException(ErrorCode.USER_NOT_LOGIN);
        }
        return currentUser;
    }
    @Override
    public boolean userLogout(HttpServletRequest request){
        Object userObj = request.getSession().getAttribute(UserConstant.USER_LOGIN_KEY);
        ThrowUtils.throwIf(userObj == null, ErrorCode.USER_NOT_LOGIN);
        //移除登录状态
        request.getSession().removeAttribute(UserConstant.USER_LOGIN_KEY);
        return true;
    }

    @Override
    public QueryWrapper buildQueryWrapper(UserQueryDto userQueryDto) {
        ThrowUtils.throwIf(userQueryDto == null, ErrorCode.PARAMS_ERROR,"请求参数为空");
        return QueryWrapper.create()
                .eq(User::getId, userQueryDto.getId())
                .eq(User::getUserName, userQueryDto.getUserName())
                .like(User::getUserAccount, userQueryDto.getUserAccount())
                .like(User::getUserEmail, userQueryDto.getUserEmail())
                .like(User::getUserProfile, userQueryDto.getUserProfile())
                .orderBy(userQueryDto.getSortField(), userQueryDto.getSortOrder().equals("ascend"));
    }


    @Override
    public LoginUserVO getLoginUserVO(User user) {
        if (user == null) return null;
        LoginUserVO loginUserVO = new LoginUserVO();
        BeanUtils.copyProperties(user, loginUserVO);
        return loginUserVO;
    }

    @Override
    public UserVO getUserVO(User user) {
        if (user == null) return null;
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }
    @Override
    public List<UserVO> getUserVOList(List<User> userList) {
        if (CollectionUtils.isEmpty(userList))
            return new ArrayList<>();
        return userList.stream()
                .map(this::getUserVO)
                .collect(Collectors.toList());
    }
}
