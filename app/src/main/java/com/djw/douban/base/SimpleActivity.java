package com.djw.douban.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.View;

import com.github.anzewei.parallaxbacklayout.ParallaxBackActivityHelper;
import com.github.anzewei.parallaxbacklayout.ParallaxBackLayout;
import com.roger.catloadinglibrary.CatLoadingView;
import com.zhy.autolayout.AutoLayoutActivity;

/**
 * Created by JasonDong on 2017/3/23.
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