package com.mini.homeworks.AssignDetail;

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
    private String status;
    private String beginTime;
    private String endTime;
    private String content;
    private String pointNum;
    private String commitNum;
    private String isGroup;
    private String groupNum;
    private String studentNum;
    private String groupPoint;
    private String personalPoint;
    private String feedback;
    private String assignAttachmentNum;
    private String submitAttachmentNum;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPointNum() {
        return pointNum;
    }

    public void setPointNum(String pointNum) {
        this.pointNum = pointNum;
    }

    public String getCommitNum() {
        return commitNum;
    }

    public void setCommitNum(String commitNum) {
        this.commitNum = commitNum;
    }

    public String getIsGroup() {
        return isGroup;
    }

    public void setIsGroup(String isGroup) {
        this.isGroup = isGroup;
    }

    public String getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(String groupNum) {
        this.groupNum = groupNum;
    }

    public String getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
    }

    public String getGroupPoint() {
        return groupPoint;
    }

    public void setGroupPoint(String groupPoint) {
        this.groupPoint = groupPoint;
    }

    public String getPersonalPoint() {
        return personalPoint;
    }

    public void setPersonalPoint(String personalPoint) {
        this.personalPoint = personalPoint;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getAssignAttachmentNum() {
        return assignAttachmentNum;
    }

    public void setAssignAttachmentNum(String assignAttachmentNum) {
        this.assignAttachmentNum = assignAttachmentNum;
    }

    public String getSubmitAttachmentNum() {
        return submitAttachmentNum;
    }

    public void setSubmitAttachmentNum(String submitAttachmentNum) {
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
        private String uploadTime;
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

        public String getUploadTime() {
            return uploadTime;
        }

        public void setUploadTime(String uploadTime) {
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
