package com.djw.douban.module;

import android.app.Activity;


import com.djw.douban.http.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by JasonDong
 * <p>
 * on 2017/3/24.
 */

@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity() {
        return mActivity;
    }
}
