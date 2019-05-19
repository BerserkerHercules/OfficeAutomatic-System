package com.zjw.oa.service.impl;

import com.zjw.oa.entity.Gsgg;
import com.zjw.oa.entity.Xmjd;
import com.zjw.oa.mapper.GgMapper;
import com.zjw.oa.service.GgService;
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
public class GgServiceImpl implements GgService {

    @Autowired
    private GgMapper ggMapper;

    @Override
    public List<Gsgg> getGgList() {
        return ggMapper.getGgList();
    }

    @Override
    public List<Xmjd> getxmList() {
        return ggMapper.getxmList();
    }

}
