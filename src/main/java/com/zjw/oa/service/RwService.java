package com.zjw.oa.service;

import com.zjw.oa.entity.Rw;
import com.zjw.oa.entity.Rz;

import java.util.List;

/**
 * Description 用户service层
 *
 * @author dddz97
 * @date 2019-03-21 10:47:26
 */
public interface RwService {

    List<Rw> myRw(Rw rw);
    Rw rwXq(Rw rw);
    void delRw(Rw rw)throws Exception;
    void updateRw(Rw rw)throws Exception;
    void addRw(Rw rw)throws Exception;
    List<Rz> rzList(String rz);
    void addRz(Rz rz)throws Exception;
}
