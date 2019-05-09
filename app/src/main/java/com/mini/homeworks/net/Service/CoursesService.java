package com.mini.homeworks.net.Service;

import com.mini.homeworks.net.bean.CoursesBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface CoursesService {
    @GET("/api/course/list/")
    Call<CoursesBean> getCoursesBean(@Header("cookie") String cookies , @Header("token") String tokens);
}