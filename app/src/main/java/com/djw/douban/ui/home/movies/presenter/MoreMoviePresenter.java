package com.djw.douban.ui.home.movies.presenter;

import com.djw.douban.base.CommonSubscriber;
import com.djw.douban.base.RxPresenter;
import com.djw.douban.data.ParamsData;
import com.djw.douban.data.movies.BannerData;
import com.djw.douban.data.movies.MoviesItemData;
import com.djw.douban.http.RetrofitHelper;
import com.djw.douban.ui.home.movies.contract.MoreMovieContract;
import com.djw.douban.util.RxUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by JasonDong on 2017/4/11.
 */

public class MoreMoviePresenter extends RxPresenter<MoreMovieContract.View> implements MoreMovieContract.Presenter {

    private final RetrofitHelper helper;

    @Inject
    public MoreMoviePresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getMoreMovie(int type, int start, int count, final boolean isLoadMore) {
        switch (type) {
            case ParamsData.ONE:
                Subscription subscribe = helper.getHotMovies(start, count)
                        .compose(RxUtil.<MoviesItemData>rxSchedulerHelper())
                        .subscribe(new CommonSubscriber<MoviesItemData>(mView) {
                            @Override
                            public void onNext(MoviesItemData moviesItemData) {

                                mView.showMoreMovie(moviesItemData.getSubjects(), isLoadMore);
                            }
                        });
                addSubscrebe(subscribe);
                break;
            case ParamsData.TWO:
                Subscription subscribe1 = helper.getCommingSoon(start, count)
                        .compose(RxUtil.<MoviesItemData>rxSchedulerHelper())
                        .subscribe(new CommonSubscriber<MoviesItemData>(mView) {
                            @Override
                            public void onNext(MoviesItemData moviesItemData) {
                                mView.showMoreMovie(moviesItemData.getSubjects(), isLoadMore);
                            }
                        });
                addSubscrebe(subscribe1);
                break;
        }
    }
}
