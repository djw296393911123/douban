package com.djw.douban.ui.book.contract;

import com.djw.douban.base.BasePresenter;
import com.djw.douban.base.BaseView;
import com.djw.douban.data.book.Books;
import com.djw.douban.data.newbook.BookListData;

import java.util.List;

/**
 * Created by JasonDong on 2017/5/17.
 */

public interface BookTagContract {

    interface View extends BaseView {

        void showBookFromTag(List<Books> list,boolean isLoadMore);

    }

    interface Presenter extends BasePresenter<View> {

        void getBookFromTag(int start, int count, String tag, boolean isLoadMore, boolean isShowProgress);

    }

}
