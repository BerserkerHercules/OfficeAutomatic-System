package com.yzy.pe.service.impl;

import com.yzy.pe.mapper.UserMapper;
import com.yzy.pe.entity.User;
import com.yzy.pe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description
 *
 * @author ZhengJiawei
 * @date 2019-03-21 10:47:40
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        User user1 = userMapper.userLogin(user);
        return user1;
    }

    @Override
    public List<User> getUserList() {
        return userMapper.getUserList();
    }

}
