package com.djw.douban.ui.home.movies.presenter;

import com.djw.douban.base.CommonSubscribers;
import com.djw.douban.base.RxPresenter;
import com.djw.douban.data.movies.MoviesItemData;
import com.djw.douban.http.RetrofitHelper;
import com.djw.douban.ui.home.movies.contract.Top250Contract;
import com.djw.douban.util.RxUtil;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by JasonDong
 * <p>
 * on 2017/4/7.
 */

public class Top250Presenter extends RxPresenter<Top250Contract.View> implements Top250Contract.Presenter {

    private final RetrofitHelper helper;

    @Inject
    Top250Presenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void showTop250(int start, int count, final boolean isLoadMore, boolean isShowProgress) {
        Subscription subscribe = helper.getTop250(start, count)
                .compose(RxUtil.<MoviesItemData>rxSchedulerHelper())
                .subscribe(new CommonSubscribers<MoviesItemData>(mView, isShowProgress) {
                    @Override
                    public void onNext(MoviesItemData moviesItemData) {
                        mView.showTop250(moviesItemData.getSubjects(), isLoadMore);
                    }
                });
        addSubscrebe(subscribe);
    }
}
