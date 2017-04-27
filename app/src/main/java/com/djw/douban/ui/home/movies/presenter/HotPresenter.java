package com.djw.douban.ui.home.movies.presenter;

import com.djw.douban.base.CommonSubscribers;
import com.djw.douban.base.RxPresenter;
import com.djw.douban.data.movies.MoviesItemData;
import com.djw.douban.http.RetrofitHelper;
import com.djw.douban.ui.home.movies.contract.HotContract;
import com.djw.douban.util.RxUtil;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by JasonDong
 * <p>
 * on 2017/4/7.
 */

public class HotPresenter extends RxPresenter<HotContract.View> implements HotContract.Presenter {

    private final RetrofitHelper helper;

    @Inject
    HotPresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getHot(int start, int count, final boolean isLoadMore, boolean isShowPress) {
        Subscription subscribe = helper.getHotMovies(start, count)
                .compose(RxUtil.<MoviesItemData>rxSchedulerHelper())
                .subscribe(new CommonSubscribers<MoviesItemData>(mView, isShowPress) {
                    @Override
                    public void onNext(MoviesItemData moviesItemData) {
                        mView.showHot(moviesItemData.getSubjects(), isLoadMore);
                    }
                });
        addSubscrebe(subscribe);
    }
}
