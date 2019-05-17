package com.zjw.oa.service;

import com.zjw.oa.entity.Dk;
import com.zjw.oa.entity.Dto.UserDto;
import com.zjw.oa.entity.User;

import java.util.List;

/**
 * Description 用户service层
 *
 * @author ZhengJiawei
 * @date 2019-03-21 10:47:26
 */
public interface UserService {

    User login(User user);

    List<User> getUserList(User user);

    void addDk(Dk dk) throws Exception;

    List<UserDto> getDkList(UserDto userDto);

}
