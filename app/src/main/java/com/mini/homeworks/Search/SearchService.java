package com.mini.homeworks.Search;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface SearchService {
    @GET("/api/search/")
    Call<SearchBean> getSearchBean(@Query("keyword") String s,
                                   @Header("cookie") String cookie,
                                   @Header("token") String token
    );
}