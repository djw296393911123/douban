package com.djw.douban.ui.home.movies.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
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

import butterknife.BindView;

public class NorthAmericaActivity extends RxActivity<NorthAmericaPresenter> implements NorthAmericaContract.View, XRecyclerView.LoadingListener {

    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.tl_base)
    Toolbar tlBase;
    @BindView(R.id.xrv_north)
    XRecyclerView xrvNorth;
    @BindView(R.id.tv_north)
    TextView tvNorth;
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
    public void showMoviesList(List<NorthAmericaItemData.SubjectsBean> list, String date, boolean isLoadMore) {
        xrvNorth.loadMoreComplete();
        xrvNorth.refreshComplete();
        tvNorth.setText(date);
        adapter.notifyDataChange(list, isLoadMore);
    }

    @Override
    public void initView() {
        tlBase.setTitle("");
        tvToolbarTitle.setText(R.string.northamerica);
        xrvNorth.setLayoutManager(new LinearLayoutManager(this));
        xrvNorth.setLoadingListener(this);
        adapter = new NorthAmericaRecyclerAdapter(this);
        xrvNorth.setAdapter(adapter);
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
