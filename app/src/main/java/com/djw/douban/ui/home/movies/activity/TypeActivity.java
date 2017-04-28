package com.djw.douban.ui.home.movies.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.djw.douban.R;
import com.djw.douban.base.RxToolbarActivity;
import com.djw.douban.data.ParamsData;
import com.djw.douban.data.movies.TypeData;
import com.djw.douban.ui.home.movies.adapter.TypeAdapter;
import com.djw.douban.ui.home.movies.contract.TypeContract;
import com.djw.douban.ui.home.movies.presenter.TypePresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;

public class TypeActivity extends RxToolbarActivity<TypePresenter> implements TypeContract.View, XRecyclerView.LoadingListener {

    @BindView(R.id.xrv_type)
    XRecyclerView xrvType;
    private TypeAdapter adapter;
    private String q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showType(List<TypeData.SubjectsBean> list, boolean isLoadMore) {
        xrvType.refreshComplete();
        xrvType.loadMoreComplete();
        adapter.notifyDataChange(list, isLoadMore);
    }

    @Override
    public void initView() {
        xrvType.setLayoutManager(new LinearLayoutManager(this));
        xrvType.setLoadingListener(this);
        xrvType.setLoadingMoreProgressStyle(25);
        adapter = new TypeAdapter(this);
        xrvType.setAdapter(adapter);
    }

    @Override
    protected void scrollToTop() {
        xrvType.scrollToPosition(0);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void inject() {
        getActivityComponent().inject(this);
        mPresenter.attachView(this);
        q = getIntent().getExtras().getString("q");
        setToolBarTitle(q);
        mPresenter.getType(q, ParamsData.START, ParamsData.COUNT, false, true);
    }

    @Override
    public void onRefresh() {
        mPresenter.getType(q, ParamsData.START, ParamsData.COUNT, false, false);
    }

    @Override
    public void onLoadMore() {
        mPresenter.getType(q, adapter.getItemCount() + 1, ParamsData.COUNT, true, false);
    }
}
