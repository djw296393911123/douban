package com.djw.douban.ui.cloud.presenter;

import com.djw.douban.base.CommonSubscriber;
import com.djw.douban.base.RxPresenter;
import com.djw.douban.data.cloud.UserData;
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
        Subscription subscribe = helper.getUser(id)
                .compose(RxUtil.<UserData>rxSchedulerHelper())
                .subscribe(new CommonSubscriber<UserData>(mView) {
                    @Override
                    public void onNext(UserData userData) {
                        mView.showUser(userData);
                    }
                });
        addSubscrebe(subscribe);
    }
}
