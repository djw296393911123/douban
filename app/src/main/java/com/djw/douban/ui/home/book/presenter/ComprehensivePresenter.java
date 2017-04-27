package com.djw.douban.ui.home.book.presenter;

import com.djw.douban.base.CommonSubscribers;
import com.djw.douban.base.RxPresenter;
import com.djw.douban.data.book.BannerData;
import com.djw.douban.data.book.BookRoot;
import com.djw.douban.data.book.Books;
import com.djw.douban.http.RetrofitHelper;
import com.djw.douban.ui.home.book.contract.BookContract;
import com.djw.douban.util.RxUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by JasonDong
 * <p>
 * on 2017/4/10.
 */

public class ComprehensivePresenter extends RxPresenter<BookContract.View> implements BookContract.Presenter {

    private final RetrofitHelper helper;

    @Inject
    ComprehensivePresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getBookList(int start, int count, String type, final boolean isLoadMore, final boolean isShowProgress) {
        Subscription subscribe = helper.getBookByTag(type, start, count)
                .compose(RxUtil.<BookRoot>rxSchedulerHelper())
                .subscribe(new CommonSubscribers<BookRoot>(mView, isShowProgress) {
                    @Override
                    public void onNext(BookRoot bookRoot) {
                        if (!isLoadMore) {
                            List<String> url = new ArrayList<>();
                            List<String> title = new ArrayList<>();
                            List<String> id = new ArrayList<>();
                            for (int i = 0; i < 6; i++) {
                                Books books = bookRoot.getBooks().get(i);
                                url.add(books.getImages().getLarge() == null ? books.getImages().getMedium() == null ? books.getImages().getSmall() : books.getImages().getMedium() : books.getImages().getLarge());
                                title.add(books.getTitle());
                                id.add(books.getId());
                            }
                            mView.showBookList(bookRoot.getBooks().subList(6, bookRoot.getBooks().size()), new BannerData(url, title, id), false);
                        } else {
                            mView.showBookList(bookRoot.getBooks(), null, true);
                        }
                    }
                });
        addSubscrebe(subscribe);
    }
}
