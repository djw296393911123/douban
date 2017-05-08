package com.djw.douban.ui.leader.presenter;

import com.djw.douban.base.CommonSubscribers;
import com.djw.douban.base.RxPresenter;
import com.djw.douban.data.girl.GankListItemData;
import com.djw.douban.http.RetrofitHelper;
import com.djw.douban.ui.leader.contract.LeaderContract;
import com.djw.douban.util.RxUtil;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by JasonDong
 * <p>
 * on 2017/5/5.
 */

public class LeaderPresenter extends RxPresenter<LeaderContract.View> implements LeaderContract.Presenter {

    private final RetrofitHelper helper;

    @Inject
    LeaderPresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getGril() {
        Subscription subscribe = helper.getGirl()
                .compose(RxUtil.<GankListItemData>rxSchedulerHelper())
                .subscribe(new CommonSubscribers<GankListItemData>(mView, false) {
                    @Override
                    public void onNext(GankListItemData gankListItemData) {
                        List<GankListItemData.ResultsBean> results = gankListItemData.getResults();
                        GankListItemData.ResultsBean resultsBean = results.get(0);
                        mView.showGirl(resultsBean.getUrl(), "--by  " + resultsBean.getWho());
                    }
                });
        addSubscrebe(subscribe);
    }
}
