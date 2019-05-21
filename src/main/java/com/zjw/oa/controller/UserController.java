package com.zjw.oa.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zjw.oa.entity.Dk;
import com.zjw.oa.entity.Dto.UserDto;
import com.zjw.oa.entity.User;
import com.zjw.oa.service.UserService;
import com.zjw.oa.util.JsonUtil;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description
 *
 * @author ZhengJiawei
 * @date 2019-03-21 10:49:24
 */
@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * Description 登录，可以加入@CrossOrigin支持跨域。
     *
     * @param user 用户输入的学号和密码
     * @return Result
     * @author ZhengJiawei
     * @date 2019-03-21 09:06:08
     */
    @RequestMapping(value = "/login")
    @ResponseBody
    @CrossOrigin
    public JSONObject login(@RequestBody User user) {
        User user1 = userService.login(user);
        if (user1 != null&&user1.getPermission()==1) {
            return JSON.parseObject("{statusCode:200,user:{isAdmin:false,userName:\""+user1.getUserName()+"\"," +
                    "zw:\"" + user1.getZw() + "\"," +
                    "userId:\"" + user1.getUserId() + "\"," +
                    "permission:" + user1.getPermission() + "}}");
        }else if(user1 != null){
            return JSON.parseObject("{statusCode:200,user:{isAdmin:false,userName:\""+user1.getUserName()+"\"," +
                    "userId:\"" + user1.getUserId() + "\"," +
                    "zw:\"" + user1.getZw() + "\"," +
                    "permission:" + user1.getPermission() + "}}");
        }
        return JSON.parseObject("{statusCode:404}");
    }

    /**
     * Description 获取用户列表
     *
     * @author ZhengJiawei
     * @date 2019-03-22 10:50:47
     */
    @RequestMapping(value = "/userList")
    @ResponseBody
    @CrossOrigin
    public JSONArray getUserList(@RequestBody(required=false) User user) {
        List<User> list = userService.getUserList(user);
        String jsonStr = JsonUtil.serializeDate(list);
        return JSON.parseArray(jsonStr);
    }

    @RequestMapping(value = "/addDk")
    @ResponseBody
    @CrossOrigin
    public JSONObject addDk(@RequestBody Dk dk) {
        try{
            userService.addDk(dk);
        }catch (Exception e){
            return JSON.parseObject("{success:false,msg:\"打卡失败！\"}");
        }
        return JSON.parseObject("{success:true,msg:\"打卡成功！\"}");
    }

    @RequestMapping(value = "/getDkList")
    @ResponseBody
    @CrossOrigin
    public JSONArray getDkList(@RequestBody UserDto userDto) {
        List<UserDto> list = userService.getDkList(userDto);
        String jsonStr = JsonUtil.serializeDate(list);
        return JSON.parseArray(jsonStr);
    }

}
