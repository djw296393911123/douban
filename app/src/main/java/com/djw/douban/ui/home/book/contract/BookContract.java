package com.djw.douban.ui.home.book.contract;

import com.djw.douban.base.BasePresenter;
import com.djw.douban.base.BaseView;
import com.djw.douban.data.book.BannerData;
import com.djw.douban.data.book.BookRoot;
import com.djw.douban.data.book.Books;

import java.util.List;

/**
 * Created by JasonDong on 2017/4/10.
 */

public interface BookContract {

    interface View extends BaseView {

        void showBookList(List<Books> books, BannerData bannerData, boolean isLoadMore);

    }

    interface Presenter extends BasePresenter<View> {

        void getBookList(int start, int count, String type, boolean isLoadMore, boolean isShowProgress);

    }

}
