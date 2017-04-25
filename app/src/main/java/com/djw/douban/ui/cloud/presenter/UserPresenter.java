package com.djw.douban.ui.cloud.presenter;

import com.djw.douban.base.CommonSubscribers;
import com.djw.douban.base.RxPresenter;
import com.djw.douban.data.cloud.VisitedData;
import com.djw.douban.http.RetrofitHelper;
import com.djw.douban.ui.cloud.contract.UserContract;
import com.djw.douban.util.RxUtil;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by JasonDong on 2017/4/13.
 */

public class UserPresenter extends RxPresenter<UserContract.View> implements UserContract.Presenter {

    private final RetrofitHelper helper;

    @Inject
    public UserPresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getUser(String id) {
        Subscription subscribe = helper.getVisited(id)
                .compose(RxUtil.<VisitedData>rxSchedulerHelper())
                .subscribe(new CommonSubscribers<VisitedData>(mView, true) {
                    @Override
                    public void onNext(VisitedData visitedData) {
                        mView.showUser(visitedData);
                    }
                });
        addSubscrebe(subscribe);
    }
}
