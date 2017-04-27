package com.djw.douban.ui.home.music.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.djw.douban.R;
import com.djw.douban.base.RxActivity;
import com.djw.douban.data.ParamsData;
import com.djw.douban.data.newmusic.MusicBaseData;
import com.djw.douban.data.newmusic.MusicListData;
import com.djw.douban.data.newmusic.MusicStyleInfoData;
import com.djw.douban.ui.home.music.adapter.MusicTypeAdapter;
import com.djw.douban.ui.home.music.adapter.TopAdapter;
import com.djw.douban.ui.home.music.contract.ChooseTypeContract;
import com.djw.douban.ui.home.music.presenter.MusicChooseTypePresenter;
import com.djw.douban.util.CustomPopWindows;
import com.djw.douban.util.TypePopWindows;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;

public class ChooseTypeActivity extends RxActivity<MusicChooseTypePresenter> implements ChooseTypeContract.View, XRecyclerView.LoadingListener, View.OnClickListener {

    @BindView(R.id.xrv_choose)
    XRecyclerView xrvChoose;
    @BindView(R.id.rv_type_tope)
    RecyclerView rvType;
    @BindView(R.id.tl_zhihu)
    TabLayout tlZhihu;
    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.tl_base)
    Toolbar tlBase;
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
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showChooseType(List<MusicListData> list, boolean isLoadMore) {
        xrvChoose.refreshComplete();
        xrvChoose.loadMoreComplete();
        adapter.notifyDataChange(list, isLoadMore);
    }

    @Override
    public void showTopType(List<MusicBaseData> list) {
        topAdapter.notifyDataChange(list);

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
        xrvChoose.setLayoutManager(new LinearLayoutManager(this));
        xrvChoose.setLoadingListener(this);
        adapter = new MusicTypeAdapter(this);
        xrvChoose.setAdapter(adapter);
    }

    private void initTabLayout() {
        tlBase.setTitle("");
        tvToolbarTitle.setText(R.string.find);
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
