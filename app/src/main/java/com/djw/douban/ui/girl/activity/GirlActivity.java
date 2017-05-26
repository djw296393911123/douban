package com.djw.douban.ui.girl.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.azoft.carousellayoutmanager.CenterScrollListener;
import com.djw.douban.R;
import com.djw.douban.base.RxToolbarActivity;
import com.djw.douban.data.girl.GirlBaseData;
import com.djw.douban.ui.girl.adapter.GirlAdapter;
import com.djw.douban.ui.girl.contract.GirlContract;
import com.djw.douban.ui.girl.presenter.GirlPresenter;

import java.util.List;

import butterknife.BindView;

public class GirlActivity extends RxToolbarActivity<GirlPresenter> implements GirlContract.View {

    @BindView(R.id.rv_girl)
    RecyclerView rvGirl;
    private GirlAdapter adapter;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_girl);
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showGirl(List<GirlBaseData> list, boolean isLoadMore) {
        adapter.notifyDataChange(list, isLoadMore);
    }

    @Override
    protected void initView() {
        setToolBarTitle("画廊");
        CarouselLayoutManager layoutManager = new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL);
        layoutManager.setMaxVisibleItems(1);
        rvGirl.setLayoutManager(layoutManager);
        rvGirl.setHasFixedSize(true);
        rvGirl.addOnScrollListener(new CenterScrollListener());
        layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());
        adapter = new GirlAdapter(this) {
            @Override
            public void loadMore() {
                mPresenter.getGirl(++page, true, true);
            }
        };
        rvGirl.setAdapter(adapter);
    }

    @Override
    protected void scrollToTop() {

    }

    @Override
    protected void doBusiness() {

    }

    @Override
    protected void inject() {
        getActivityComponent().inject(this);
        mPresenter.attachView(this);
        mPresenter.getGirl(page, false, true);
    }
}
