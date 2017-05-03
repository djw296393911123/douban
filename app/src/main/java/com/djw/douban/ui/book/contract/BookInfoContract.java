package com.djw.douban.ui.book.contract;

import com.djw.douban.base.BasePresenter;
import com.djw.douban.base.BaseView;
import com.djw.douban.data.book.BookInfoData;

/**
 * Created by
 * <p>
 * JasonDong on 2017/4/10.
 */

public interface BookInfoContract {

    interface View extends BaseView {

        void showContent(BookInfoData bookInfoData);

    }

    interface Presenter extends BasePresenter<View> {

        void getContent(String id);

    }

}
