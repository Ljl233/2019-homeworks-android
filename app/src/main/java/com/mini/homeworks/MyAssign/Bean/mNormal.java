package com.mini.homeworks.MyAssign.Bean;

import org.litepal.crud.LitePalSupport;

public class mNormal extends LitePalSupport {
    private String siteId;
    private int status;
    private String assignName;
    private long beginTime;
    private long endTime;
    private int color;
    private String assignId;
    public  void setColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public void setAssignName(String assignName) {
        this.assignName = assignName;
    }

    public int getStatus() {
        return status;
    }

    public String getAssignName() {
        return assignName;
    }

    public String getSiteId() {
        return siteId;
    }

    public long getEndTime() {
        return endTime;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public String getAssignId() {
        return assignId;
    }

    public void setAssignId(String assignId) {
        this.assignId = assignId;
    }
}