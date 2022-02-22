package com.mylike.newdemo.callback;


import okhttp3.ResponseBody;

public interface ResponseCallBack {
    void callBack(ResponseBody body);
    void complete();
}
