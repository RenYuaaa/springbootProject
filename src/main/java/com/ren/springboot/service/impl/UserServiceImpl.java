package com.ren.springboot.service.impl;

import com.ren.springboot.dao.UserDao;
import com.ren.springboot.entity.vo.UserVo;
import com.ren.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;


    @Override
    public List<UserVo> login(String userName) {
        List<UserVo> users = userDao.selectByUserName(userName);
        return users;
    }
}
