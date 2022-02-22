package com.mylike.newdemo.ui.model;

import android.util.Log;

import com.mylike.newdemo.ApiService;
import com.mylike.newdemo.base.BaseModel;
import com.mylike.newdemo.callback.ResponseCallBack;
import com.mylike.newdemo.common.CommonSubscriber;
import com.mylike.newdemo.common.inject.BaseModule;
import com.mylike.newdemo.common.network.HttpRxObservable;
import com.mylike.newdemo.ui.bean.User;
import com.mylike.newdemo.ui.bean.UserInfo;
import com.mylike.newdemo.ui.bean.VersionInfo;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;


import java.util.HashMap;
import java.util.Map;


import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class MainModel extends BaseModel {
    private final RxAppCompatActivity mActivity;
    @Inject
    ApiService mApiService;

    @Inject
    public MainModel(RxAppCompatActivity activity) {
        mActivity = activity;
        Log.e("创建了: ", "MainModel");
    }

    public void get(HashMap<String, String> params, CommonSubscriber<User> subscriber) {
        Flowable<User> userFlowable = mApiService.getUser(params);
        //被观察者
        Flowable observable = HttpRxObservable.getObservable(userFlowable, mActivity);
        observable.subscribe(subscriber);
    }

    public void getVersionInfo(HashMap<String, String> params, CommonSubscriber<VersionInfo> subscriber) {
        Flowable<VersionInfo> userFlowable = mApiService.getVersionInfo(params);
        //被观察者
        Flowable observable = HttpRxObservable.getObservable(userFlowable, mActivity);
        observable.subscribe(subscriber);
    }

    public void getUserInfo(HashMap<String, String> params, CommonSubscriber<UserInfo> subscriber) {
        Flowable<UserInfo> userFlowable = mApiService.getUserInfo(params);
        //被观察者
        Flowable observable = HttpRxObservable.getObservable(userFlowable, mActivity);
        observable.subscribe(subscriber);
    }

    public void download(String url, final ResponseCallBack callBack) {
        BaseModule baseModule = new BaseModule(mActivity);
        ApiService apiService = baseModule.mRetrofitDownload.create(ApiService.class);
        Observable<ResponseBody> download = apiService.download(url);
        download.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        callBack.callBack(responseBody);
                    }
                });
    }

    public void upload(Map<String, RequestBody> params, final ResponseCallBack callBack) {
        BaseModule baseModule = new BaseModule(mActivity);
        ApiService apiService = baseModule.mRetrofitUpload.create(ApiService.class);
        Observable<ResponseBody> download = apiService.upImg(params);
        download.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        callBack.callBack(responseBody);
                    }
                });
    }
}
