package com.mini.homeworks.net.bean;

import java.util.List;

public class TasksBean {

    /**
     * msg : String
     * cookie : String
     * total : Int
     * assignList : [{"siteId":"String","status":"Int","courseName":"String","assignName":"String","teacher":"String","beginTime":"Int","endTime":"Int","assignId":"String"}]
     */

    private String msg;
    private String cookie;
    private int total;
    private List<AssignListBean> assignList;

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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<AssignListBean> getAssignList() {
        return assignList;
    }

    public void setAssignList(List<AssignListBean> assignList) {
        this.assignList = assignList;
    }

    public static class AssignListBean {
        /**
         * siteId : String
         * status : Int
         * courseName : String
         * assignName : String
         * teacher : String
         * beginTime : Int
         * endTime : Int
         * assignId : String
         */

        private String siteId;
        private int status;
        private String courseName;
        private String assignName;
        private String teacher;
        private int beginTime;
        private int endTime;
        private String assignId;

        public String getSiteId() {
            return siteId;
        }

        public void setSiteId(String siteId) {
            this.siteId = siteId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public String getAssignName() {
            return assignName;
        }

        public void setAssignName(String assignName) {
            this.assignName = assignName;
        }

        public String getTeacher() {
            return teacher;
        }

        public void setTeacher(String teacher) {
            this.teacher = teacher;
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
