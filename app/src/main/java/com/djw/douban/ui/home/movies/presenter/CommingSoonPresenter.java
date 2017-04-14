package com.djw.douban.ui.home.movies.presenter;

import com.djw.douban.base.ApiException;
import com.djw.douban.base.CommonSubscriber;
import com.djw.douban.base.CommonSubscribers;
import com.djw.douban.base.RxPresenter;
import com.djw.douban.data.movies.MoviesItemData;
import com.djw.douban.http.RetrofitHelper;
import com.djw.douban.ui.home.movies.contract.CommingSoonContract;
import com.djw.douban.ui.home.movies.contract.MoviesContract;
import com.djw.douban.util.RxUtil;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by JasonDong on 2017/4/7.
 */

public class CommingSoonPresenter extends RxPresenter<CommingSoonContract.View> implements CommingSoonContract.Presenter {

    private final RetrofitHelper helper;

    @Inject
    public CommingSoonPresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getCommingSoon(int start, int count, final boolean isLoadMore, boolean isShowProgress) {
        Subscription subscribe = helper.getCommingSoon(start, count)
                .compose(RxUtil.<MoviesItemData>rxSchedulerHelper())
                .subscribe(new CommonSubscribers<MoviesItemData>(mView, isShowProgress) {
                    @Override
                    public void onNext(MoviesItemData moviesItemData) {
                        mView.showCommingSoon(moviesItemData.getSubjects(), isLoadMore);
                    }
                });
        addSubscrebe(subscribe);
    }
}
