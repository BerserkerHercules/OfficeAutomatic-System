package com.zjw.oa.mapper;

import com.zjw.oa.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Description
 *
 * @author ZhengJiawei
 * @date 2019-03-21 10:46:50
 */
@Mapper
public interface UserMapper {

    /**
     * Description 登录
     *
     * @param user 用户
     * @return User
     * @author ZhengJiawei
     * @date 2019-03-20 16:29:47
     */
    User userLogin(User user);

    /**
     * Description 获取用户列表
     *
     * @return List<User>
     * @author ZhengJiawei
     * @date 2019-03-22 09:24:53
     */
    List<User> getUserList();

}
