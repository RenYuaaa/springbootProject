package com.ren.springboot.service;

import com.ren.springboot.entity.User;

/**
 * 用户操作接口
 */
public interface UserService {
    Boolean userLogin(User user);
}
