package com.djw.douban.base;

import android.app.Application;

import com.bumptech.glide.request.target.ViewTarget;
import com.djw.douban.R;
import com.djw.douban.component.AppComponent;
import com.djw.douban.component.DaggerAppComponent;
import com.djw.douban.module.AppMoudel;
import com.djw.douban.module.HttpMoudel;
import com.djw.douban.util.SPUtils;

/**
 * Created by JasonDong on 2017/3/23.
 */

public class BaseApplication extends Application {
    private static BaseApplication instance;
    public static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        SPUtils.init(this, "carinfo");
        ViewTarget.setTagId(R.id.glide_tag_id);
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
