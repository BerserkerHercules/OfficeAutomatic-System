package com.zjw.oa.entity;


import java.util.Date;

public class Qjsq {

  private int qjId;
  private String qjyy;
  private String qjms;
  private Date ksTime;
  private Date jsTime;
  private String qjzt;
  private int userId;


  public int getQjId() {
    return qjId;
  }

  public void setQjId(int qjId) {
    this.qjId = qjId;
  }


  public String getQjyy() {
    return qjyy;
  }

  public void setQjyy(String qjyy) {
    this.qjyy = qjyy;
  }


  public String getQjms() {
    return qjms;
  }

  public void setQjms(String qjms) {
    this.qjms = qjms;
  }


  public Date getKsTime() {
    return ksTime;
  }

  public void setKsTime(Date ksTime) {
    this.ksTime = ksTime;
  }


  public Date getJsTime() {
    return jsTime;
  }

  public void setJsTime(Date jsTime) {
    this.jsTime = jsTime;
  }


  public String getQjzt() {
    return qjzt;
  }

  public void setQjzt(String qjzt) {
    this.qjzt = qjzt;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }
}
