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

public interface CommingSoonContract {

    interface View extends BaseView {

        void showCommingSoon(List<MoviesItemData.SubjectsBean> list, boolean isLoadMore);

    }

    interface Presenter extends BasePresenter<View> {

        void getCommingSoon(int start, int count, boolean isLoadMore, boolean isShowProgress);

    }

}
