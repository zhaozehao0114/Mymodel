package com.bawei.http;

import android.app.Application;
import android.content.Context;

/**
 * @ProjectName: MVVMZG51905
 * @Package: com.zy.utils
 * @ClassName: MyApplication
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/11/12 11:12
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/11/12 11:12
 * @UpdateRemark:
 * @Version: 1.0
 */
public class MyApplication extends Application {
    private static Context appContext;
    @Override
    public void onCreate() {
        super.onCreate();
        appContext=this.getApplicationContext();
    }

    /**
     * 获取上下文方法
     * @param
     * @return
     * @author zhangyue
     * @time 2021/11/12 11:13
     */
    public static Context getAppContext(){
        return appContext;
    }
}
