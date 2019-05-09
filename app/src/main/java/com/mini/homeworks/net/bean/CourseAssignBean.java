package com.mini.homeworks.net.bean;

import java.util.List;

public class CourseAssignBean {


    /**
     * msg : String
     * cookie : String
     * siteId : String
     * total : Int
     * data : [{"status":"Int","assignName":"Int","beginTime":"Int","endTime":"String","assignId":"String"}]
     */

    private String msg;
    private String cookie;
    private String siteId;
    private int total;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * status : Int
         * assignName : Int
         * beginTime : Int
         * endTime : Int
         * assignId : String
         */

        private int status;
        private String assignName;
        private int beginTime;
        private int endTime;
        private String assignId;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getAssignName() {
            return assignName;
        }

        public void setAssignName(String assignName) {
            this.assignName = assignName;
        }

        public int getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(int beginTime) {
            this.beginTime = beginTime;
        }

        public int getEndTime() {
            return endTime;
        }

        public void setEndTime(int endTime) {
            this.endTime = endTime;
        }

        public String getAssignId() {
            return assignId;
        }

        public void setAssignId(String assignId) {
            this.assignId = assignId;
        }
    }

}
