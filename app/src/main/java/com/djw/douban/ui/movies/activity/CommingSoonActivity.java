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
import com.djw.douban.ui.movies.adapter.CommingSoonAdapter;
import com.djw.douban.ui.movies.contract.CommingSoonContract;
import com.djw.douban.ui.movies.presenter.CommingSoonPresenter;

import java.util.List;

import butterknife.BindView;

public class CommingSoonActivity extends RxToolbarActivity<CommingSoonPresenter> implements CommingSoonContract.View, OnRefreshListener, OnLoadMoreListener {

    @BindView(R.id.swipe_target)
    RecyclerView xrvCommingSoon;
    @BindView(R.id.stll_movies)
    SwipeToLoadLayout swipeToLoadLayout;
    private CommingSoonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comming_soon);
    }

    @Override
    public void showError(String msg) {
        refreshOrLoadMoreStop();
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCommingSoon(List<MoviesItemData.SubjectsBean> list, boolean isLoadMore) {
        refreshOrLoadMoreStop();
        adapter.notifyDataChange(list, isLoadMore);
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
    public void initView() {
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        setToolBarTitle(getString(R.string.commingsoon));
        xrvCommingSoon.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CommingSoonAdapter(this);
        xrvCommingSoon.setAdapter(adapter);
    }

    @Override
    protected void scrollToTop() {
        xrvCommingSoon.smoothScrollToPosition(0);
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
