package com.mini.homeworks.PersonalInformation;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface InformationService {
    @GET("/api/userInfo/")
    Call<InformationBean> getInformationBean(@Header("token") String token);
}