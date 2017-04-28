package com.djw.douban.ui.home.music.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.djw.douban.R;
import com.djw.douban.base.RxToolbarActivity;
import com.djw.douban.data.ParamsData;
import com.djw.douban.data.music.MusicRoot;
import com.djw.douban.ui.home.music.adapter.MusicMoreAdapter;
import com.djw.douban.ui.home.music.contract.MusicMoreContract;
import com.djw.douban.ui.home.music.presenter.MusicMorePresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoreMusicActivity extends RxToolbarActivity<MusicMorePresenter> implements MusicMoreContract.View, XRecyclerView.LoadingListener {

    @BindView(R.id.xrv_more)
    XRecyclerView xrvMore;
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
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMore(MusicRoot musicRoot, boolean isLoadMore) {
        xrvMore.loadMoreComplete();
        xrvMore.refreshComplete();
        adapter.notifyDataChange(musicRoot.getMusics(), isLoadMore);
    }

    @Override
    public void initView() {
        xrvMore.setLayoutManager(new LinearLayoutManager(this));
        xrvMore.setLoadingListener(this);
        adapter = new MusicMoreAdapter(this);
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
}
