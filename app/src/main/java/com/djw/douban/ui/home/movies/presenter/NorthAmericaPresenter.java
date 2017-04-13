package com.djw.douban.ui.home.movies.presenter;

import com.djw.douban.base.CommonSubscriber;
import com.djw.douban.base.RxPresenter;
import com.djw.douban.data.movies.NorthAmericaItemData;
import com.djw.douban.http.RetrofitHelper;
import com.djw.douban.ui.home.movies.contract.NorthAmericaContract;
import com.djw.douban.util.RxUtil;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by JasonDong on 2017/4/7.
 */

public class NorthAmericaPresenter extends RxPresenter<NorthAmericaContract.View> implements NorthAmericaContract.Presenter {

    private final RetrofitHelper helper;

    @Inject
    public NorthAmericaPresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getMoviesList(int start, int count, final boolean isLoadMore) {
        Subscription subscribe = helper.getNorthAmerica(start, count)
                .compose(RxUtil.<NorthAmericaItemData>rxSchedulerHelper())
                .subscribe(new CommonSubscriber<NorthAmericaItemData>(mView) {
                    @Override
                    public void onNext(NorthAmericaItemData northAmericaItemData) {
                        mView.showMoviesList(northAmericaItemData.getSubjects(), isLoadMore);
                    }
                });
        addSubscrebe(subscribe);
    }
}
