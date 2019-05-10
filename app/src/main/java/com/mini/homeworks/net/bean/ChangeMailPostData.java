package com.mini.homeworks.net.bean;

public class ChangeMailPostData {

    private String email;
    private int verifyCode;

    public ChangeMailPostData(String email, int verifyCode) {
        this.email = email;
        this.verifyCode = verifyCode;
    }
}
