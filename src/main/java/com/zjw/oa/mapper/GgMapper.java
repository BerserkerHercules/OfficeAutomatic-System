package com.zjw.oa.mapper;

import com.zjw.oa.entity.Gsgg;
import com.zjw.oa.entity.Xmjd;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Description
 *
 * @author ZhengJiawei
 * @date 2019-03-21 10:46:50
 */
@Mapper
public interface GgMapper {

    /**
     * Description 公告
     * @return User
     * @author ZhengJiawei
     * @date 2019-03-20 16:29:47
     */
    List<Gsgg> getGgList();

    List<Xmjd> getxmList();

}
