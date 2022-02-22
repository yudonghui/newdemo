package com.mylike.newdemo.common.updateapp;


import android.text.TextUtils;

import com.google.gson.Gson;
import com.mylike.newdemo.utils.AppInfo;
import com.xuexiang.xupdate.entity.UpdateEntity;
import com.xuexiang.xupdate.proxy.IUpdateParser;


public class CustomUpdateParser implements IUpdateParser {
    @Override
    public UpdateEntity parseJson(String json) throws Exception {
        VersionInfoEntity versionInfoEntity = new Gson().fromJson(json, VersionInfoEntity.class);
        String lowVersion = versionInfoEntity.getLowVersion();
        String version = versionInfoEntity.getVersion();
        String appUrl = versionInfoEntity.getAppUrl();
        String forceUpdate = versionInfoEntity.getForceUpdate();
        String versionName = versionInfoEntity.getVersionName();
        String updateContent = versionInfoEntity.getUpdateContent();
        String tenantId = versionInfoEntity.getTenantId();
        int remoteVersionCode = Integer.parseInt(version);//线上的版本号
        int appVersionCode = AppInfo.getAppVersionCode();//本地的版本号
        boolean isHasUpdate = false;
        if (remoteVersionCode > appVersionCode) {//有高版本
            isHasUpdate = true;
        }
        return new UpdateEntity()
                .setHasUpdate(isHasUpdate)//是否有新版本
                .setForce("true".equals(forceUpdate))//是否强制安装：不安装无法使用app
                .setIsIgnorable(!"true".equals(forceUpdate))//是否可忽略该版本
                .setVersionCode(remoteVersionCode)//最新版本code
                .setVersionName(versionName)//最新版本名称
                .setUpdateContent(updateContent)//更新内容
                .setDownloadUrl(appUrl);
    }
}

