package com.mylike.newdemo.ui.model;

import com.mylike.newdemo.ApiService;
import com.mylike.newdemo.base.BaseModel;
import com.mylike.newdemo.callback.ResponseCallBack;
import com.mylike.newdemo.common.CommonSubscriber;
import com.mylike.newdemo.common.inject.BaseModule;
import com.mylike.newdemo.common.network.HttpRxObservable;
import com.mylike.newdemo.ui.bean.VersionInfo;
import com.trello.rxlifecycle3.components.support.RxFragment;


import java.util.HashMap;


import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class HomeModel extends BaseModel {
    private final RxFragment mRxFragment;
    @Inject
    ApiService mApiService;
    @Inject
    public HomeModel(RxFragment fragment) {
        mRxFragment = fragment;
    }

    public void getVersionInfo(HashMap<String, String> params, CommonSubscriber<VersionInfo> subscriber) {
        Flowable<VersionInfo> userFlowable = mApiService.getVersionInfo(params);
        //被观察者
        Flowable observable = HttpRxObservable.getObservable(userFlowable, mRxFragment);
        observable.subscribe(subscriber);
    }
    public void download(String url, final ResponseCallBack callBack) {
        BaseModule baseModule = new BaseModule(mRxFragment.getContext());
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
}
