package com.mini.homeworks.PersonalInformation;

public class SendVerifyCodeBean  {
    private String msg;
    private String verifyCodeToken;

    public String getMsg() {
        return msg;
    }

    public String getVerifyCodeToken() {
        return verifyCodeToken;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setVerifyCodeToken(String verifyCodeToken) {
        this.verifyCodeToken = verifyCodeToken;
    }
}
