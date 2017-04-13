package com.djw.douban.ui.mine.presenter;

import com.djw.douban.base.CommonSubscribers;
import com.djw.douban.base.RxPresenter;
import com.djw.douban.data.mine.MineItemData;
import com.djw.douban.http.RetrofitHelper;
import com.djw.douban.ui.mine.contract.OnlineContract;
import com.djw.douban.util.RxUtil;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by JasonDong on 2017/4/13.
 */

public class OnlinePresenter extends RxPresenter<OnlineContract.View> implements OnlineContract.Presenter {

    private final RetrofitHelper helper;

    @Inject
    public OnlinePresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getOnline(String cate) {
        Subscription subscribe = helper.getOnline(cate)
                .compose(RxUtil.<MineItemData>rxSchedulerHelper())
                .subscribe(new CommonSubscribers<MineItemData>(mView, false) {
                    @Override
                    public void onNext(MineItemData mineItemData) {
                        mView.showOnline(mineItemData);
                    }
                });
        addSubscrebe(subscribe);
    }
}
