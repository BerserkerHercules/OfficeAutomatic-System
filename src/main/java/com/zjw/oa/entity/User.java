package com.zjw.oa.entity;


public class User {

  private int userId;
  private String userName;
  private String nikeName;
  private String pwd;
  private String zw;
  private int permission;


  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
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


  public int getPermission() {
    return permission;
  }

  public void setPermission(int permission) {
    this.permission = permission;
  }

}
