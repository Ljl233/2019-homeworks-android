package com.mini.homeworks.net.bean;

import java.util.List;

public class SearchBean {

    /**
     * msg : String
     * cookie : String
     * total : Int
     * courseData : [{"courseName":"String","siteId":"String"}]
     * assignData : [{"assignId":"String","siteId":"String","assignName":"String","courseName":"String"}]
     * contentData : [{"assignId":"String","siteId":"String","assignName":"String","courseName":"String"}]
     */

    private String msg;
    private String cookie;
    private int total;
    private List<CourseDataBean> courseData;
    private List<AssignDataBean> assignData;
    private List<ContentDataBean> contentData;

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

    public List<CourseDataBean> getCourseData() {
        return courseData;
    }

    public void setCourseData(List<CourseDataBean> courseData) {
        this.courseData = courseData;
    }

    public List<AssignDataBean> getAssignData() {
        return assignData;
    }

    public void setAssignData(List<AssignDataBean> assignData) {
        this.assignData = assignData;
    }

    public List<ContentDataBean> getContentData() {
        return contentData;
    }

    public void setContentData(List<ContentDataBean> contentData) {
        this.contentData = contentData;
    }

    public static class CourseDataBean {
        /**
         * courseName : String
         * siteId : String
         */

        private String courseName;
        private String siteId;

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public String getSiteId() {
            return siteId;
        }

        public void setSiteId(String siteId) {
            this.siteId = siteId;
        }
    }

    public static class AssignDataBean {
        /**
         * assignId : String
         * siteId : String
         * assignName : String
         * courseName : String
         */

        private String assignId;
        private String siteId;
        private String assignName;
        private String courseName;

        public String getAssignId() {
            return assignId;
        }

        public void setAssignId(String assignId) {
            this.assignId = assignId;
        }

        public String getSiteId() {
            return siteId;
        }

        public void setSiteId(String siteId) {
            this.siteId = siteId;
        }

        public String getAssignName() {
            return assignName;
        }

        public void setAssignName(String assignName) {
            this.assignName = assignName;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }
    }

    public static class ContentDataBean {
        /**
         * assignId : String
         * siteId : String
         * assignName : String
         * courseName : String
         */

        private String assignId;
        private String siteId;
        private String assignName;
        private String courseName;

        public String getAssignId() {
            return assignId;
        }

        public void setAssignId(String assignId) {
            this.assignId = assignId;
        }

        public String getSiteId() {
            return siteId;
        }

        public void setSiteId(String siteId) {
            this.siteId = siteId;
        }

        public String getAssignName() {
            return assignName;
        }

        public void setAssignName(String assignName) {
            this.assignName = assignName;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }
    }
}
