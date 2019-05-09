package com.mini.homeworks.net.Service;


import com.mini.homeworks.net.bean.SearchBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface SearchService {
    @GET("/api/search/")
    Call<SearchBean> getSearchBean(@Query("keyword") String s,
                                   @Header("cookie") String cookie,
                                   @Header("token") String token
    );
}