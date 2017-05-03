package com.djw.douban.ui.movies.contract;

import com.djw.douban.base.BasePresenter;
import com.djw.douban.base.BaseView;
import com.djw.douban.data.movies.MoviesItemData;

import java.util.List;

/**
 * Created by JasonDong
 * <p>
 * on 2017/4/11.
 */

public interface MoreMovieContract {

    interface View extends BaseView {

        void showMoreMovie(List<MoviesItemData.SubjectsBean> list, boolean isLoadMore);

    }

    interface Presenter extends BasePresenter<View> {

        void getMoreMovie(int type, int start, int count, boolean isLoadMore);

    }

}
