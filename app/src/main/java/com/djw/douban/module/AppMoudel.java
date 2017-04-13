package com.djw.douban.module;


import com.djw.douban.base.BaseApplication;
import com.djw.douban.http.RetrofitHelper;
import com.djw.douban.http.apis.DoubanApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by JasonDong on 2017/3/23.
 */

@Module
public class AppMoudel {

    private final BaseApplication application;

    public AppMoudel(BaseApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    BaseApplication provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    RetrofitHelper provideRetrofitHelper(DoubanApi doubanApi) {
        return new RetrofitHelper(doubanApi);
    }

}
