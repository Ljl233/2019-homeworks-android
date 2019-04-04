package com.mini.homeworks.PersonalInformation;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;

public interface ChangeMailService {
    @PUT("/api/mail/modify/")
    Call<ChangeMailReturnData> getReturn(@Body ChangeMailPostData changeMailPostData);

}
