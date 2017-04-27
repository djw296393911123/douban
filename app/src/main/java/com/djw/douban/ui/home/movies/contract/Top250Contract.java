package com.djw.douban.ui.home.movies.contract;

import com.djw.douban.base.BasePresenter;
import com.djw.douban.base.BaseView;
import com.djw.douban.data.movies.MoviesItemData;

import java.util.List;

/**
 * Created by JasonDong
 * <p>
 * on 2017/4/14.
 */

public interface Top250Contract {
    interface View extends BaseView {

        void showTop250(List<MoviesItemData.SubjectsBean> list, boolean isLoadMore);

    }

    interface Presenter extends BasePresenter<View> {

        void showTop250(int start, int count, boolean isLoadMore, boolean isShowProgress);

    }
}
