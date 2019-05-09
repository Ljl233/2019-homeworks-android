package com.mini.homeworks.net.Service;

import com.mini.homeworks.net.bean.SendVerifyCodeBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface SendVerifyCodeService {
    @GET("/api/mail/modify/sendVerifyCode/")
    Call<SendVerifyCodeBean> Send(@Header("token") String token, @Field("email") String email) ;
}