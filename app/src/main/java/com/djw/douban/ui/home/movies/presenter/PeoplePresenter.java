package com.djw.douban.ui.home.movies.presenter;

import com.djw.douban.base.CommonSubscriber;
import com.djw.douban.base.RxPresenter;
import com.djw.douban.data.movies.MoviesActorsData;
import com.djw.douban.http.RetrofitHelper;
import com.djw.douban.ui.home.movies.contract.MoviesPeopleContract;
import com.djw.douban.util.RxUtil;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by JasonDong on 2017/4/7.
 */

public class PeoplePresenter extends RxPresenter<MoviesPeopleContract.View> implements MoviesPeopleContract.Presenter {

    private final RetrofitHelper helper;

    @Inject
    public PeoplePresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getPeople(int id) {
        Subscription subscribe = helper.getMoviesPeople(id)
                .compose(RxUtil.<MoviesActorsData>rxSchedulerHelper())
                .subscribe(new CommonSubscriber<MoviesActorsData>(mView) {
                    @Override
                    public void onNext(MoviesActorsData data) {
                        mView.showPeople(data);
                    }
                });
        addSubscrebe(subscribe);
    }
}
