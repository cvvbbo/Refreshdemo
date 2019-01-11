package com.refreshdemo.test.Smartrefreashdemo;

public class MessageBean {

    private int mesId;  //消息ID
    private String mesType;
    private String mesName; //消息名称
    private String mesContent; //消息内容
    private String startTime;
    private String endTime;
    private int mark;


    public int getMesId() {
        return mesId;
    }

    public void setMesId(int mesId) {
        this.mesId = mesId;
    }

    public String getMesType() {
        return mesType;
    }

    public void setMesType(String mesType) {
        this.mesType = mesType;
    }

    public String getMesName() {
        return mesName;
    }

    public void setMesName(String mesName) {
        this.mesName = mesName;
    }

    public String getMesContent() {
        return mesContent;
    }

    public void setMesContent(String mesContent) {
        this.mesContent = mesContent;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}

