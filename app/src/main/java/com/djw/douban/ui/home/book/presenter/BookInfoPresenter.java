package com.djw.douban.ui.home.book.presenter;

import com.djw.douban.base.CommonSubscriber;
import com.djw.douban.base.RxPresenter;
import com.djw.douban.data.book.BookInfoData;
import com.djw.douban.http.RetrofitHelper;
import com.djw.douban.ui.home.book.contract.BookInfoContract;
import com.djw.douban.util.RxUtil;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by JasonDong on 2017/4/10.
 */

public class BookInfoPresenter extends RxPresenter<BookInfoContract.View> implements BookInfoContract.Presenter {

    private final RetrofitHelper helper;

    @Inject
    BookInfoPresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getContent(String id) {
        Subscription subscribe = helper.getBookInfo(id)
                .compose(RxUtil.<BookInfoData>rxSchedulerHelper())
                .subscribe(new CommonSubscriber<BookInfoData>(mView) {
                    @Override
                    public void onNext(BookInfoData bookInfoData) {
                        mView.showContent(bookInfoData);
                    }
                });
        addSubscrebe(subscribe);
    }
}
