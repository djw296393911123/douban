package com.djw.douban.ui.home.movies.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.djw.douban.R;
import com.djw.douban.base.RxActivity;
import com.djw.douban.data.ParamsData;
import com.djw.douban.data.movies.MoviesItemData;
import com.djw.douban.ui.home.movies.adapter.HotAdapter;
import com.djw.douban.ui.home.movies.contract.HotContract;
import com.djw.douban.ui.home.movies.presenter.HotPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;

public class HotActivity extends RxActivity<HotPresenter> implements HotContract.View, XRecyclerView.LoadingListener {

    @BindView(R.id.xrv_hot)
    XRecyclerView xrvHot;
    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.tl_base)
    Toolbar tlBase;
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
        tlBase.setTitle("");
        tvToolbarTitle.setText(R.string.hot);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                Log.i("position", position + "");
                return 1;
            }
        });
        xrvHot.setLayoutManager(layoutManager);
        xrvHot.setLoadingListener(this);
        adapter = new HotAdapter(this);
        xrvHot.setAdapter(adapter);
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
