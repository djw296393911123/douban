package com.djw.douban.ui.home.music.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.djw.douban.R;
import com.djw.douban.base.RxActivity;
import com.djw.douban.data.ParamsData;
import com.djw.douban.data.newmusic.MusicBaseData;
import com.djw.douban.ui.home.music.adapter.MusicTypeAdapter;
import com.djw.douban.ui.home.music.contract.ChooseTypeContract;
import com.djw.douban.ui.home.music.presenter.MusicChooseTypePresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;

public class ChooseTypeActivity extends RxActivity<MusicChooseTypePresenter> implements ChooseTypeContract.View, XRecyclerView.LoadingListener {

    @BindView(R.id.xrv_choose)
    XRecyclerView xrvChoose;
    private MusicTypeAdapter adapter;
    private String tag;
    private static int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_type);
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showChooseType(List<MusicBaseData> list, boolean isLoadMore) {
        xrvChoose.refreshComplete();
        xrvChoose.loadMoreComplete();
        adapter.notifyDataChange(list, isLoadMore);
    }

    @Override
    public void showSortData(List<MusicBaseData> list) {

    }

    @Override
    public void initView() {
        xrvChoose.setLayoutManager(new LinearLayoutManager(this));
        xrvChoose.setLoadingListener(this);
        xrvChoose.setPullRefreshEnabled(false);
        adapter = new MusicTypeAdapter(this) {
            @Override
            public void onItemClicks(String tag, int position) {
                ChooseTypeActivity.position = position;
                mPresenter.getChooseType(tag, ParamsData.START, ParamsData.COUNT, false, true, position);

            }

            @Override
            public void onTypeItemClick(String tag, String position) {

            }
        };
        xrvChoose.setAdapter(adapter);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void inject() {
        getActivityComponent().inject(this);
        mPresenter.attachView(this);
        Bundle extras = getIntent().getExtras();
        tag = extras.getString("tag");
        position = extras.getInt("position");
        mPresenter.getChooseType(tag, ParamsData.START, ParamsData.COUNT, false, true, position);
    }

    @Override
    public void onRefresh() {
        mPresenter.getChooseType(tag, ParamsData.START, ParamsData.COUNT, false, false, position);
    }

    @Override
    public void onLoadMore() {
        mPresenter.getChooseType(tag, adapter.getItemCount() + 1, ParamsData.COUNT, true, false, position);
    }
}
