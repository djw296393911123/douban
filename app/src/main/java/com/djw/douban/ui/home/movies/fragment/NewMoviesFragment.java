package com.djw.douban.ui.home.movies.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.djw.douban.MainActivity;
import com.djw.douban.R;
import com.djw.douban.base.BaseFragment;
import com.djw.douban.data.ParamsData;
import com.djw.douban.data.newmovies.NewMoviesBaseData;
import com.djw.douban.ui.home.movies.adapter.NewMoviesAdapter;
import com.djw.douban.ui.home.movies.contract.NewMoviesContract;
import com.djw.douban.ui.home.movies.presenter.NewMoviesPresenter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewMoviesFragment extends BaseFragment<NewMoviesPresenter> implements NewMoviesContract.View {

    private NewMoviesAdapter adapter;

    private boolean isLoading = false;

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void initView(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_new);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new NewMoviesAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return adapter.isSpan(position);
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                int totalItemCount = recyclerView.getLayoutManager().getItemCount();
                if (lastVisibleItem >= totalItemCount - 2 && dy > 0) {
                    if (!isLoading) {
                        mPresenter.getNewMovies(adapter.getItemCount() + 1, ParamsData.COUNT, true, true);
                        isLoading = true;
                    }
                }
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
        mPresenter.getNewMovies(ParamsData.START, ParamsData.COUNT, false, true);
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
        adapter.notifyDataChange(list, isLoadMore);
        isLoading = false;
    }
}
