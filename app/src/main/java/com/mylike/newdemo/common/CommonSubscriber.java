package com.mylike.newdemo.common;

import com.mylike.newdemo.common.network.ApiException;
import com.mylike.newdemo.common.network.ExceptionEngine;
import com.mylike.newdemo.common.network.HttpResponse;

import io.reactivex.rxjava3.subscribers.ResourceSubscriber;

/**
 * Created by ydh on 2022/2/21
 */
public abstract class CommonSubscriber<T extends HttpResponse> extends ResourceSubscriber<T> {
    @Override
    public void onNext(T t) {
        getData(t);
    }

    @Override
    public void onError(Throwable e) {
        ApiException apiException = ExceptionEngine.handleException(e);
        error(apiException);
    }

    @Override
    public void onComplete() {

    }

    public abstract void getData(T t);

    public abstract void error(ApiException e);
}

