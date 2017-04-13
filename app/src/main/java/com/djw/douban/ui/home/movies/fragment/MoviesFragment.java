package com.djw.douban.ui.home.movies.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.djw.douban.MainActivity;
import com.djw.douban.R;
import com.djw.douban.base.BaseFragment;
import com.djw.douban.data.ParamsData;
import com.djw.douban.data.movies.MoviesHomeData;
import com.djw.douban.ui.home.movies.adapter.HomeRecycleAdapter;
import com.djw.douban.ui.home.movies.contract.MoviesHomeContract;
import com.djw.douban.ui.home.movies.presenter.MoviesPresenter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends BaseFragment<MoviesPresenter> implements MoviesHomeContract.View {

    private HomeRecycleAdapter adapter;

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void initView(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_movies);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new HomeRecycleAdapter(getActivity());
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void doBusiness() {

    }

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
        mPresenter.attachView(this);
        mPresenter.getMoviesOne(ParamsData.START, ParamsData.COUNT);
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_movies;
    }

    @Override
    public void showError(String msg) {

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
    public void showMoviesOne(List<MoviesHomeData> list) {
        adapter.notifyDataChange(list);
    }

}
