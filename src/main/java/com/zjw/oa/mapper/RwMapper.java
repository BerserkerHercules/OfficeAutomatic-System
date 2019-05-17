package com.zjw.oa.mapper;


import com.zjw.oa.entity.Rw;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Description
 *
 * @author ZhengJiawei
 * @date 2019-03-21 10:46:50
 */
@Mapper
public interface RwMapper {

    List<Rw> myRw(Rw rw);

    Rw rwXq(Rw rw);

    void delRw(Rw rw)throws Exception;

    void updateRw(Rw rw)throws Exception;

    void addRw(Rw rw)throws Exception;

}
