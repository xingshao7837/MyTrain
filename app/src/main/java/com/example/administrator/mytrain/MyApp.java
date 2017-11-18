package com.example.administrator.mytrain;

import android.app.Application;

/**
 * Created by Administrator on 2017/11/17 0017.
 */

public class MyApp  extends Application {
    private static MyApp app;

    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
    }

    public static MyApp getApp() {
        return app;
    }
}
