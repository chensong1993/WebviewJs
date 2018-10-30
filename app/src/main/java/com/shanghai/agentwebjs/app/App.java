package com.shanghai.agentwebjs.app;

import android.app.Application;

/**
 * @author chensong
 * @date 2018/10/16 11:49
 */
public class App extends Application {
    public static  App instance;
    public static synchronized App getInstance() {
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
    }
}
