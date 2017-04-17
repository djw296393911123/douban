package com.djw.douban.ui.home.movies.contract;

import com.djw.douban.base.BasePresenter;
import com.djw.douban.base.BaseView;
import com.djw.douban.data.movies.MoviesItemData;

import java.util.List;

/**
 * Created by JasonDong on 2017/4/17.
 */

public interface HotContract {

    interface View extends BaseView {

        void showHot(List<MoviesItemData.SubjectsBean> list, boolean isLoadMore);

    }

    interface Presenter extends BasePresenter<View> {

        void getHot(int start, int count, boolean isLoadMore, boolean isShowPress);

    }

}
