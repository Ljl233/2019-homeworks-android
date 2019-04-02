package com.mini.homeworks.Login;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CourseService {
    @POST("/api/login/")
    Call<CourseBean> getCourseBean(@Body LoginPostData loginPostData);
}
