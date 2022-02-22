package com.mylike.newdemo.common.inject;


import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

import dagger.Module;
import dagger.Provides;

/*
* 在firstmodule中使用的构造方法是有参数的，这个参数需要一个创建对象的过程。这里进行再次封装
* 做一个provides
* */
@Module
public class ActivityModule {
    private RxAppCompatActivity mActivity;

    public ActivityModule(RxAppCompatActivity compatActivity) {
        mActivity = compatActivity;
    }

    @Provides
    RxAppCompatActivity getActivity(){
        return mActivity;
    }
}
