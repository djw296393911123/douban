package com.djw.douban.ui.movies.presenter;

import com.djw.douban.base.CommonSubscribers;
import com.djw.douban.base.RxPresenter;
import com.djw.douban.data.movies.NorthAmericaItemData;
import com.djw.douban.http.RetrofitHelper;
import com.djw.douban.ui.movies.contract.NorthAmericaContract;
import com.djw.douban.util.RxUtil;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by JasonDong
 * <p>
 * on 2017/4/7.
 */

public class NorthAmericaPresenter extends RxPresenter<NorthAmericaContract.View> implements NorthAmericaContract.Presenter {

    private final RetrofitHelper helper;

    @Inject
    NorthAmericaPresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getMoviesList(int start, int count, final boolean isLoadMore, boolean isShowProgress) {
        Subscription subscribe = helper.getNorthAmerica(start, count)
                .compose(RxUtil.<NorthAmericaItemData>rxSchedulerHelper())
                .subscribe(new CommonSubscribers<NorthAmericaItemData>(mView, isShowProgress) {
                    @Override
                    public void onNext(NorthAmericaItemData northAmericaItemData) {
                        mView.showMoviesList(northAmericaItemData.getSubjects(), northAmericaItemData.getDate(), isLoadMore);
                    }
                });
        addSubscrebe(subscribe);
    }
}
