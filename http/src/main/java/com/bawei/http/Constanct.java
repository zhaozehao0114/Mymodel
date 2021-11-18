package com.bawei.http;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;




public class Constanct {

    public static final int TIMEOUT=1;
    

    public static String getAuthCode(){
        try {
            ApplicationInfo info = MyApplication.getAppContext().getPackageManager().getApplicationInfo(MyApplication.getAppContext().getPackageName(), PackageManager.GET_META_DATA);
            String authCode=info.metaData.getString("authCode");
            return authCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }
}
