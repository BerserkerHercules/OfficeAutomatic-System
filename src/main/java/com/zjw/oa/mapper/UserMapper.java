package com.zjw.oa.mapper;

import com.zjw.oa.entity.Dk;
import com.zjw.oa.entity.Dto.UserDto;
import com.zjw.oa.entity.Hys;
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

    User userLogin(User user);

    List<User> getUserList(User user);

    void addDk(Dk dk) throws Exception;

    List<UserDto> getDkList(UserDto userDto);

    User getUser(User user);

    List<Hys> hysList();

    void updateHys(Hys hys)throws Exception;

}
