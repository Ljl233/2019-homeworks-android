package com.mini.homeworks.net.bean;

public class SendVerifyCodePostData {
    String email;
    public SendVerifyCodePostData(String email){
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
