package com.djw.douban.ui.book.contract;

import com.djw.douban.base.BasePresenter;
import com.djw.douban.base.BaseView;
import com.djw.douban.data.newbook.BookBaseData;

import java.util.List;

/**
 * Created by JasonDong
 * <p>
 * on 2017/4/10.
 */

public interface BookContract {

    interface View extends BaseView {

        void showBookList(List<BookBaseData> books, boolean isLoadMore);

    }

    interface Presenter extends BasePresenter<View> {

        void getBookList(int start, int count, String type, boolean isLoadMore, boolean isShowProgress);

    }

}
