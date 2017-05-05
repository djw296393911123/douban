package com.djw.douban.ui.message;

import android.os.Bundle;

import com.djw.douban.R;
import com.djw.douban.base.RxToolbarActivity;
import com.djw.douban.ui.movies.presenter.HotPresenter;

public class MessageActivity extends RxToolbarActivity<HotPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
    }

    @Override
    public void initView() {
        setToolBarTitle(getString(R.string.message));
    }

    @Override
    protected void scrollToTop() {

    }

    @Override
    public void doBusiness() {

    }

    @Override
    protected void inject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void showError(String msg) {

    }
}
