package com.djw.douban.base;

import android.support.annotation.LayoutRes;

import com.djw.douban.component.ActivityComponent;
import com.djw.douban.component.DaggerActivityComponent;
import com.djw.douban.module.ActivityModule;

import javax.inject.Inject;

/**
 * Created by JasonDong on 2017/4/10.
 */

public abstract class RxActivity<T extends BasePresenter> extends BaseActivity implements BaseView {
    @Inject
    public T mPresenter;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        context = this;
        initView();
        doBusiness();
        inject();
    }

    public abstract void initView();

    public abstract void doBusiness();

    public abstract void inject();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.detachView();
    }


    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(BaseApplication.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}
