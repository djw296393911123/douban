package com.djw.douban.base;

import android.content.Context;
import android.support.annotation.LayoutRes;

/**
 * Created by JasonDong
 * <p>
 * on 2017/3/23.
 */

public abstract class SimpleActivity extends BaseActivity {

    protected Context context;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        context = this;
        initView();
        doBusiness();
    }

    public abstract void initView();

    public abstract void doBusiness();
}