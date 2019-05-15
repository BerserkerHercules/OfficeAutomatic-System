package com.zjw.oa.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zjw.oa.entity.Gsgg;
import com.zjw.oa.entity.User;
import com.zjw.oa.entity.Xmjd;
import com.zjw.oa.service.GgService;
import com.zjw.oa.service.UserService;
import com.zjw.oa.util.JsonUtil;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
@RequestMapping(value = "/gsxx")
public class GgController {

    @Resource
    private GgService ggService;

    /**
     * 公告
     * @return
     */
    @RequestMapping(value = "/gsgg")
    @ResponseBody
    @CrossOrigin
    public JSONArray getGgList() {
        List<Gsgg> list = ggService.getGgList();
        String jsonStr = JsonUtil.serializeDate(list);
        return JSON.parseArray(jsonStr);
    }

    /**
     * 项目进度
     * @return
     */
    @RequestMapping(value = "/xmjd")
    @ResponseBody
    @CrossOrigin
    public JSONArray getXmList() {
        List<Xmjd> list = ggService.getxmList();
        String jsonStr = JsonUtil.serializeDate(list);
        return JSON.parseArray(jsonStr);
    }

}
