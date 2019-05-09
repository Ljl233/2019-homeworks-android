package com.mini.homeworks.net.Service;

import com.mini.homeworks.net.bean.ChangeMailBean;
import com.mini.homeworks.net.bean.ChangeMailPostData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.PUT;

public interface ChangeMailService {
    @PUT("/api/mail/modify/")
    Call<ChangeMailBean> getReturn(@Header ("token") String token, @Header("verifyCodeToken") String verifyCodeToken , @Body ChangeMailPostData changeMailPostData);

}


/*
修改邮箱
URL	Method	header
/api/mail/modify/	PUT token verifyCodeToken
Post Data
{
    "email": String,
    "verifyCode": int
}
Return Data
{
    "msg": String
}
Status Code
200 成功
400 请求错误
401 身份认证错误
 */
