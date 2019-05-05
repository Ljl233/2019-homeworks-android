package com.mini.homeworks.Utils;


import com.mini.homeworks.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitWrapper {

  //  OkHttpClient client = new OkHttpClient.Builder()
    //        .addNetworkInterceptor(new TokenInterceptor())
      //      .build();


    private Retrofit retrofit;
    private static RetrofitWrapper instance;

    private RetrofitWrapper() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
        //        .client(client)
                .build();
    }

    public static RetrofitWrapper getInstance() {
        if (instance == null) {
            synchronized (RetrofitWrapper.class) {
                instance = new RetrofitWrapper();
            }
        }
        return instance;
    }

    public <T> T create(Class<T> service) {
        return retrofit.create(service);
    }

    public class Constant {
        public static final String BASE_URL = "http://47.102.120.167:2333";
    }
}

