package com.ren.springboot.controller;

import com.ren.springboot.common.CommonStatus;
import com.ren.springboot.common.Result;
import com.ren.springboot.entity.User;
import com.ren.springboot.service.UserService;
import com.ren.springboot.utils.JWTUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Api(description = "用户操作")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/userLogin")
    @ApiOperation(value = "用户登录")
    public Result userLogin(@RequestBody User user) {
        Boolean result = userService.userLogin(user);
        if (result == true) {
            String token = JWTUtil.generateToken(user.getUserName(), user.getPassword());
            return new Result(CommonStatus.SUCCESS, token);
        }
        return new Result(CommonStatus.PASSWORD_ERROR);
    }
}
