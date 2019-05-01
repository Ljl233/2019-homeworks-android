package com.mini.homeworks.Utils;

import android.media.audiofx.DynamicsProcessing;
import android.net.Uri;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Set;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/*
public class TokenHeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        // get token
        String token = AppService.getToken();
        Request originalRequest = chain.request();
        // get new request, add request header
        Request updateRequest = originalRequest.newBuilder()
                .header("token", token)
                .build();
        return chain.proceed(updateRequest);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String url = request.url().toString();
        if (NetConfig.needAddToken(request)) {
            Uri uri = Uri.parse(url);
            Set<String> oldParam = uri.getQueryParameterNames();
            if (!oldParam.contains("token")){
                String token = getToken();
                if (oldParam.size() >0){
                    url += "&token=" + token;
                }else {
                    url += "?token=" +token;
                }
            }
            request = request.newBuilder().url(url).build();
        }
        return chain.proceed(request);
    }

    @Override
    public Response intercept(Chain chain) throws IOException{
        String token = MainDataManager.getInstance().getToken();
        if (StringUtils.isEmpty(token)) {
            Request originalRequest = chain.request();
            return chain.proceed(originalRequest);
        }else {
            Request originalRequest = chain.request();
            Request updateRequest = originalRequest.newBuilder().header("token", token).build();
            return chain.proceed(updateRequest);
        }
    }

}

public class TokenInterceptor implements Interceptor {
    public static final Charset UTF_8 = Charset.forName("UTF-8");

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Response originalResponse = chain.proceed(originalRequest);
        // 获取返回的数据字符串
        ResponseBody responseBody = originalResponse.body();
        BufferedSource source = originalResponse.body().source();
        source.request(Integer.MAX_VALUE);
        Buffer buffer = source.buffer();
        Charset charset = UTF_8;
        MediaType contentType = responseBody.contentType();
        if (contentType != null) {
            charset = contentType.charset();
        }
        String bodyString = buffer.clone().readString(charset);

        String newToken = service.refreshToken.execute().body();
        // 保存新的 token
        DynamicsProcessing.Config.saveToken(newToken);
        // 添加到 Query 参数
        HttpUrl url = chain.request().url()
                .newBuilder()
                .setQueryParameter("token", newToken)
                .build();
        Request newRequest = chain.request().newBuilder()
                .url(url)
                .build();
        // 添加到 Header
        Request anewRequest = originalRequest.newBuilder()
                .header("Authorization", newToken)
                .build();
        originalResponse.body().close();
        return chain.proceed(anewRequest);
        return originalResponse;
    }
}
*/