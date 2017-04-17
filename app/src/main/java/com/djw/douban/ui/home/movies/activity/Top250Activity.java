package com.djw.douban.ui.home.movies.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.djw.douban.R;
import com.djw.douban.base.RxActivity;
import com.djw.douban.data.ParamsData;
import com.djw.douban.data.movies.MoviesItemData;
import com.djw.douban.ui.home.movies.adapter.Top250Adapter;
import com.djw.douban.ui.home.movies.contract.Top250Contract;
import com.djw.douban.ui.home.movies.presenter.Top250Presenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class Top250Activity extends RxActivity<Top250Presenter> implements Top250Contract.View, XRecyclerView.LoadingListener, View.OnClickListener {

    private XRecyclerView recyclerView;
    private Top250Adapter adapter;
    private Toolbar toolbar;
    private long time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top250);
    }

    @Override
    public void initView() {
        recyclerView = (XRecyclerView) findViewById(R.id.rv_top);
        toolbar = (Toolbar) findViewById(R.id.tl_base);
        toolbar.setOnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLoadingListener(this);
        adapter = new Top250Adapter(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void doBusiness() {
        toolbar.setTitle("");
        ((TextView) toolbar.findViewById(R.id.tv_toolbar_title)).setText(R.string.top);
    }


    @Override
    public void inject() {
        getActivityComponent().inject(this);
        mPresenter.attachView(this);
        mPresenter.showTop250(ParamsData.START, ParamsData.COUNT, false, true);
    }


    @Override
    public void showError(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onRefresh() {
        mPresenter.showTop250(ParamsData.START, ParamsData.COUNT, false, false);
    }

    @Override
    public void onLoadMore() {
        mPresenter.showTop250(adapter.getItemCount() + 1, ParamsData.COUNT, true, false);
    }

    @Override
    public void showTop250(List<MoviesItemData.SubjectsBean> list, boolean isLoadMore) {
        recyclerView.refreshComplete();
        recyclerView.loadMoreComplete();
        adapter.notifyDataChange(list, isLoadMore);
    }

    @Override
    public void onClick(View v) {
        if (System.currentTimeMillis() - time > 2000) {
            time = System.currentTimeMillis();
        } else {
            mPresenter.showTop250(ParamsData.START, ParamsData.COUNT, false, false);
            recyclerView.smoothScrollToPosition(0);
        }
    }
}
