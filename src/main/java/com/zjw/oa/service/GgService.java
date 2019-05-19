package com.zjw.oa.service;

import com.zjw.oa.entity.Gsgg;
import com.zjw.oa.entity.Xmjd;

import java.util.List;

/**
 * Description 用户service层
 *
 * @author ZhengJiawei
 * @date 2019-03-21 10:47:26
 */
public interface GgService {


    List<Gsgg> getGgList();

    List<Xmjd> getxmList();

}
