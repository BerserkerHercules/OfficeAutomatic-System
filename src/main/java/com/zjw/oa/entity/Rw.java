package com.zjw.oa.entity;


import java.sql.Date;

public class Rw {

  private int rwId;
  private String rwmc;
  private String rwms;
  private Date rwTime;
  private int jsUserId;
  private int fbUserId;
  private String jsUserName;
  private String fbUserName;
  private int jjcd;
  private int isComplete;


  public int getRwId() {
    return rwId;
  }

  public void setRwId(int rwId) {
    this.rwId = rwId;
  }


  public String getRwmc() {
    return rwmc;
  }

  public void setRwmc(String rwmc) {
    this.rwmc = rwmc;
  }


  public String getRwms() {
    return rwms;
  }

  public void setRwms(String rwms) {
    this.rwms = rwms;
  }


  public Date getRwTime() {
    return rwTime;
  }

  public void setRwTime(Date rwTime) {
    this.rwTime = rwTime;
  }


  public int getJsUserId() {
    return jsUserId;
  }

  public void setJsUserId(int jsUserId) {
    this.jsUserId = jsUserId;
  }


  public int getFbUserId() {
    return fbUserId;
  }

  public void setFbUserId(int fbUserId) {
    this.fbUserId = fbUserId;
  }


  public String getJsUserName() {
    return jsUserName;
  }

  public void setJsUserName(String jsUserName) {
    this.jsUserName = jsUserName;
  }


  public String getFbUserName() {
    return fbUserName;
  }

  public void setFbUserName(String fbUserName) {
    this.fbUserName = fbUserName;
  }

  public int getJjcd() {
    return jjcd;
  }

  public void setJjcd(int jjcd) {
    this.jjcd = jjcd;
  }

  public int getIsComplete() {
    return isComplete;
  }

  public void setIsComplete(int isComplete) {
    this.isComplete = isComplete;
  }
}
