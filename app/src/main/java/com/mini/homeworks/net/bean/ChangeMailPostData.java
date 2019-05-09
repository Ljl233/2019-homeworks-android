package com.mini.homeworks.net.bean;

public class ChangeMailPostData {

    private String email;
    private String verifyCode;

    public ChangeMailPostData(String email, String verifyCode) {
        this.email = email;
        this.verifyCode = verifyCode;
    }
}
