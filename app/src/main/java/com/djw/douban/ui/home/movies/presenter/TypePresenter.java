package com.djw.douban.ui.home.movies.presenter;

import com.djw.douban.base.CommonSubscribers;
import com.djw.douban.base.RxPresenter;
import com.djw.douban.data.movies.TypeData;
import com.djw.douban.http.RetrofitHelper;
import com.djw.douban.ui.home.movies.contract.TypeContract;
import com.djw.douban.util.RxUtil;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by JasonDong
 * <p>
 * on 2017/4/17.
 */

public class TypePresenter extends RxPresenter<TypeContract.View> implements TypeContract.Presenter {

    private final RetrofitHelper helper;

    @Inject
    TypePresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getType(String q, int start, int count, final boolean isLoadMore, boolean isShowProgress) {
        Subscription subscribe = helper.getType(q, start, count)
                .compose(RxUtil.<TypeData>rxSchedulerHelper())
                .subscribe(new CommonSubscribers<TypeData>(mView, isShowProgress) {
                    @Override
                    public void onNext(TypeData typeData) {
                        mView.showType(typeData.getSubjects(), isLoadMore);
                    }
                });
        addSubscrebe(subscribe);
    }
}
