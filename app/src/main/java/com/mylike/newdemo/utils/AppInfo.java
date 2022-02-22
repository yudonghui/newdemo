package com.mylike.newdemo.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.mylike.newdemo.App;


public class AppInfo {
    public static String getAppVersionName() {
        String versionName = "";
        int versioncode;
        try {
            // ---get the package info---
            PackageManager pm = App.getContext().getPackageManager();
            PackageInfo pi = pm.getPackageInfo(App.getContext().getPackageName(), 0);
            versionName = pi.versionName;
            versioncode = pi.versionCode;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            LogUtils.e(e.toString());
        }
        return versionName;
    }

    public static int getAppVersionCode() {
        String versionName = "";
        int versioncode = 0;
        try {
            // ---get the package info---
            PackageManager pm = App.getContext().getPackageManager();
            PackageInfo pi = pm.getPackageInfo(App.getContext().getPackageName(), 0);
            versionName = pi.versionName;
            versioncode = pi.versionCode;

        } catch (Exception e) {
            LogUtils.e(e.toString());
        }
        return versioncode;
    }
}
