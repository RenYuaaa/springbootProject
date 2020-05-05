package com.ren.springboot.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "用户实体类")
public class User {

    @ApiModelProperty(value = "主键Id", name = "id")
    private Integer id;

    @ApiModelProperty(value = "用户名", name = "userName")
    @NotNull(message = "用户名不能为空")
    private String userName;

    @ApiModelProperty(value = "密码", name = "password")
    @NotNull(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "手机号", name = "phoneNumber")
    private Integer phoneNumber;

    @ApiModelProperty(value = "昵称", name = "nickName")
    private String nickName;

    @ApiModelProperty(value = "角色", name = "role")
    private Role role;


}
