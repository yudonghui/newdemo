package com.mylike.newdemo.common.inject;

import android.content.Context;


import com.mylike.newdemo.ApiService;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule extends BaseModule {
    public AppModule(Context application) {
        super(application);
    }

    @Provides
    public ApiService provideApiService() {
        return mRetrofit.create(ApiService.class);
    }
}
