package com.djw.douban.ui.cloud.activity;

import android.os.Bundle;
import android.util.Log;

import com.djw.douban.R;
import com.djw.douban.base.RxActivity;
import com.djw.douban.data.cloud.UserData;
import com.djw.douban.ui.cloud.contract.UserContract;
import com.djw.douban.ui.cloud.presenter.UserPresenter;

public class UserActivity extends RxActivity<UserPresenter> implements UserContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showUser(UserData userData) {
        Log.i("userData", userData.toString());
    }

    @Override
    public void initView() {

    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void inject() {
        getActivityComponent().inject(this);
        mPresenter.attachView(this);
        mPresenter.getUser(getIntent().getExtras().getString("id"));
    }
}
