package com.djw.douban.ui.home.movies.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.djw.douban.R;
import com.djw.douban.base.BaseFragment;
import com.djw.douban.data.ParamsData;
import com.djw.douban.data.movies.MoviesItemData;
import com.djw.douban.ui.home.movies.adapter.MoviesRecyclerAdapter;
import com.djw.douban.ui.home.movies.contract.MoviesContract;
import com.djw.douban.ui.home.movies.presenter.Top250Presenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Top250Fragment extends BaseFragment<Top250Presenter> implements MoviesContract.View, XRecyclerView.LoadingListener {


    private MoviesRecyclerAdapter adapter;
    private XRecyclerView recyclerView;

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void initView(View view) {
        recyclerView = (XRecyclerView) view.findViewById(R.id.rv_movies);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        adapter = new MoviesRecyclerAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLoadingListener(this);
    }

    @Override
    protected void doBusiness() {

    }

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
        mPresenter.attachView(this);
        mPresenter.getMoviesList(ParamsData.START, ParamsData.COUNT, false);
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_hot;
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {
        recyclerView.refreshComplete();
        recyclerView.loadMoreComplete();
    }

    @Override
    public void showMoviesList(List<MoviesItemData.SubjectsBean> subjectsBeen, boolean isLoadMore) {
//        adapter.notifyDataChange(subjectsBeen, isLoadMore);
    }

    @Override
    public void onRefresh() {
        mPresenter.getMoviesList(ParamsData.START, ParamsData.COUNT, false);
    }

    @Override
    public void onLoadMore() {
        mPresenter.getMoviesList(adapter.getItemCount() + 1, ParamsData.COUNT, true);
    }
}
