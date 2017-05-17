package com.djw.douban.ui.movies.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.djw.douban.R;
import com.djw.douban.base.RxToolbarActivity;
import com.djw.douban.data.ParamsData;
import com.djw.douban.data.movies.NorthAmericaItemData;
import com.djw.douban.ui.movies.adapter.NorthAmericaRecyclerAdapter;
import com.djw.douban.ui.movies.contract.NorthAmericaContract;
import com.djw.douban.ui.movies.presenter.NorthAmericaPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NorthAmericaActivity extends RxToolbarActivity<NorthAmericaPresenter> implements NorthAmericaContract.View, OnRefreshListener, OnLoadMoreListener {

    @BindView(R.id.swipe_target)
    RecyclerView rvNorth;
    @BindView(R.id.tv_north)
    TextView tvNorth;
    @BindView(R.id.stll_movies)
    SwipeToLoadLayout swipeToLoadLayout;
    private NorthAmericaRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_north_america);
    }

    @Override
    public void showError(String msg) {
        refreshOrLoadMoreStop();
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMoviesList(List<NorthAmericaItemData.SubjectsBean> list, String date, boolean isLoadMore) {
        refreshOrLoadMoreStop();
        tvNorth.setText(date);
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
        setToolBarTitle(getString(R.string.northamerica));
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        rvNorth.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NorthAmericaRecyclerAdapter(this);
        rvNorth.setAdapter(adapter);
    }

    @Override
    protected void scrollToTop() {
        rvNorth.smoothScrollToPosition(0);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void inject() {
        getActivityComponent().inject(this);
        mPresenter.attachView(this);
        mPresenter.getMoviesList(ParamsData.START, ParamsData.COUNT, false, true);
    }

    @Override
    public void onRefresh() {
        mPresenter.getMoviesList(ParamsData.START, ParamsData.COUNT, false, false);
    }

    @Override
    public void onLoadMore() {
        mPresenter.getMoviesList(adapter.getItemCount() + 1, ParamsData.COUNT, true, false);
    }
}
