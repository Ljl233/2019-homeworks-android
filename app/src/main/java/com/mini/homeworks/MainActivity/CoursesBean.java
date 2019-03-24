package com.mini.homeworks.MainActivity;

import java.util.List;

public class CoursesBean {

    /**
     * msg : String
     * cookie : String
     * total : Int
     * courseList : [{"courseName":"String","teacher":"String","siteId":"String"}]
     */

    private String msg;
    private String cookie;
    private int total;
    private List<CourseListBean> courseList;

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

    public List<CourseListBean> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<CourseListBean> courseList) {
        this.courseList = courseList;
    }

    public static class CourseListBean {
        /**
         * courseName : String
         * teacher : String
         * siteId : String
         */

        private String courseName;
        private String teacher;
        private String siteId;

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public String getTeacher() {
            return teacher;
        }

        public void setTeacher(String teacher) {
            this.teacher = teacher;
        }

        public String getSiteId() {
            return siteId;
        }

        public void setSiteId(String siteId) {
            this.siteId = siteId;
        }
    }
}
