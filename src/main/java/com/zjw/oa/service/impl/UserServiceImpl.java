package com.zjw.oa.service.impl;

import com.zjw.oa.entity.Dk;
import com.zjw.oa.entity.Dto.UserDto;
import com.zjw.oa.entity.Hys;
import com.zjw.oa.mapper.UserMapper;
import com.zjw.oa.entity.User;
import com.zjw.oa.service.UserService;
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
    public List<User> getUserList(User user) {
        return userMapper.getUserList(user);
    }

    @Override
    public void addDk(Dk dk) throws Exception {
        userMapper.addDk(dk);
    }

    @Override
    public List<UserDto> getDkList(UserDto userDto) {
        return userMapper.getDkList(userDto);
    }

    @Override
    public List<Hys> hysList() {
        return userMapper.hysList();
    }

    @Override
    public void updateHys(Hys hys) throws Exception {
        userMapper.updateHys(hys);
    }

    @Override
    public User getUser(User user) {
        return userMapper.getUser(user);
    }

}
