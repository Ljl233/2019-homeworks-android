package com.mini.homeworks.net.bean;

import java.util.List;

public class NotificationBean {
    //弹窗提醒
    public static class AssignmentsGet{

        /**
         * msg : String
         * cookie : String
         * realName : String
         * userName : String
         * total : Int
         * data : [{"courseName":"String","assignName":"String","teacher":"String","siteId":"String","assignId":"String","beginTime":"Int","endTime":"Int"}]
         */

        private String msg;
        private String cookie;
        private String realName;
        private String userName;
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

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
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
             * courseName : String
             * assignName : String
             * teacher : String
             * siteId : String
             * assignId : String
             * beginTime : Int
             * endTime : Int
             */

            private String courseName;
            private String assignName;
            private String teacher;
            private String siteId;
            private String assignId;
            private int beginTime;
            private int endTime;

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

            public String getSiteId() {
                return siteId;
            }

            public void setSiteId(String siteId) {
                this.siteId = siteId;
            }

            public String getAssignId() {
                return assignId;
            }

            public void setAssignId(String assignId) {
                this.assignId = assignId;
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
        }
    }
    //邮件提醒启用状态更改
    public static class MailIsSendModify {

        /**
         * msg : String
         * isSend : Boolean
         */

        private String msg;
        private boolean isSend;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public boolean getIsSend() {
            return isSend;
        }

        public void setIsSend(boolean isSend) {
            this.isSend = isSend;
        }
    }

    //添加时间节点
    public static class NoticeTimeAdd {

        /**
         * msg : String
         * noticeTimeId : String
         */

        private String msg;
        private String noticeTimeId;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getNoticeTimeId() {
            return noticeTimeId;
        }

        public void setNoticeTimeId(String noticeTimeId) {
            this.noticeTimeId = noticeTimeId;
        }
    }

    //获取全部时间节点及邮箱通知设置
    public static class NoticeConfigGet {

        /**
         * msg : String
         * isSend : Boolean
         * noticeTimeList : [{"noticeTime":"Int","noticeTimeId":"String","noticeTimeStatus":"Int"}]
         * total : Int
         */

        private String msg;
        private boolean isSend;
        private int total;
        private List<NoticeTimeListBean> noticeTimeList;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public boolean getIsSend() {
            return isSend;
        }

        public void setIsSend(boolean isSend) {
            this.isSend = isSend;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<NoticeTimeListBean> getNoticeTimeList() {
            return noticeTimeList;
        }

        public void setNoticeTimeList(List<NoticeTimeListBean> noticeTimeList) {
            this.noticeTimeList = noticeTimeList;
        }

        public static class NoticeTimeListBean {
            /**
             * noticeTime : Int
             * noticeTimeId : String
             * noticeTimeStatus : Int
             */

            private int noticeTime;
            private String noticeTimeId;
            private int noticeTimeStatus;

            public int getNoticeTime() {
                return noticeTime;
            }

            public void setNoticeTime(int noticeTime) {
                this.noticeTime = noticeTime;
            }

            public String getNoticeTimeId() {
                return noticeTimeId;
            }

            public void setNoticeTimeId(String noticeTimeId) {
                this.noticeTimeId = noticeTimeId;
            }

            public int getNoticeTimeStatus() {
                return noticeTimeStatus;
            }

            public void setNoticeTimeStatus(int noticeTimeStatus) {
                this.noticeTimeStatus = noticeTimeStatus;
            }
        }
    }

    //修改时间节点
    public static class ModifyChange {

        /**
         * msg : String
         * noticeTimeId : String
         */

        private String msg;
        private String noticeTimeId;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getNoticeTimeId() {
            return noticeTimeId;
        }

        public void setNoticeTimeId(String noticeTimeId) {
            this.noticeTimeId = noticeTimeId;
        }
    }

    //改变时间节点启用状态
    public static class ChangesStatusBean {

        /**
         * msg : String
         * statusMessage : String
         */

        private String msg;
        private String statusMessage;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getStatusMessage() {
            return statusMessage;
        }

        public void setStatusMessage(String statusMessage) {
            this.statusMessage = statusMessage;
        }
    }
    //移除时间节点
    public static class NoticeTimeDeleteBean{

        /**
         * msg : String
         */

        private String msg;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
