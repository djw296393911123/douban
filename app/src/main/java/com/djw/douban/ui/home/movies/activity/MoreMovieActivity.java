package com.djw.douban.ui.home.movies.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.djw.douban.R;
import com.djw.douban.base.RxToolbarActivity;
import com.djw.douban.data.ParamsData;
import com.djw.douban.data.movies.MoviesItemData;
import com.djw.douban.ui.home.movies.adapter.Top250Adapter;
import com.djw.douban.ui.home.movies.contract.MoreMovieContract;
import com.djw.douban.ui.home.movies.presenter.MoreMoviePresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;

public class MoreMovieActivity extends RxToolbarActivity<MoreMoviePresenter> implements MoreMovieContract.View, XRecyclerView.LoadingListener {

    @BindView(R.id.xrv_more)
    XRecyclerView xrvMore;
    private Top250Adapter adapter;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_movie);
    }

    @Override
    public void initView() {
        setToolBarTitle(getString(R.string.dianying));
        xrvMore = (XRecyclerView) findViewById(R.id.xrv_more);
        xrvMore.setLayoutManager(new LinearLayoutManager(this));
        xrvMore.setLoadingListener(this);
        xrvMore.setLoadingMoreProgressStyle(25);
        adapter = new Top250Adapter(this);
        xrvMore.setAdapter(adapter);
    }

    @Override
    protected void scrollToTop() {
        xrvMore.scrollToPosition(0);
    }

    @Override
    public void doBusiness() {
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
        xrvMore.refreshComplete();
        xrvMore.loadMoreComplete();
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
