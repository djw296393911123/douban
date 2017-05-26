package com.djw.douban.ui.girl.presenter;

import com.djw.douban.base.CommonSubscribers;
import com.djw.douban.base.RxPresenter;
import com.djw.douban.data.girl.GankListItemData;
import com.djw.douban.data.girl.GirlBaseData;
import com.djw.douban.data.girl.GirlMoreData;
import com.djw.douban.data.girl.GirlNormalData;
import com.djw.douban.http.RetrofitHelper;
import com.djw.douban.ui.girl.contract.GirlContract;
import com.djw.douban.util.RxUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by JasonDong
 * <p>
 * on 2017/5/26.
 */

public class GirlPresenter extends RxPresenter<GirlContract.View> implements GirlContract.Presenter {

    private final RetrofitHelper helper;

    @Inject
    GirlPresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getGirl(int page, final boolean isLoadMore, boolean isShowProgress) {
        Subscription subscribe = helper.getGirls(page)
                .compose(RxUtil.<GankListItemData>rxSchedulerHelper())
                .subscribe(new CommonSubscribers<GankListItemData>(mView, isShowProgress) {
                    @Override
                    public void onNext(GankListItemData gankListItemData) {
                        List<GirlBaseData> list = new ArrayList<>();
                        List<GankListItemData.ResultsBean> results = gankListItemData.getResults();
                        for (int i = 0; i < results.size(); i++) {
                            list.add(new GirlNormalData(results.get(i).getUrl()));
                        }
                        list.add(new GirlMoreData());
                        mView.showGirl(list, isLoadMore);
                    }
                });
        addSubscrebe(subscribe);
    }
}
