package com.djw.douban.base;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.djw.douban.R;
import com.djw.douban.component.ActivityComponent;
import com.djw.douban.component.DaggerActivityComponent;
import com.djw.douban.module.ActivityModule;

import javax.inject.Inject;

/**
 * Created by JasonDong
 * <p>
 * on 2017/4/10.
 */

public abstract class RxToolbarActivity<T extends BasePresenter> extends BaseActivity implements BaseView {
    @Inject
    public T mPresenter;
    private TextView tvTitle;

    private long pressTime;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
//        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
//            @Override
//            public void onCancel(DialogInterface dialog) {
//                //取消网络请求
//                if (mPresenter != null) mPresenter.detachView();
//            }
//        });
        context = this;
        initHeader();
        initView();
        doBusiness();
        inject();
    }

    public void initHeader() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tl_base);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (System.currentTimeMillis() - pressTime > 2000) {
                    pressTime = System.currentTimeMillis();
                } else {
                    scrollToTop();
                }
            }
        });
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        tvTitle = ((TextView) toolbar.findViewById(R.id.tv_toolbar_title));
        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    protected void setToolBarTitle(String title) {
        if (!TextUtils.isEmpty(title))
            tvTitle.setText(title);
    }

    protected abstract void initView();

    protected abstract void scrollToTop();

    protected abstract void doBusiness();

    protected abstract void inject();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.detachView();
        mPresenter = null;
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
