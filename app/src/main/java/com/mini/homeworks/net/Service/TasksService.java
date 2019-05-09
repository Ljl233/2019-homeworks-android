package com.mini.homeworks.net.Service;

import com.mini.homeworks.net.bean.TasksBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface TasksService {
    @GET("/api/assignment/list/")
    Call<TasksBean> getTaskBean(@Header("cookie") String cookie, @Header ("token") String token);
}
