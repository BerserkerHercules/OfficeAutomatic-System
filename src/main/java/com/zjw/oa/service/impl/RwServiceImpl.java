package com.zjw.oa.service.impl;

import com.zjw.oa.entity.Rw;
import com.zjw.oa.entity.Rz;
import com.zjw.oa.entity.User;
import com.zjw.oa.mapper.RwMapper;
import com.zjw.oa.mapper.UserMapper;
import com.zjw.oa.service.RwService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description
 *
 * @author dddz97
 * @date 2019-03-21 10:47:40
 */
@Service
public class RwServiceImpl implements RwService {

    @Autowired
    private RwMapper rwMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Rw> myRw(Rw rw) {

        List<Rw> list = rwMapper.myRw(rw);
        list.forEach(e->{
            User u = new User();
            u.setUserId(e.getFbUserId());
            e.setFbUserName(userMapper.getUser(u).getUserName());
        });
        return list;
    }

    @Override
    public Rw rwXq(Rw rw) {
        return rwMapper.rwXq(rw);
    }

    @Override
    public void delRw(Rw rw) throws Exception {
        rwMapper.delRw(rw);
    }

    @Override
    public void updateRw(Rw rw) throws Exception {
        rwMapper.updateRw(rw);
    }

    @Override
    public void addRw(Rw rw) throws Exception {
        rwMapper.addRw(rw);
    }

    @Override
    public List<Rz> rzList(String rz) {
        return rwMapper.rzList(rz);
    }

    @Override
    public void addRz(Rz rz) throws Exception {
        rwMapper.addRz(rz);
    }
}
