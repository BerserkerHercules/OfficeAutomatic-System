package com.zjw.oa.entity;

/**
* gongsi gonggao 实体类
**/
public class Gsgg {

  private int ggId;
  private String ggTitle;
  private String ggNr;
  private Date  ggTime;
  private int isZs;


  public int getGgId() {
    return ggId;
  }

  public void setGgId(int ggId) {
    this.ggId = ggId;
  }


  public String getGgTitle() {
    return ggTitle;
  }

  public void setGgTitle(String ggTitle) {
    this.ggTitle = ggTitle;
  }


  public String getGgNr() {
    return ggNr;
  }

  public void setGgNr(String ggNr) {
    this.ggNr = ggNr;
  }


  public Date  getGgTime() {
    return ggTime;
  }

  public void setGgTime(Date  ggTime) {
    this.ggTime = ggTime;
  }


  public int getIsZs() {
    return isZs;
  }

  public void setIsZs(int isZs) {
    this.isZs = isZs;
  }

}
