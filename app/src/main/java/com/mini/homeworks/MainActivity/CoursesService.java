package com.mini.homeworks.MainActivity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface CoursesService {
    @GET("/api/course/list/")
    Call<CoursesBean> getCoursesBean(@Header("cookie") String cookies , @Header("token") String tokens);
}