package com.mini.homeworks.net.Service;

import com.mini.homeworks.net.bean.SendVerifyCodeBean;
import com.mini.homeworks.net.bean.SendVerifyCodePostData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface SendVerifyCodeService {
    @POST("api/mail/modify/sendVerifyCode/")
    Call<SendVerifyCodeBean> Send(@Header("token") String token, @Body SendVerifyCodePostData email) ;
}