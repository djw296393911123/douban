package com.djw.douban.ui.home.book.contract;

import com.djw.douban.base.BasePresenter;
import com.djw.douban.base.BaseView;
import com.djw.douban.data.book.BookRoot;

/**
 * Created by JasonDong on 2017/4/10.
 */

public interface BookContract {

    interface View extends BaseView {

        void showBookList(BookRoot bookRoot, boolean isLoadMore);

    }

    interface Presenter extends BasePresenter<View> {

        void getBookList(int start, int count, String type, boolean isLoadMore);

    }

}
