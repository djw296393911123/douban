package com.djw.douban.ui.home.movies.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.djw.douban.R;
import com.djw.douban.base.RxActivity;
import com.djw.douban.data.ParamsData;
import com.djw.douban.data.movies.NorthAmericaItemData;
import com.djw.douban.ui.home.movies.adapter.NorthAmericaRecyclerAdapter;
import com.djw.douban.ui.home.movies.contract.NorthAmericaContract;
import com.djw.douban.ui.home.movies.presenter.NorthAmericaPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class NorthAmericaActivity extends RxActivity<NorthAmericaPresenter> implements NorthAmericaContract.View, XRecyclerView.LoadingListener {

    private XRecyclerView recyclerView;
    private NorthAmericaRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_north_america);
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMoviesList(List<NorthAmericaItemData.SubjectsBean> list, boolean isLoadMore) {
        recyclerView.loadMoreComplete();
        recyclerView.refreshComplete();
        adapter.notifyDataChange(list, isLoadMore);
    }

    @Override
    public void initView() {
        recyclerView = (XRecyclerView) findViewById(R.id.xrv_north);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setLoadingListener(this);
        adapter = new NorthAmericaRecyclerAdapter(this);
        recyclerView.setAdapter(adapter);
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
