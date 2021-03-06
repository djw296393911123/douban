package com.djw.douban.ui.music.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.djw.douban.R;
import com.djw.douban.base.RxToolbarActivity;
import com.djw.douban.data.ParamsData;
import com.djw.douban.data.newmusic.MusicBaseData;
import com.djw.douban.data.newmusic.MusicListData;
import com.djw.douban.data.newmusic.MusicStyleInfoData;
import com.djw.douban.ui.music.adapter.MusicTypeAdapter;
import com.djw.douban.ui.music.adapter.TopAdapter;
import com.djw.douban.ui.music.contract.ChooseTypeContract;
import com.djw.douban.ui.music.presenter.MusicChooseTypePresenter;
import com.djw.douban.util.CustomPopWindows;
import com.djw.douban.util.TypePopWindows;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

public class ChooseTypeActivity extends RxToolbarActivity<MusicChooseTypePresenter> implements ChooseTypeContract.View, View.OnClickListener, OnRefreshListener, OnLoadMoreListener {

    @BindView(R.id.swipe_target)
    RecyclerView rvChoose;
    @BindView(R.id.rv_type_tope)
    RecyclerView rvType;
    @BindView(R.id.tl_zhihu)
    TabLayout tlZhihu;
    @BindView(R.id.stll_movies)
    SwipeToLoadLayout swipeToLoadLayout;
    private MusicTypeAdapter adapter;
    private String tag;
    private TopAdapter topAdapter;
    private TypePopWindows typePopWindows;
    private int type = 0;
    private CustomPopWindows customPopWindows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_type);
        ButterKnife.bind(this);
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showChooseType(List<MusicListData> list, boolean isLoadMore) {
        if (swipeToLoadLayout.isRefreshing()) {
            swipeToLoadLayout.setRefreshing(false);
        }
        if (swipeToLoadLayout.isLoadingMore()) {
            swipeToLoadLayout.setLoadingMore(false);
        }
        adapter.notifyDataChange(list, isLoadMore);
    }

    @Override
    public void showTopType(List<MusicBaseData> list) {

        topAdapter.notifyDataChange(list,getIntent().getExtras().getInt("position"));

    }

    @Override
    public void showPopData(List<MusicStyleInfoData> list) {
        typePopWindows = new TypePopWindows(this, list) {
            @Override
            public void onItemClicks(String id, String title) {
                int type = Integer.parseInt(id);
                ChooseTypeActivity.this.type = type;
                mPresenter.getChooseType(tag, type, ParamsData.START, ParamsData.COUNT, false, true);
                typePopWindows.dismiss();
            }
        };
    }

    @Override
    public void showRefreshOther(List<MusicStyleInfoData> list) {
        topAdapter.addType(list);
    }

    @Override
    public void initView() {
        initTabLayout();
        rvType.setLayoutManager(new LinearLayoutManager(this));
        topAdapter = new TopAdapter(this) {
            @Override
            public void onTopItemClick(String tag, int where) {
                ChooseTypeActivity.this.tag = tag;
                switch (where) {
                    case MusicBaseData.ONE:
                        mPresenter.getChooseType(tag, type, ParamsData.START, ParamsData.COUNT, false, true);
                        break;
                    case MusicBaseData.TWO:
                        mPresenter.getChooseType(tag, type, ParamsData.START, ParamsData.COUNT, false, true);
                        break;
                    case MusicBaseData.THREE:
                        if (tag.equals("+自定义标签")) {
                            customPopWindows.showAsDropDown(rvType, 5, 5);
                        } else {
                            mPresenter.getChooseType(tag, type, ParamsData.START, ParamsData.COUNT, false, true);
                        }
                        break;
                }
            }
        };
        rvType.setAdapter(topAdapter);
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        rvChoose.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MusicTypeAdapter(this);
        ScaleInAnimationAdapter scaleAdapter = new ScaleInAnimationAdapter(adapter);
        scaleAdapter.setFirstOnly(false);
        rvChoose.setAdapter(scaleAdapter);
    }

    @Override
    protected void scrollToTop() {
        rvChoose.smoothScrollToPosition(0);
    }

    private void initTabLayout() {
        setToolBarTitle(getString(R.string.find));
        View tab_one = LayoutInflater.from(this).inflate(R.layout.item_choose_twoo, null);
        TextView one = (TextView) tab_one.findViewById(R.id.tv_type_one);
        tab_one.setOnClickListener(this);
        one.setText(R.string.welcome);
        View tab_two = LayoutInflater.from(this).inflate(R.layout.item_choose_twoo, null);
        TextView two = (TextView) tab_two.findViewById(R.id.tv_type_one);
        tab_two.setOnClickListener(this);
        two.setText(R.string.shaixuan);
        tlZhihu.addTab(tlZhihu.newTab().setCustomView(tab_one));
        tlZhihu.addTab(tlZhihu.newTab().setCustomView(tab_two));
    }

    @Override
    public void doBusiness() {
        customPopWindows = new CustomPopWindows(this) {
            @Override
            public void onItemClicks(String title) {
                mPresenter.refreshOtherType(title);
                customPopWindows.dismiss();
            }
        };
    }

    @Override
    public void inject() {
        getActivityComponent().inject(this);
        mPresenter.attachView(this);
        Bundle extras = getIntent().getExtras();
        tag = extras.getString("tag");
        int position = extras.getInt("position");
        mPresenter.getChooseType(tag, type, ParamsData.START, ParamsData.COUNT, false, true);
        mPresenter.getTopType(position);
        mPresenter.getPopData();

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

    @Override
    public void onRefresh() {
        mPresenter.getChooseType(tag, type, ParamsData.START, ParamsData.COUNT, false, false);
    }

    @Override
    public void onLoadMore() {
        mPresenter.getChooseType(tag, type, adapter.getItemCount() + 1, ParamsData.COUNT, true, false);
    }

    @Override
    public void onClick(View v) {
        typePopWindows.showAsDropDown(tlZhihu, 5, 5);
    }
}
