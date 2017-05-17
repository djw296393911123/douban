package com.djw.douban.ui.movies.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.djw.douban.R;
import com.djw.douban.base.RxToolbarActivity;
import com.djw.douban.data.ParamsData;
import com.djw.douban.data.movies.MoviesItemData;
import com.djw.douban.ui.movies.adapter.Top250Adapter;
import com.djw.douban.ui.movies.contract.Top250Contract;
import com.djw.douban.ui.movies.presenter.Top250Presenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Top250Activity extends RxToolbarActivity<Top250Presenter> implements Top250Contract.View, OnRefreshListener, OnLoadMoreListener {

    @BindView(R.id.swipe_target)
    RecyclerView rvTop;
    @BindView(R.id.stll_movies)
    SwipeToLoadLayout swipeToLoadLayout;
    private Top250Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top250);
    }

    @Override
    public void initView() {
        setToolBarTitle(getString(R.string.top));
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        rvTop.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Top250Adapter(this);
        rvTop.setAdapter(adapter);
    }

    @Override
    protected void scrollToTop() {
        rvTop.smoothScrollToPosition(0);
    }

    @Override
    public void doBusiness() {
    }


    @Override
    public void inject() {
        getActivityComponent().inject(this);
        mPresenter.attachView(this);
        mPresenter.showTop250(ParamsData.START, ParamsData.COUNT, false, true);
    }


    @Override
    public void showError(String msg) {
        refreshOrLoadMoreStop();
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

    private void refreshOrLoadMoreStop() {
        if (swipeToLoadLayout.isRefreshing()) {
            swipeToLoadLayout.setRefreshing(false);
        }
        if (swipeToLoadLayout.isLoadingMore()) {
            swipeToLoadLayout.setLoadingMore(false);
        }
    }

    @Override
    public void showTop250(List<MoviesItemData.SubjectsBean> list, boolean isLoadMore) {
       refreshOrLoadMoreStop();
        adapter.notifyDataChange(list, isLoadMore);
    }
}
