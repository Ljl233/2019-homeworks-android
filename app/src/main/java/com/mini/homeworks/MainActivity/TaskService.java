package com.mini.homeworks.MainActivity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface TaskService {
    @GET("/api/assignment/list/")
    Call<TaskBean> getTaskBean(@Header("cookie") String cookie, @Header ("token") String token);
}
