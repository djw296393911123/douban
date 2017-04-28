package com.djw.douban.ui.home.movies.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.djw.douban.R;
import com.djw.douban.base.RxToolbarActivity;
import com.djw.douban.data.ParamsData;
import com.djw.douban.data.movies.MoviesItemData;
import com.djw.douban.ui.home.movies.adapter.HotAdapter;
import com.djw.douban.ui.home.movies.contract.HotContract;
import com.djw.douban.ui.home.movies.presenter.HotPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;

public class HotActivity extends RxToolbarActivity<HotPresenter> implements HotContract.View, XRecyclerView.LoadingListener {

    @BindView(R.id.xrv_hot)
    XRecyclerView xrvHot;
    private HotAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot);
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showHot(List<MoviesItemData.SubjectsBean> list, boolean isLoadMore) {
        xrvHot.loadMoreComplete();
        xrvHot.refreshComplete();
        adapter.notifyDataChange(list, isLoadMore);
    }

    @Override
    public void initView() {
        setToolBarTitle(getString(R.string.hot));
        xrvHot.setLayoutManager(new LinearLayoutManager(this));
        xrvHot.setLoadingListener(this);
        xrvHot.setLoadingMoreProgressStyle(25);
        adapter = new HotAdapter(this);
        xrvHot.setAdapter(adapter);
    }

    @Override
    protected void scrollToTop() {
        xrvHot.scrollToPosition(0);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void inject() {
        getActivityComponent().inject(this);
        mPresenter.attachView(this);
        mPresenter.getHot(ParamsData.START, ParamsData.COUNT, false, true);
    }

    @Override
    public void onRefresh() {
        mPresenter.getHot(ParamsData.START, ParamsData.COUNT, false, false);
    }

    @Override
    public void onLoadMore() {
        mPresenter.getHot(adapter.getItemCount() + 1, ParamsData.COUNT, true, false);
    }
}
