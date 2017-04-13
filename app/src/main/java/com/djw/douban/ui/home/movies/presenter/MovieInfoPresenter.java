package com.djw.douban.ui.home.movies.presenter;

import com.djw.douban.base.CommonSubscriber;
import com.djw.douban.base.RxPresenter;
import com.djw.douban.data.movies.MoviesInfoData;
import com.djw.douban.http.RetrofitHelper;
import com.djw.douban.ui.home.movies.contract.MovieInfoContract;
import com.djw.douban.util.RxUtil;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by JasonDong on 2017/4/7.
 */

public class MovieInfoPresenter extends RxPresenter<MovieInfoContract.View> implements MovieInfoContract.Presenter {

    private final RetrofitHelper helper;

    @Inject
    public MovieInfoPresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getInfo(int id) {
        Subscription subscribe = helper.getMovieInfo(id)
                .compose(RxUtil.<MoviesInfoData>rxSchedulerHelper())
                .subscribe(new CommonSubscriber<MoviesInfoData>(mView) {
                    @Override
                    public void onNext(MoviesInfoData data) {
                        mView.showInfo(data);
                    }
                });
        addSubscrebe(subscribe);
    }
}
