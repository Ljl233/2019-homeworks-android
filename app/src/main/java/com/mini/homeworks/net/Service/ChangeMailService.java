package com.mini.homeworks.net.Service;

import com.mini.homeworks.net.bean.ChangeMailBean;
import com.mini.homeworks.net.bean.ChangeMailPostData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.Header;
import retrofit2.http.PUT;

public interface ChangeMailService {
    @PUT("api/mail/modify/")
    Call<ChangeMailBean> getReturn(@Header ("token") String token, @Header("verifyCodeToken") String verifyCodeToken , @Body ChangeMailPostData changeMailPostData);

}
