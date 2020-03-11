package com.ren.springboot.service.impl;

import com.ren.springboot.dao.UserDao;
import com.ren.springboot.entity.User;
import com.ren.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public Boolean userLogin(User user) {
        String password = userDao.userLogin(user.getUserName());
        if (!password.equals(user.getPassword())) {
            return false;
        }
        return true;
    }
}
