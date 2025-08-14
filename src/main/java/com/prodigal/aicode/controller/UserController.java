package com.prodigal.aicode.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.prodigal.aicode.annotation.AuthCheck;
import com.prodigal.aicode.common.BaseResult;
import com.prodigal.aicode.common.DeleteRequest;
import com.prodigal.aicode.common.ResultUtils;
import com.prodigal.aicode.constant.UserConstant;
import com.prodigal.aicode.exception.ErrorCode;
import com.prodigal.aicode.exception.ThrowUtils;
import com.prodigal.aicode.model.dto.user.*;
import com.prodigal.aicode.model.vo.LoginUserVO;
import com.prodigal.aicode.model.vo.UserVO;
import com.prodigal.aicode.utils.EmailValidatorUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.prodigal.aicode.model.entity.User;
import com.prodigal.aicode.service.UserService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 用户 控制层。
 *
 * @author Lang
 * @since 2025-08-09
 */
@RestController
@RequestMapping("/user")
@Tag(name = "UserController", description = "用户管理")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册用户。
     *
     * @param userRegisterDto 用户注册信息
     * @return 注册结果
     */
    @PostMapping("register")
    public BaseResult<Long> register(@RequestBody UserRegisterDto userRegisterDto) {
        return ResultUtils.success(userService.userRegister(userRegisterDto));
    }

    /**
     * 登录用户。
     *
     * @param userLoginDto 用户登录信息
     * @return 登录结果
     */
    @PostMapping("login")
    public BaseResult<LoginUserVO> login(@RequestBody UserLoginDto userLoginDto, HttpServletRequest request) {
        return ResultUtils.success(userService.userLogin(userLoginDto, request));
    }

    /**
     * 获取当前登录用户信息。
     *
     * @param request 请求对象
     * @return 当前登录用户信息
     */
    @GetMapping("get/login")
    public BaseResult<LoginUserVO> getLoginUser(HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        LoginUserVO loginUserVO = userService.getLoginUserVO(loginUser);
        return ResultUtils.success(loginUserVO);
    }

    @PostMapping("logout")
    public BaseResult<Boolean> logout(HttpServletRequest request){
        ThrowUtils.throwIf(request == null, ErrorCode.PARAMS_ERROR);
        return ResultUtils.success(userService.userLogout(request));
    }
    /**
     * 保存用户。
     *
     * @param userAddDto 用户
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResult<Long> save(@RequestBody UserAddDto userAddDto) {
        ThrowUtils.throwIf(userAddDto == null, ErrorCode.PARAMS_ERROR);
        ThrowUtils.throwIf(userAddDto.getUserAccount() == null, ErrorCode.PARAMS_ERROR, "用户账号不能为空");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq(User::getUserAccount, userAddDto.getUserAccount());
        boolean exists = userService.exists(queryWrapper);
        ThrowUtils.throwIf(exists, ErrorCode.OPERATION_ERROR, "用户已存在");

        User user = new User();
        BeanUtil.copyProperties(userAddDto, user);
        String userEmail = user.getUserEmail();
        if (StrUtil.isNotBlank(userEmail)) {
            ThrowUtils.throwIf(!EmailValidatorUtils.isValidEmail(userEmail), ErrorCode.PARAMS_ERROR, "邮箱格式错误");
        }
        //默认密码：123456
        user.setUserPassword(userService.getEncryptPassword("123456"));
        user.setUserName(user.getUserName()==null?user.getUserAccount():user.getUserName());
        boolean isOK = userService.save(user);
        ThrowUtils.throwIf(!isOK, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(user.getId()).msg("创建用户成功");
    }

    /**
     * 根据主键删除用户。
     *
     * @param deleteRequest 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @PostMapping("remove")
    public BaseResult<Boolean> remove(@RequestBody DeleteRequest deleteRequest) {
        ThrowUtils.throwIf(deleteRequest==null || deleteRequest.getId() <= 0, ErrorCode.PARAMS_ERROR);
        boolean isOK = userService.removeById(deleteRequest.getId());
        return ResultUtils.success(isOK);
    }

    /**
     * 根据主键更新用户。
     *
     * @param userUpdateDto 用户
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PostMapping("update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResult<Boolean> update(@RequestBody UserUpdateDto userUpdateDto,HttpServletRequest request) {
        ThrowUtils.throwIf(userUpdateDto == null || userUpdateDto.getId() <= 0, ErrorCode.PARAMS_ERROR);
        User user = new User();
        BeanUtil.copyProperties(userUpdateDto, user);
        boolean isOK = userService.updateById(user);
        ThrowUtils.throwIf(!isOK, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true).msg("更新成功");
    }

    /**
     * 查询所有用户。
     *
     * @return 所有数据
     */
    @PostMapping("list")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public List<User> list() {
        return userService.list();
    }

    /**
     * 根据主键获取用户。
     *
     * @param id 用户主键
     * @return 用户详情
     */
    @GetMapping("getInfo/{id}")
    public BaseResult<UserVO> getInfo(@PathVariable Long id) {
        User byId = userService.getById(id);
        UserVO userVO = userService.getUserVO(byId);
        return ResultUtils.success(userVO);
    }


    /**
     * 分页查询用户。
     *
     * @param userQueryDto 分页对象
     * @return 分页对象
     */
    @PostMapping("page/vo")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResult<Page<UserVO>> page(@RequestBody UserQueryDto userQueryDto) {
        ThrowUtils.throwIf(userQueryDto == null, ErrorCode.PARAMS_ERROR,"请求参数为空");
        long current = userQueryDto.getCurrent();
        long pageSize = userQueryDto.getPageSize();
        Page<User> userPage = userService.page(Page.of(current, pageSize), userService.buildQueryWrapper(userQueryDto));
        //数据脱敏
        Page<UserVO> userVOPage = new Page<>(current, pageSize, userPage.getTotalRow());
        List<UserVO> userVOList = userService.getUserVOList(userPage.getRecords());
        userVOPage.setRecords(userVOList);
        return ResultUtils.success(userVOPage);
    }
}
