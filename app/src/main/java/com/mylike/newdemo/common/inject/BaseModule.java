package com.mylike.newdemo.common.inject;

import android.content.Context;


import com.mylike.newdemo.interceptors.RequestParamInterceptor;
import com.mylike.newdemo.interceptors.RequestUrlInterceptor;
import com.mylike.newdemo.utils.LogUtils;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class BaseModule {

    public static long timeOut = 30;//连接超时,30秒
    protected Retrofit mRetrofit;
    public Retrofit mRetrofitDownload;
    public Retrofit mRetrofitUpload;

    public static String BASE_URL = "http://api.dev.ixungen.cn/";
    public static String RES_URL = "https://res.ixungen.cn/";
    public static String DOWD_URL = "https://www.ixungen.cn/download/xungen.apk";

    public BaseModule(Context application) {
        //原生Log日志拦截
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                LogUtils.e("返回结果: " + message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = null;
        try {
            okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(timeOut, TimeUnit.SECONDS)
                    .readTimeout(timeOut, TimeUnit.SECONDS)
                    .writeTimeout(timeOut, TimeUnit.SECONDS)
                    .addInterceptor(new RequestParamInterceptor())//对请求的参数进一步定制
                    .addInterceptor(new RequestUrlInterceptor())//对请求的接口进一步定制
                    //.addInterceptor(downloadInterceptor)
                    .addInterceptor(loggingInterceptor)
                    //其他配置
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Retrofit初始化
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mRetrofitDownload = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mRetrofitUpload = new Retrofit.Builder()
                .baseUrl(RES_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
