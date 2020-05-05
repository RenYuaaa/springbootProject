package com.ren.springboot.controller;

import com.ren.springboot.common.CommonStatus;
import com.ren.springboot.common.Result;
import com.ren.springboot.entity.vo.UserVo;
import com.ren.springboot.service.UserService;
import com.ren.springboot.utils.JWTUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;


@RestController
@Api(description = "用户操作")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "用户登录")
    @GetMapping("/user/login")
    public Result userLogin(@ApiParam(value = "用户名") @RequestParam(value = "userName") @NotNull(message = "用户名不能为空") String userName,
                            @ApiParam(value = "用户密码") @RequestParam(value = "password") @NotNull(message = "密码不能为空") String password) {


        List<UserVo> users = userService.login(userName);
        if (Objects.isNull(users) || users.isEmpty()) {
            return new Result<>(CommonStatus.USER_NOT_EXIST);
        }

        if (users.get(0).getPassword().equals(password)) {
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(users.get(0), userVo);
            userVo.setToken(JWTUtil.sign(userName, password));
            return new Result<>(CommonStatus.SUCCESS, userVo);
        } else {
            return new Result<>(CommonStatus.PASSWORD_ERROR);
        }
    }

    @ApiOperation(value = "测试shiro")
    @GetMapping("/shiro")
    @RequiresAuthentication
    public Result shiroTest() {
        return new Result(CommonStatus.SUCCESS);
    }

}
