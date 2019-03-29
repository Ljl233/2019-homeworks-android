package com.mini.homeworks.Search;



import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Url;


public interface SearchService {
    @GET("/api/search/")
    Call<SearchBean> getSearchBean(@Header("cookie") String cookie,
                                   @Header("token") String token,
                                   @Url String s);
}
