package com.djw.douban.module;


import com.djw.douban.base.BaseApplication;
import com.djw.douban.db.DBHelper;
import com.djw.douban.http.RetrofitHelper;
import com.djw.douban.http.apis.DoubanApi;
import com.djw.douban.http.apis.GirlApi;
import com.djw.douban.http.apis.MessageApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by JasonDong
 * <p>
 * on 2017/3/23.
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
    RetrofitHelper provideRetrofitHelper(DoubanApi doubanApi, GirlApi girlApi, MessageApi messageApi) {
        return new RetrofitHelper(doubanApi, girlApi, messageApi);
    }

    @Provides
    @Singleton
    DBHelper provideDBHelper() {
        return new DBHelper(application.getApplicationContext());
    }
}
