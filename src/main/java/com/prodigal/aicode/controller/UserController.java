package com.prodigal.aicode.controller;

import com.mybatisflex.core.paginate.Page;
import com.prodigal.aicode.common.BaseResult;
import com.prodigal.aicode.common.ResultUtils;
import com.prodigal.aicode.exception.ErrorCode;
import com.prodigal.aicode.exception.ThrowUtils;
import com.prodigal.aicode.model.dto.user.UserLoginDto;
import com.prodigal.aicode.model.dto.user.UserRegisterDto;
import com.prodigal.aicode.model.vo.LoginUserVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
@Tag(name = "用户管理", description = "用户管理")
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
    @GetMapping("getLoginUser")
    public BaseResult<LoginUserVO> getLoginUser(HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        LoginUserVO loginUserVO = userService.getLoginUserVO(loginUser);
        return ResultUtils.success(loginUserVO);
    }

    @PostMapping("logout")
    public BaseResult<Boolean> userLogout(HttpServletRequest request){
        ThrowUtils.throwIf(request == null, ErrorCode.PARAMS_ERROR);
        return ResultUtils.success(userService.userLogout(request));
    }
    /**
     * 保存用户。
     *
     * @param user 用户
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody User user) {
        return userService.save(user);
    }

    /**
     * 根据主键删除用户。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable Long id) {
        return userService.removeById(id);
    }

    /**
     * 根据主键更新用户。
     *
     * @param user 用户
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody User user) {
        return userService.updateById(user);
    }

    /**
     * 查询所有用户。
     *
     * @return 所有数据
     */
    @GetMapping("list")
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
    public User getInfo(@PathVariable Long id) {
        return userService.getById(id);
    }

    /**
     * 分页查询用户。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<User> page(Page<User> page) {
        return userService.page(page);
    }

}
