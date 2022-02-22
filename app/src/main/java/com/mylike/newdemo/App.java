package com.mylike.newdemo;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.mylike.newdemo.common.updateapp.OKHttpUpdateHttpService;

import com.mylike.newdemo.utils.LogUtils;
import com.xuexiang.xupdate.XUpdate;
import com.xuexiang.xupdate.entity.UpdateError;
import com.xuexiang.xupdate.listener.OnUpdateFailureListener;


/**
 * Created by ydh on 2022/2/21
 */
public class App extends Application {
    private static Context mContext;
    public static boolean ISSUE = true;//true测试，false正式

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        initUpdate();
    }

    public static Context getContext() {
        return mContext;
    }

    private void initUpdate() {
        XUpdate.get()
                .debug(true)
                .isWifiOnly(false)                                               //默认设置true只在wifi下检查版本更新
                .isGet(true)                                                    //默认设置使用get请求检查版本
                .isAutoMode(false)                                              //默认设置非自动模式，可根据具体使用配置
                // .param("versionCode", UpdateUtils.getVersionCode(this))  //设置默认公共请求参数
                //  .param("appKey", getPackageName())
                .setOnUpdateFailureListener(new OnUpdateFailureListener() { //设置版本更新出错的监听
                    @Override
                    public void onFailure(UpdateError error) {
                        if (error.getCode() != UpdateError.ERROR.CHECK_NO_NEW_VERSION) {    //对不同错误进行处理
                            LogUtils.e("版本" + error.toString());
                            if (ISSUE == true)
                                Toast.makeText(getContext(), "版本" + error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .supportSilentInstall(false)                                     //设置是否支持静默安装，默认是true
                .setIUpdateHttpService(new OKHttpUpdateHttpService())           //这个必须设置！实现网络请求功能。
                .init(this);                                          //这个必须初始化
    }
}
