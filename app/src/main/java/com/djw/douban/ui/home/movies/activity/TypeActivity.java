package com.djw.douban.ui.home.movies.activity;

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
import com.djw.douban.data.movies.TypeData;
import com.djw.douban.ui.home.movies.adapter.TypeAdapter;
import com.djw.douban.ui.home.movies.contract.TypeContract;
import com.djw.douban.ui.home.movies.presenter.TypePresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TypeActivity extends RxToolbarActivity<TypePresenter> implements TypeContract.View, OnRefreshListener, OnLoadMoreListener {

    @BindView(R.id.swipe_target)
    RecyclerView rvType;
    @BindView(R.id.stll_movies)
    SwipeToLoadLayout swipeToLoadLayout;
    private TypeAdapter adapter;
    private String q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);
        ButterKnife.bind(this);
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showType(List<TypeData.SubjectsBean> list, boolean isLoadMore) {
        if (swipeToLoadLayout.isRefreshing()) {
            swipeToLoadLayout.setRefreshing(false);
        }
        if (swipeToLoadLayout.isLoadingMore()) {
            swipeToLoadLayout.setLoadingMore(false);
        }
        adapter.notifyDataChange(list, isLoadMore);
    }

    @Override
    public void initView() {
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        rvType.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TypeAdapter(this);
        rvType.setAdapter(adapter);
    }

    @Override
    protected void scrollToTop() {
        rvType.scrollToPosition(0);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void inject() {
        getActivityComponent().inject(this);
        mPresenter.attachView(this);
        q = getIntent().getExtras().getString("q");
        setToolBarTitle(q);
        mPresenter.getType(q, ParamsData.START, ParamsData.COUNT, false, true);
    }

    @Override
    public void onRefresh() {
        mPresenter.getType(q, ParamsData.START, ParamsData.COUNT, false, false);
    }

    @Override
    public void onLoadMore() {
        mPresenter.getType(q, adapter.getItemCount() + 1, ParamsData.COUNT, true, false);
    }
}
