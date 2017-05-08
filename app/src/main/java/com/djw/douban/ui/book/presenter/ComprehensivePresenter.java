package com.djw.douban.ui.book.presenter;

import com.djw.douban.base.CommonSubscribers;
import com.djw.douban.base.RxPresenter;
import com.djw.douban.data.book.BookRoot;
import com.djw.douban.data.book.Books;
import com.djw.douban.data.newbook.BookBannerData;
import com.djw.douban.data.newbook.BookBaseData;
import com.djw.douban.data.newbook.BookListData;
import com.djw.douban.http.RetrofitHelper;
import com.djw.douban.ui.book.contract.BookContract;
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
                            List<BookBaseData> list = new ArrayList<>();
                            List<String> url = new ArrayList<>();
                            List<String> title = new ArrayList<>();
                            List<String> id = new ArrayList<>();
                            for (int i = 0; i < 6; i++) {
                                Books books = bookRoot.getBooks().get(i);
                                url.add(books.getImages().getLarge() == null ? books.getImages().getMedium() == null ? books.getImages().getSmall() : books.getImages().getMedium() : books.getImages().getLarge());
                                title.add(books.getTitle());
                                id.add(books.getId());
                            }
                            list.add(new BookBannerData(title, url, id));

                            for (int i = 6; i < bookRoot.getBooks().size(); i++) {
                                Books books = bookRoot.getBooks().get(i);
                                list.add(new BookListData(books.getTitle(), books.getRating().getAverage(), books.getImages().getLarge() == null ? books.getImages().getMedium() == null ? books.getImages().getSmall() : books.getImages().getMedium() : books.getImages().getLarge(), books.getId()));
                            }
                            mView.showBookList(list, false);
                        } else {
                            List<BookBaseData> list = new ArrayList<>();
                            for (int i = 0; i < bookRoot.getBooks().size(); i++) {
                                Books books = bookRoot.getBooks().get(i);
                                list.add(new BookListData(books.getTitle(), books.getRating().getAverage(), books.getImages().getLarge() == null ? books.getImages().getMedium() == null ? books.getImages().getSmall() : books.getImages().getMedium() : books.getImages().getLarge(), books.getId()));
                            }
                            mView.showBookList(list, true);
                        }
                    }
                });
        addSubscrebe(subscribe);
    }
}
