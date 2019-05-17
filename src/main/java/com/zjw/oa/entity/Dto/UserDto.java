package com.zjw.oa.entity.Dto;



import com.zjw.oa.entity.User;

import java.util.Date;

public class UserDto extends User {
    private Date dkTime;

    public Date getDkTime() {
        return dkTime;
    }

    public void setDkTime(Date dkTime) {
        this.dkTime = dkTime;
    }

}
