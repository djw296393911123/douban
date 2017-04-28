package com.djw.douban.ui.home.movies.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.djw.douban.R;
import com.djw.douban.base.RxToolbarActivity;
import com.djw.douban.data.ParamsData;
import com.djw.douban.data.movies.MoviesItemData;
import com.djw.douban.ui.home.movies.adapter.Top250Adapter;
import com.djw.douban.ui.home.movies.contract.Top250Contract;
import com.djw.douban.ui.home.movies.presenter.Top250Presenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;

public class Top250Activity extends RxToolbarActivity<Top250Presenter> implements Top250Contract.View, XRecyclerView.LoadingListener {

    @BindView(R.id.rv_top)
    XRecyclerView rvTop;
    private Top250Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top250);
    }

    @Override
    public void initView() {
        setToolBarTitle(getString(R.string.top));
        rvTop.setLayoutManager(new LinearLayoutManager(this));
        rvTop.setLoadingListener(this);
        rvTop.setLoadingMoreProgressStyle(25);
        adapter = new Top250Adapter(this);
        rvTop.setAdapter(adapter);
    }

    @Override
    protected void scrollToTop() {
        rvTop.scrollToPosition(0);
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
        rvTop.refreshComplete();
        rvTop.loadMoreComplete();
        adapter.notifyDataChange(list, isLoadMore);
    }
}
