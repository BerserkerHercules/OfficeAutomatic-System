package com.zjw.oa.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zjw.oa.entity.Rw;
import com.zjw.oa.service.RwService;
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
@RequestMapping(value = "/rwxx")
public class RwController {

    @Resource
    private RwService rwService;


    /**
     * 查看自己的工作任务 - 列表
     * @param rw
     * @return
     */
    @RequestMapping(value = "/myRw")
    @ResponseBody
    @CrossOrigin
    public JSONArray myRw(@RequestBody Rw rw) {
        List<Rw> list = rwService.myRw(rw);
        String jsonStr = JsonUtil.serializeDate(list);
        return JSON.parseArray(jsonStr);
    }

    /**
     * 查看自己的工作任务 - 详情
     * @param rw
     * @return
     */
    @RequestMapping(value = "/rwXq")
    @ResponseBody
    @CrossOrigin
    public JSONObject rwXq(@RequestBody Rw rw) {
        Rw rw1 = rwService.rwXq(rw);
        String jsonStr = JsonUtil.serialize(rw1);
        return JSON.parseObject(jsonStr);
    }

    /**
     * 删除任务 - 权限
     * @param rw
     * @return
     */
    @RequestMapping(value = "/delRw")
    @ResponseBody
    @CrossOrigin
    public JSONObject delRw(@RequestBody Rw rw) {
        try{
            rwService.delRw(rw);
        }catch (Exception e){
            return JSON.parseObject("{success:false,msg:\"删除任务失败！\"}");
        }
        return JSON.parseObject("{success:true,msg:\"删除任务成功！\"}");
    }

    /**
     * 更新任务 - 权限
     * @param rw
     * @return
     */
    @RequestMapping(value = "/updateRw")
    @ResponseBody
    @CrossOrigin
    public JSONObject updateRw(@RequestBody Rw rw) {
        try{
            rwService.updateRw(rw);
        }catch (Exception e){
            return JSON.parseObject("{success:false,msg:\"编辑任务失败！\"}");
        }
        return JSON.parseObject("{success:true,msg:\"编辑任务成功！\"}");
    }

}
