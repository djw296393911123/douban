package com.djw.douban.ui.movies.contract;

import com.djw.douban.base.BasePresenter;
import com.djw.douban.base.BaseView;
import com.djw.douban.data.newmovies.NewMoviesBaseData;

import java.util.List;

/**
 * Created by JasonDong
 * <p>
 * on 2017/4/14.
 */

public interface NewMoviesContract {

    interface View extends BaseView {

        void showNewMovies(List<NewMoviesBaseData> list, boolean isLoadMore);

    }

    interface Presenter extends BasePresenter<View> {

        void getNewMovies(int start, int count, boolean isLoadMore, boolean isShowProgress);

    }

}
