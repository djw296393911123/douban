package com.djw.douban.ui.home.music.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.djw.douban.R;
import com.djw.douban.base.RxActivity;
import com.djw.douban.data.ParamsData;
import com.djw.douban.data.music.MusicRoot;
import com.djw.douban.ui.home.music.adapter.MusicMoreAdapter;
import com.djw.douban.ui.home.music.contract.MusicMoreContract;
import com.djw.douban.ui.home.music.presenter.MusicMorePresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoreMusicActivity extends RxActivity<MusicMorePresenter> implements MusicMoreContract.View, XRecyclerView.LoadingListener {

    @BindView(R.id.xrv_more)
    XRecyclerView xrvMore;
    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.tl_base)
    Toolbar tlBase;
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
    public void doBusiness() {
        tlBase.setTitle("");
    }

    @Override
    public void inject() {
        getActivityComponent().inject(this);
        mPresenter.attachView(this);
        Bundle bundle = getIntent().getExtras();
        tag = bundle.getString("tag");
        tvToolbarTitle.setText(tag);
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
