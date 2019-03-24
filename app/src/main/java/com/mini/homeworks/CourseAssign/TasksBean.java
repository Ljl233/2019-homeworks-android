package com.mini.homeworks.CourseAssign;

import java.util.List;

public class TasksBean {


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
    private String total;
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

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
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
         * endTime : String
         * assignId : String
         */

        private String status;
        private String assignName;
        private String beginTime;
        private String endTime;
        private String assignId;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getAssignName() {
            return assignName;
        }

        public void setAssignName(String assignName) {
            this.assignName = assignName;
        }

        public String getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(String beginTime) {
            this.beginTime = beginTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
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
