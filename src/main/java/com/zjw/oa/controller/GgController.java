package com.zjw.oa.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.zjw.oa.entity.Gsgg;
import com.zjw.oa.entity.Xmjd;
import com.zjw.oa.service.GgService;
import com.zjw.oa.util.JsonUtil;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description gongsigonggao-controller
 *
 * @author dddz97
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
