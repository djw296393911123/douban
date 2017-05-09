package com.djw.douban.ui.movies.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.djw.douban.MainActivity;
import com.djw.douban.R;
import com.djw.douban.base.BaseFragment;
import com.djw.douban.data.ParamsData;
import com.djw.douban.data.newmovies.NewMoviesBaseData;
import com.djw.douban.ui.message.MessageActivity;
import com.djw.douban.ui.movies.adapter.NewMoviesAdapter;
import com.djw.douban.ui.movies.contract.NewMoviesContract;
import com.djw.douban.ui.movies.presenter.NewMoviesPresenter;
import com.djw.douban.ui.search.activity.SearchActivity;
import com.djw.douban.zxing.activity.CaptureActivity;

import java.util.List;

import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewMoviesFragment extends BaseFragment<NewMoviesPresenter> implements NewMoviesContract.View, OnRefreshListener, OnLoadMoreListener, View.OnClickListener {

    private NewMoviesAdapter adapter;

    private SwipeToLoadLayout swipeToLoadLayout;
    private RecyclerView recyclerView;

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.tv_search).setOnClickListener(this);
        swipeToLoadLayout = ((SwipeToLoadLayout) view.findViewById(R.id.stll_movies));
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.swipe_target);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new NewMoviesAdapter();
        recyclerView.setAdapter(adapter);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return adapter.isSpan(position);
            }
        });
    }

    @Override
    protected void doBusiness() {

    }

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
        mPresenter.attachView(this);
        mPresenter.getNewMovies(ParamsData.START, ParamsData.COUNT_NEW_MOVIES, false, true);
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_new_movies;
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        ((MainActivity) getActivity()).showProgress();
    }

    @Override
    public void dismissProgress() {
        ((MainActivity) getActivity()).dismissProgress();
    }

    @Override
    public void showNewMovies(List<NewMoviesBaseData> list, boolean isLoadMore) {
        if (swipeToLoadLayout.isRefreshing()) {
            swipeToLoadLayout.setRefreshing(false);
        }
        if (swipeToLoadLayout.isLoadingMore()) {
            swipeToLoadLayout.setLoadingMore(false);
        }
        adapter.notifyDataChange(list, isLoadMore);
    }

    @Override
    public void onRefresh() {
        mPresenter.getNewMovies(ParamsData.START, ParamsData.COUNT_NEW_MOVIES, false, false);
    }

    @Override
    public void onLoadMore() {
        mPresenter.getNewMovies(adapter.getItemCount() + 1, ParamsData.COUNT, true, false);
    }

    public void scrollToTop() {
        recyclerView.smoothScrollToPosition(0);
    }

    @Override
    public void onClick(View v) {
        startActivity(SearchActivity.class);
    }

    @OnClick(R.id.iv_back)
    void startActivityToQrActivity() {
        startActivity(CaptureActivity.class);
    }

    @OnClick(R.id.iv_message)
    void startActivityToMessageActivity() {
        startActivity(MessageActivity.class);
    }

}
