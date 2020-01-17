package com.zjw.oa.mapper;


import com.zjw.oa.entity.Qjsq;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Description 请假申请MAPPER
 *
 * @author dddz97
 * @date 2019-03-21 10:46:50
 */
@Mapper
public interface QjsqMapper {

    void addQjsq(Qjsq qjsq) throws Exception;

    List<Qjsq> getQjList(Qjsq qjsq);

    Qjsq getQj(Qjsq qjsq);

    void agreeQj(Qjsq qjsq) throws Exception;

}
