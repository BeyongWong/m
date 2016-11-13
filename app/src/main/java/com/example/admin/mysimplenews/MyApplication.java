package com.example.admin.mysimplenews;

import android.app.Application;

/**
 * Created by admin on 2016/11/9.
 */

public class MyApplication extends Application {
//    public static MyApplication app=new MyApplication(); 错误的
  public static MyApplication app;

    @Override
    public void onCreate() {
        super.onCreate();
app=this;
    }
}
