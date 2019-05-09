package com.mini.homeworks.net.Service;

import com.mini.homeworks.net.bean.CourseAssignBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface CourseAssignListService {
    @GET("api/course/{siteId}/assignment/list")
    Call<CourseAssignBean> getTasksBean(@Path("siteId") String siteId,
                                        @Header("cookie") String cookie,
                                        @Header("token") String token);
}
