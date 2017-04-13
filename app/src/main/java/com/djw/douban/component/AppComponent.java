package com.djw.douban.component;


import com.djw.douban.base.BaseApplication;
import com.djw.douban.http.RetrofitHelper;
import com.djw.douban.module.AppMoudel;
import com.djw.douban.module.HttpMoudel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by JasonDong on 2017/3/23.
 */

@Singleton
@Component(modules = {AppMoudel.class, HttpMoudel.class})
public interface AppComponent {

    BaseApplication getContext();

    RetrofitHelper retrofitHelper();

}
