package com.djw.douban.ui.home.book.presenter;

import com.djw.douban.base.CommonSubscriber;
import com.djw.douban.base.CommonSubscribers;
import com.djw.douban.base.RxPresenter;
import com.djw.douban.data.book.BookRoot;
import com.djw.douban.http.RetrofitHelper;
import com.djw.douban.ui.home.book.contract.BookContract;
import com.djw.douban.util.RxUtil;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by JasonDong on 2017/4/10.
 */

public class ComprehensivePresenter extends RxPresenter<BookContract.View> implements BookContract.Presenter {

    private final RetrofitHelper helper;

    @Inject
    public ComprehensivePresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getBookList(int start, int count, String type, final boolean isLoadMore, boolean isShowProgress) {
        Subscription subscribe = helper.getBookByTag(type, start, count)
                .compose(RxUtil.<BookRoot>rxSchedulerHelper())
                .subscribe(new CommonSubscribers<BookRoot>(mView, isShowProgress) {
                    @Override
                    public void onNext(BookRoot bookRoot) {
                        mView.showBookList(bookRoot, isLoadMore);
                    }
                });
        addSubscrebe(subscribe);
    }
}
