package com.zjw.oa.entity;


public class Rw {

  private long rwId;
  private String rwmc;
  private String rwms;
  private java.sql.Timestamp rwTime;
  private long jsUserId;
  private long fbUserId;
  private String jsUserName;
  private String fbUserName;
  private int jjcd;


  public long getRwId() {
    return rwId;
  }

  public void setRwId(long rwId) {
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


  public java.sql.Timestamp getRwTime() {
    return rwTime;
  }

  public void setRwTime(java.sql.Timestamp rwTime) {
    this.rwTime = rwTime;
  }


  public long getJsUserId() {
    return jsUserId;
  }

  public void setJsUserId(long jsUserId) {
    this.jsUserId = jsUserId;
  }


  public long getFbUserId() {
    return fbUserId;
  }

  public void setFbUserId(long fbUserId) {
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
}
