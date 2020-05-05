package com.ren.springboot.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "角色对象")
public class Role {

    @ApiModelProperty(value = "主键id", name = "id")
    private Integer id;

    @ApiModelProperty(value = "角色名称", name = "releName")
    private String roleName;

    @ApiModelProperty(value = "权限", name = "permission")
    private Permission permission;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }
}
