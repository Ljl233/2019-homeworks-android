package com.mini.homeworks.AssignDetail;


import retrofit2.*;
import retrofit2.http.*;

public interface DetailService {
    @GET("/api/assignment/{siteId}/{assignId}/info/")
    Call<DetailBean> getDetailBean(@Path("siteId") String siteId,
                                   @Path("assignId") String assignId,
                                   @Header("cookie") String cookie,
                                   @Header("token") String token);
}
