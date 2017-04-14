package com.djw.douban.ui.home.movies.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.djw.douban.R;
import com.djw.douban.base.RxActivity;
import com.djw.douban.data.ParamsData;
import com.djw.douban.data.movies.MoviesItemData;
import com.djw.douban.ui.home.movies.adapter.Top250Adapter;
import com.djw.douban.ui.home.movies.contract.MoreMovieContract;
import com.djw.douban.ui.home.movies.presenter.MoreMoviePresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class MoreMovieActivity extends RxActivity<MoreMoviePresenter> implements MoreMovieContract.View, XRecyclerView.LoadingListener {

    private XRecyclerView recyclerView;
    private Top250Adapter adapter;
    private int type;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_movie);
    }

    @Override
    public void initView() {
        recyclerView = (XRecyclerView) findViewById(R.id.xrv_more);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        toolbar = (Toolbar) findViewById(R.id.tl_base);
        recyclerView.setLoadingListener(this);
        adapter = new Top250Adapter(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void doBusiness() {
        toolbar.setTitle("");
        ((TextView) toolbar.findViewById(R.id.tv_toolbar_title)).setText("电影");
    }

    @Override
    public void inject() {
        getActivityComponent().inject(this);
        mPresenter.attachView(this);
        type = getIntent().getExtras().getInt("type");
        mPresenter.getMoreMovie(type, ParamsData.START, ParamsData.COUNT, false);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {
        recyclerView.refreshComplete();
        recyclerView.loadMoreComplete();
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMoreMovie(List<MoviesItemData.SubjectsBean> list, boolean isLoadMore) {
        adapter.notifyDataChange(list, isLoadMore);
    }

    @Override
    public void onRefresh() {
        mPresenter.getMoreMovie(type, ParamsData.START, ParamsData.COUNT, false);
    }

    @Override
    public void onLoadMore() {
        mPresenter.getMoreMovie(type, adapter.getItemCount() + 1, ParamsData.COUNT, true);
    }

}
