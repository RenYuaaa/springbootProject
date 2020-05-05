package com.ren.springboot.service;

import com.ren.springboot.entity.vo.UserVo;

import java.util.List;

/**
 * 用户操作接口
 */
public interface UserService {

    List<UserVo> login(String userName);
}
