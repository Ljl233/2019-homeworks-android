package com.mini.homeworks.net.bean;

import java.util.List;

public class DetailBean {

    /**
     * msg : String
     * cookie : String
     * siteId : String
     * assignId : String
     * courseName : String
     * assignName : String
     * status : Int
     * beginTime : Int
     * endTime : Int
     * content : String
     * pointNum : Int
     * commitNum : Int
     * isGroup : Int
     * groupNum : Int
     * studentNum : Int
     * groupPoint : Int
     * personalPoint : Int
     * feedback : String
     * assignAttachmentNum : Int
     * assignAttachment : [{"id":"String","name":"String","ext":"String","sourceUrl":"string"}]
     * submitAttachmentNum : Int
     * submitAttachment : [{"id":"String","name":"String","ext":"String","uploadTime":"Int","sourceUrl":"String"}]
     * submitContent : String
     */

    private String msg;
    private String cookie;
    private String siteId;
    private String assignId;
    private String courseName;
    private String assignName;
    private int status;
    private long beginTime;
    private long endTime;
    private String content;
    private int pointNum;
    private int commitNum;
    private int isGroup;
    private int groupNum;
    private int studentNum;
    private int groupPoint;
    private int personalPoint;
    private String feedback;
    private int assignAttachmentNum;
    private int submitAttachmentNum;
    private String submitContent;
    private List<AssignAttachmentBean> assignAttachment;
    private List<SubmitAttachmentBean> submitAttachment;

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

    public String getAssignId() {
        return assignId;
    }

    public void setAssignId(String assignId) {
        this.assignId = assignId;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPointNum() {
        return pointNum;
    }

    public void setPointNum(int pointNum) {
        this.pointNum = pointNum;
    }

    public int getCommitNum() {
        return commitNum;
    }

    public void setCommitNum(int commitNum) {
        this.commitNum = commitNum;
    }

    public int getIsGroup() {
        return isGroup;
    }

    public void setIsGroup(int isGroup) {
        this.isGroup = isGroup;
    }

    public int getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(int groupNum) {
        this.groupNum = groupNum;
    }

    public int getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(int studentNum) {
        this.studentNum = studentNum;
    }

    public int getGroupPoint() {
        return groupPoint;
    }

    public void setGroupPoint(int groupPoint) {
        this.groupPoint = groupPoint;
    }

    public int getPersonalPoint() {
        return personalPoint;
    }

    public void setPersonalPoint(int personalPoint) {
        this.personalPoint = personalPoint;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getAssignAttachmentNum() {
        return assignAttachmentNum;
    }

    public void setAssignAttachmentNum(int assignAttachmentNum) {
        this.assignAttachmentNum = assignAttachmentNum;
    }

    public int getSubmitAttachmentNum() {
        return submitAttachmentNum;
    }

    public void setSubmitAttachmentNum(int submitAttachmentNum) {
        this.submitAttachmentNum = submitAttachmentNum;
    }

    public String getSubmitContent() {
        return submitContent;
    }

    public void setSubmitContent(String submitContent) {
        this.submitContent = submitContent;
    }

    public List<AssignAttachmentBean> getAssignAttachment() {
        return assignAttachment;
    }

    public void setAssignAttachment(List<AssignAttachmentBean> assignAttachment) {
        this.assignAttachment = assignAttachment;
    }

    public List<SubmitAttachmentBean> getSubmitAttachment() {
        return submitAttachment;
    }

    public void setSubmitAttachment(List<SubmitAttachmentBean> submitAttachment) {
        this.submitAttachment = submitAttachment;
    }

    public static class AssignAttachmentBean {
        /**
         * id : String
         * name : String
         * ext : String
         * sourceUrl : string
         */

        private String id;
        private String name;
        private String ext;
        private String sourceUrl;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getExt() {
            return ext;
        }

        public void setExt(String ext) {
            this.ext = ext;
        }

        public String getSourceUrl() {
            return sourceUrl;
        }

        public void setSourceUrl(String sourceUrl) {
            this.sourceUrl = sourceUrl;
        }
    }

    public static class SubmitAttachmentBean {
        /**
         * id : String
         * name : String
         * ext : String
         * uploadTime : Int
         * sourceUrl : String
         */

        private String id;
        private String name;
        private String ext;
        private long uploadTime;
        private String sourceUrl;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getExt() {
            return ext;
        }

        public void setExt(String ext) {
            this.ext = ext;
        }

        public long getUploadTime() {
            return uploadTime;
        }

        public void setUploadTime(long uploadTime) {
            this.uploadTime = uploadTime;
        }

        public String getSourceUrl() {
            return sourceUrl;
        }

        public void setSourceUrl(String sourceUrl) {
            this.sourceUrl = sourceUrl;
        }
    }
}
