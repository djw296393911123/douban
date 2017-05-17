package com.djw.douban.ui.book.presenter;

import com.djw.douban.base.CommonSubscribers;
import com.djw.douban.base.RxPresenter;
import com.djw.douban.data.book.BookRoot;
import com.djw.douban.http.RetrofitHelper;
import com.djw.douban.ui.book.contract.BookTagContract;
import com.djw.douban.util.RxUtil;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by JasonDong
 * <p>
 * on 2017/5/17.
 */

public class BookTagPresenter extends RxPresenter<BookTagContract.View> implements BookTagContract.Presenter {

    private final RetrofitHelper helper;

    @Inject
    BookTagPresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getBookFromTag(int start, int count, String tag, final boolean isLoadMore, boolean isShowProgress) {
        Subscription subscribe = helper.getBookByTag(tag, start, count)
                .compose(RxUtil.<BookRoot>rxSchedulerHelper())
                .subscribe(new CommonSubscribers<BookRoot>(mView, isShowProgress) {
                    @Override
                    public void onNext(BookRoot bookRoot) {
                        mView.showBookFromTag(bookRoot.getBooks(), isLoadMore);
                    }
                });
        addSubscrebe(subscribe);
    }
}
