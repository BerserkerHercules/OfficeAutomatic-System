package com.zjw.oa.entity;


import java.util.Date;

public class Rz {

  private int rzId;
  private String rdnr;
  private Date rzTime;
  private int userId;
  private String userName;


  public int getRzId() {
    return rzId;
  }

  public void setRzId(int rzId) {
    this.rzId = rzId;
  }


  public String getRdnr() {
    return rdnr;
  }

  public void setRdnr(String rdnr) {
    this.rdnr = rdnr;
  }


  public Date getRzTime() {
    return rzTime;
  }

  public void setRzTime(Date rzTime) {
    this.rzTime = rzTime;
  }


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

}
