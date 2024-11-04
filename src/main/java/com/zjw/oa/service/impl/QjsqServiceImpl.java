package com.zjw.oa.service.impl;


import com.zjw.oa.entity.Qjsq;
import com.zjw.oa.entity.User;
import com.zjw.oa.mapper.QjsqMapper;
import com.zjw.oa.mapper.UserMapper;
import com.zjw.oa.service.QjsqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description
 *
 * @author dddz97
 * @date 2019-03-21 10:47:40
 */
@Service
public class QjsqServiceImpl implements QjsqService {

    @Resource
    private QjsqMapper qjsqMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer addQjsq(Qjsq qjsq) {
        if (qjsq.getQjyy() == null || qjsq.getKsTime() == null || qjsq.getJsTime() == null || qjsq.getUserId() == 0) {
            return 0;
        } else {
            qjsqMapper.addQjsq(qjsq);
            return 1;
        }
    }


    @Override
    public List<Qjsq> getQjList(Qjsq qjsq) {
        User u = new User();
        u.setUserId(qjsq.getUserId());
        User user = userMapper.getUser(u);
        if (user.getPermission() == 1) {
            Qjsq q = new Qjsq();
            return qjsqMapper.getQjList(q);
        }
        return qjsqMapper.getQjList(qjsq);
    }

    @Override
    public Qjsq getQj(Qjsq qjsq) {
        return qjsqMapper.getQj(qjsq);
    }

    @Override
    public void agreeQj(Qjsq qjsq) throws Exception {
        if (qjsq.getQjId()==0 || qjsq.getQjzt()==null){
            throw new Exception();
        }else {
            qjsqMapper.agreeQj(qjsq);
        }
    }
}
