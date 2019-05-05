package com.mini.homeworks.Utils;

import android.util.Log;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSource;

public class TokenInterceptor implements Interceptor {
        private static final Charset UTF8 = Charset.forName("UTF-8");
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response;
            long startTime = System.currentTimeMillis();
            response = chain.proceed(request);
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            BufferedSource source = response.body().source();
            source.request(Long.MAX_VALUE);
            Buffer buffer = source.buffer();
            String log = "\n================="
                    .concat("\nnetwork code ==== " + response.code())
                    .concat("\nnetwork url ===== " + request.url())
                    .concat("\nduration ======== " + duration)
                    .concat("\nrequest duration ============ " + (response.receivedResponseAtMillis() - response.sentRequestAtMillis()))
                    .concat("\nrequest header == " + request.headers())
                    .concat("\nrequest ========= " + bodyToString(request.body()))
                    .concat("\nbody ============ " + buffer.clone().readString(UTF8));
            Log.i("lijk", "log is " + log);
            return response;
        }
        /**
         * 请求体转String
         *
         * @param request 请求体
         * @return String 类型的请求体
         */
        private static String bodyToString(final RequestBody request) {
            try {
                final Buffer buffer = new Buffer();
                request.writeTo(buffer);
                return buffer.readUtf8();
            } catch (final Exception e) {
                return "did not work";
            }
        }
    }
/*
    @Override
    public Response intercept(Chain chain) throws IOException{
        Request request = chain.request();
        Response response;
        response = chain.proceed(request);
        String token = request.body().toString();
       /* if ( StringUtils.isEmpty(token) ) {
            Request originalRequest = chain.request();
            return chain.proceed(originalRequest);
        }
        else */
/*
       {
            Request originalRequest = chain.request();
            Request updateRequest = originalRequest.newBuilder().header("token", token).build();
            return chain.proceed(updateRequest);
        }
    }

}
*/
/*

    @Override
    public Response intercept(Chain chain) throws IOException {
        // get token
        String token = MyAssignService.getToken();
        Request originalRequest = chain.request();
        // get new request, add request header
        Request updateRequest = originalRequest.newBuilder()
                .header("token", token)
                .build();
        return chain.proceed(updateRequest);
    }

}*/
