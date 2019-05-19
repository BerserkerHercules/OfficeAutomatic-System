package com.zjw.oa.service.impl;

import com.zjw.oa.entity.Rw;
import com.zjw.oa.entity.Rz;
import com.zjw.oa.mapper.RwMapper;
import com.zjw.oa.service.RwService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description
 *
 * @author ZhengJiawei
 * @date 2019-03-21 10:47:40
 */
@Service
public class RwServiceImpl implements RwService {

    @Autowired
    private RwMapper rwMapper;

    @Override
    public List<Rw> myRw(Rw rw) {
        return rwMapper.myRw(rw);
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
    public List<Rz> rzList(Rz rz) {
        return rwMapper.rzList(rz);
    }

    @Override
    public void addRz(Rz rz) throws Exception {
        rwMapper.addRz(rz);
    }
}
