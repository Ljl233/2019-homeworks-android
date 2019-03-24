package com.mini.homeworks.CourseAssign;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface AssignListService {
    @GET("api/course/{siteId}/assignment/list")
    Call<TasksBean> getTasksBean(@Path("siteId") String siteId,
                                 @Header("cookie") String cookie,
                                 @Header("token") String token);
}
