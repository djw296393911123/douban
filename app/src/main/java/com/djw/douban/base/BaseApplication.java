package com.djw.douban.base;

import android.app.Application;

import com.djw.douban.component.AppComponent;
import com.djw.douban.component.DaggerAppComponent;
import com.djw.douban.module.AppMoudel;
import com.djw.douban.module.HttpMoudel;

/**
 * Created by JasonDong on 2017/3/23.
 *
 */

public class BaseApplication extends Application {
    private static BaseApplication instance;
    public static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static synchronized BaseApplication getInstance() {
        return instance;
    }

    public static AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appMoudel(new AppMoudel(instance))
                    .httpMoudel(new HttpMoudel())
                    .build();
        }
        return appComponent;
    }
}
