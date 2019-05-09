package com.mini.homeworks.net.Service;

import com.mini.homeworks.net.bean.LoginBean;
import com.mini.homeworks.net.bean.LoginPostData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {
    @POST("/api/login/")
    Call<LoginBean> getCourseBean(@Body LoginPostData loginPostData);

}
