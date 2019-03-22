package com.zjw.oa.service;

import com.zjw.oa.entity.User;

import java.util.List;

/**
 * Description 用户service层
 *
 * @author ZhengJiawei
 * @date 2019-03-21 10:47:26
 */
public interface UserService {

    /**
     * Description 登录
     *
     * @param user 用户输入数据
     * @return User
     * @author ZhengJiawei
     * @date 2019-03-22 14:14:27
     */
    User login(User user);

    /**
     * Description
     *
     * @return List<User>
     * @author ZhengJiawei
     * @date 2019-03-22 14:15:14
     */
    List<User> getUserList();

}
