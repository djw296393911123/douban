package com.djw.douban.ui.music.activity;

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
import com.djw.douban.data.music.MusicRoot;
import com.djw.douban.ui.music.adapter.MusicMoreAdapter;
import com.djw.douban.ui.music.contract.MusicMoreContract;
import com.djw.douban.ui.music.presenter.MusicMorePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

public class MoreMusicActivity extends RxToolbarActivity<MusicMorePresenter> implements MusicMoreContract.View, OnRefreshListener, OnLoadMoreListener {

    @BindView(R.id.swipe_target)
    RecyclerView rvMore;
    @BindView(R.id.stll_movies)
    SwipeToLoadLayout swipeToLoadLayout;
    private MusicMoreAdapter adapter;
    private String tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_music);
        ButterKnife.bind(this);
    }

    @Override
    public void showError(String msg) {
        refreshOrLoadMoreStop();
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMore(MusicRoot musicRoot, boolean isLoadMore) {
        refreshOrLoadMoreStop();
        adapter.notifyDataChange(musicRoot.getMusics(), isLoadMore);
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
        rvMore.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MusicMoreAdapter(this);
        ScaleInAnimationAdapter scaleAdapter = new ScaleInAnimationAdapter(adapter);
        scaleAdapter.setFirstOnly(false);
        rvMore.setAdapter(scaleAdapter);
    }

    @Override
    protected void scrollToTop() {
        rvMore.smoothScrollToPosition(0);
    }

    @Override
    public void doBusiness() {
    }

    @Override
    public void inject() {
        getActivityComponent().inject(this);
        mPresenter.attachView(this);
        Bundle bundle = getIntent().getExtras();
        tag = bundle.getString("tag");
        setToolBarTitle(tag);
        mPresenter.getMore(tag, ParamsData.START, ParamsData.COUNT, false, true);
    }

    @Override
    public void onRefresh() {
        mPresenter.getMore(tag, ParamsData.START, ParamsData.COUNT, false, false);
    }

    @Override
    public void onLoadMore() {
        mPresenter.getMore(tag, adapter.getItemCount() + 1, ParamsData.COUNT, true, false);
    }

    @Override
    public void dismissProgress() {
        super.dismissProgress();
        if (swipeToLoadLayout.isRefreshing()) {
            swipeToLoadLayout.setRefreshing(false);
        }
        if (swipeToLoadLayout.isLoadingMore()) {
            swipeToLoadLayout.setLoadingMore(false);
        }
    }
}
