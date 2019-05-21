package com.zjw.oa.entity;


public class User {

  private long userId;
  private String userName;
  private String nikeName;
  private String pwd;
  private String zw;
  private long permission;
  private long sjUserId;
  private String phone;


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public String getNikeName() {
    return nikeName;
  }

  public void setNikeName(String nikeName) {
    this.nikeName = nikeName;
  }


  public String getPwd() {
    return pwd;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }


  public String getZw() {
    return zw;
  }

  public void setZw(String zw) {
    this.zw = zw;
  }


  public long getPermission() {
    return permission;
  }

  public void setPermission(long permission) {
    this.permission = permission;
  }


  public long getSjUserId() {
    return sjUserId;
  }

  public void setSjUserId(long sjUserId) {
    this.sjUserId = sjUserId;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }
}
