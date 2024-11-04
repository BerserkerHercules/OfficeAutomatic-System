package com.zjw.oa.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zjw.oa.entity.Qjsq;
import com.zjw.oa.service.QjsqService;
import com.zjw.oa.util.JsonUtil;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description
 *
 * @author dddz97
 * @date 2019-03-21 10:49:24
 */
@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/qjsq")
public class QjsqController {
    @Resource
    private QjsqService qjsqService;

    /**
     * 向上级申请请假（原因，时间，是否被批准）；
     * @param qjsq
     * @return
     */
    @RequestMapping(value = "/addQjsq")
    @CrossOrigin
    public JSONObject addQj(@RequestBody Qjsq qjsq) {
        System.out.println(qjsq.toString());
          if( qjsqService.addQjsq(qjsq)==1){
              return JSON.parseObject("{success:true,msg:\"提交成功！\"}");
          }else {
              return JSON.parseObject("{success:false,msg:\"提交失败！\"}");
          }
    }

    /**
     * 请假列表
     * @param qjsq
     * @return
     */
    @RequestMapping(value = "/getQjList")
    @CrossOrigin
    public JSONArray getQjList(@RequestBody Qjsq qjsq) {
        List<Qjsq> list = qjsqService.getQjList(qjsq);
        String jsonStr = JsonUtil.serializeDate(list);
        return JSON.parseArray(jsonStr);
    }

    /**
     * 请假详情
     * @param qjsq
     * @return
     */
    @RequestMapping(value = "/getQjXx")
    @CrossOrigin
    public JSONObject getQjXx(@RequestBody Qjsq qjsq) {
        Qjsq qjsq1 = qjsqService.getQj(qjsq);
        String jsonStr = JsonUtil.serialize(qjsq1);
        return JSON.parseObject(jsonStr);
    }

    /**
     * 批准下级员工的请假申请；（权限）
     * @param qjsq
     * @return
     */
    @RequestMapping(value = "/agreeQj")
    @CrossOrigin
    public JSONObject agreeQj(@RequestBody Qjsq qjsq) {
        try{
            qjsqService.agreeQj(qjsq);
        }catch (Exception e){
            return JSON.parseObject("{success:false,msg:\"审批失败！\"}");
        }
        return JSON.parseObject("{success:true,msg:\"审批成功！\"}");
    }

}
