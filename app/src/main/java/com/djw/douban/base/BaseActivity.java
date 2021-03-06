package com.djw.douban.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.View;

import com.djw.douban.R;
import com.github.anzewei.parallaxbacklayout.ParallaxBackActivityHelper;
import com.github.anzewei.parallaxbacklayout.ParallaxBackLayout;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.ButterKnife;

/**
 * Created by JasonDong
 * <p>
 * on 2017/3/23.
 */

public abstract class BaseActivity extends AutoLayoutActivity {

    private ParallaxBackActivityHelper mHelper;
    protected Context context;
    protected ProgressDialog progressDialog;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        context = this;
        progressDialog = new ProgressDialog(this);
        DoubleBounce doubleBounce = new DoubleBounce();
        doubleBounce.setColor(R.color.colorAccent);
        progressDialog.setIndeterminateDrawable(doubleBounce);
        progressDialog.setMessage("正在加载...");
    }

    public void startActivity(Class<?> clz) {
        startActivity(clz, null);
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }

    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {

            intent.putExtras(bundle);
        }
        startActivity(intent);
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }

    public void showProgress() {
        progressDialog.show();
    }

    public void dismissProgress() {
        progressDialog.dismiss();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mHelper.onPostCreate();
    }

    @Override
    public View findViewById(int id) {
        View v = super.findViewById(id);
        if (v == null && mHelper != null)
            return mHelper.findViewById(id);
        return v;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHelper.onActivityDestroy();

    }

    public ParallaxBackLayout getBackLayout() {
        return mHelper.getBackLayout();
    }

    public void setBackEnable(boolean enable) {
        getBackLayout().setEnableGesture(enable);
    }

    public void scrollToFinishActivity() {
        mHelper.scrollToFinishActivity();
    }

    @Override
    public void onBackPressed() {
        scrollToFinishActivity();
    }

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        mHelper = new ParallaxBackActivityHelper(this);
    }

}