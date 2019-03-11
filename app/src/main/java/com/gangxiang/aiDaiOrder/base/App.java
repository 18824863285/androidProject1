package com.gangxiang.aiDaiOrder.base;

import android.app.Application;
import android.os.Debug;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.gangxiang.aiDaiOrder.util.Configuration;
import com.squareup.leakcanary.LeakCanary;
import com.zhy.autolayout.config.AutoLayoutConifg;

public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Debug.startMethodTracing("App");

        Fresco.initialize(this);
        LeakCanary.install(this);
        Configuration.Build.init(this).perform();
        AutoLayoutConifg.getInstance().useDeviceSize();
        Debug.stopMethodTracing();

    }
}
