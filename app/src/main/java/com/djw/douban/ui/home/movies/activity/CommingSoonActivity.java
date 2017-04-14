package com.djw.douban.ui.home.movies.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.djw.douban.R;
import com.djw.douban.base.RxActivity;
import com.djw.douban.data.ParamsData;
import com.djw.douban.data.movies.MoviesItemData;
import com.djw.douban.ui.home.movies.adapter.Top250Adapter;
import com.djw.douban.ui.home.movies.contract.CommingSoonContract;
import com.djw.douban.ui.home.movies.presenter.CommingSoonPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class CommingSoonActivity extends RxActivity<CommingSoonPresenter> implements CommingSoonContract.View, XRecyclerView.LoadingListener {

    XRecyclerView xrvCommingSoon;
    private Top250Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comming_soon);
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCommingSoon(List<MoviesItemData.SubjectsBean> list, boolean isLoadMore) {
        xrvCommingSoon.loadMoreComplete();
        xrvCommingSoon.refreshComplete();
        adapter.notifyDataChange(list, isLoadMore);
    }

    @Override
    public void initView() {
        xrvCommingSoon = (XRecyclerView) findViewById(R.id.xrv_comming_soon);
        xrvCommingSoon.setLayoutManager(new LinearLayoutManager(this));
        xrvCommingSoon.setLoadingListener(this);
        adapter = new Top250Adapter(this);
        xrvCommingSoon.setAdapter(adapter);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void inject() {
        getActivityComponent().inject(this);
        mPresenter.attachView(this);
        mPresenter.getCommingSoon(ParamsData.START, ParamsData.COUNT, false, true);
    }

    @Override
    public void onRefresh() {
        mPresenter.getCommingSoon(ParamsData.START, ParamsData.COUNT, false, false);
    }

    @Override
    public void onLoadMore() {
        mPresenter.getCommingSoon(adapter.getItemCount() + 1, ParamsData.COUNT, true, false);
    }
}
