package com.djw.douban.ui.book.activity;

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
import com.djw.douban.data.book.Books;
import com.djw.douban.ui.book.adapter.BookTagAdapter;
import com.djw.douban.ui.book.contract.BookTagContract;
import com.djw.douban.ui.book.presenter.BookTagPresenter;

import java.util.List;

import butterknife.BindView;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

public class BookFromTagActivity extends RxToolbarActivity<BookTagPresenter> implements BookTagContract.View, OnRefreshListener, OnLoadMoreListener {

    @BindView(R.id.swipe_target)
    RecyclerView rv_book;
    @BindView(R.id.stll_book)
    SwipeToLoadLayout swipeToLoadLayout;
    private BookTagAdapter adapter;
    private String tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_from_tag);
    }

    @Override
    public void showError(String msg) {
        refreshOrLoadMoreStop();
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void initView() {
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        rv_book.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BookTagAdapter();
        ScaleInAnimationAdapter scaleAdapter = new ScaleInAnimationAdapter(adapter);
        scaleAdapter.setFirstOnly(false);
        rv_book.setAdapter(scaleAdapter);
    }

    @Override
    protected void scrollToTop() {
        rv_book.smoothScrollToPosition(0);
    }

    @Override
    protected void doBusiness() {

    }

    @Override
    protected void inject() {
        getActivityComponent().inject(this);
        mPresenter.attachView(this);
        tag = getIntent().getExtras().getString("tag");
        setToolBarTitle(tag);
        mPresenter.getBookFromTag(ParamsData.START, ParamsData.COUNT, tag, false, true);
    }

    @Override
    public void onRefresh() {
        mPresenter.getBookFromTag(ParamsData.START, ParamsData.COUNT, tag, false, false);
    }

    @Override
    public void onLoadMore() {
        mPresenter.getBookFromTag(adapter.getItemCount() + 1, ParamsData.COUNT, tag, true, false);
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
    public void showBookFromTag(List<Books> list, boolean isLoadMore) {
        refreshOrLoadMoreStop();
        adapter.notifyDataChange(list, isLoadMore);
    }
}
