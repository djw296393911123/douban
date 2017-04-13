package com.djw.douban.ui.home.movies.presenter;

import com.djw.douban.base.CommonSubscriber;
import com.djw.douban.base.RxPresenter;
import com.djw.douban.data.movies.MoviesItemData;
import com.djw.douban.http.RetrofitHelper;
import com.djw.douban.ui.home.movies.contract.MoviesContract;
import com.djw.douban.util.RxUtil;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by JasonDong on 2017/4/7.
 */

public class HotPresenter extends RxPresenter<MoviesContract.View> implements MoviesContract.Presenter {

    private final RetrofitHelper helper;

    @Inject
    public HotPresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getMoviesList(int start, int count, final boolean isLoadMore) {
        Subscription subscribe = helper.getHotMovies(start, count)
                .compose(RxUtil.<MoviesItemData>rxSchedulerHelper())
                .subscribe(new CommonSubscriber<MoviesItemData>(mView) {
                    @Override
                    public void onNext(MoviesItemData moviesItemData) {
                        mView.showMoviesList(moviesItemData.getSubjects(), isLoadMore);
                    }
                });
        addSubscrebe(subscribe);
    }
}
