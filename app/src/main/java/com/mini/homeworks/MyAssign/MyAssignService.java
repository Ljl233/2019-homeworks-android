package com.mini.homeworks.MyAssign;

import com.mini.homeworks.MainActivity.TaskBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface MyAssignService {
    @GET("/api/assignment/list/")
    Call<TaskBean> getTaskBean(@Header("cookie") String cookie, @Header ("token") String token);
}
