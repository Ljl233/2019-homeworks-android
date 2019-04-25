package com.mini.homeworks.MyAssign;

public class AssignBean {
    private int datatype;
    private String siteId;
    private int status;
    private String assignName;
    private int beginTime;
    private int endTime;

    public int getDatatype() {
        return datatype;
    }

    public void setDatatype(int datatype) {
        this.datatype = datatype;
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
}
