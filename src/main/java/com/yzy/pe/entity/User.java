package com.yzy.pe.entity;


/**
 * 用户实体类
 *
 * @author ZhengJiawei
 * @create 2019-02-13 11:13
 */
public class User {

    private String userId;

    private String userName;

    private String pwd;

    private String permissionDegree;

    private String qdbm;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPermissionDegree() {
        return permissionDegree;
    }

    public void setPermissionDegree(String permissionDegree) {
        this.permissionDegree = permissionDegree;
    }

    public String getQdbm() {
        return qdbm;
    }

    public void setQdbm(String qdbm) {
        this.qdbm = qdbm;
    }

}