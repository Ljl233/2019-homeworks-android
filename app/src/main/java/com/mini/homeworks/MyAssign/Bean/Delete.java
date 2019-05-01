package com.mini.homeworks.MyAssign.Bean;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

public class Delete extends LitePalSupport {
    private String siteId;
    private int status;
    private String assignName;
    private int beginTime;
    private int endTime;
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

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public void setBeginTime(int beginTime) {
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

    public int getEndTime() {
        return endTime;
    }

    public int getBeginTime() {
        return beginTime;
    }

    public String getAssignId() {
        return assignId;
    }

    public void setAssignId(String assignId) {
        this.assignId = assignId;
    }

}