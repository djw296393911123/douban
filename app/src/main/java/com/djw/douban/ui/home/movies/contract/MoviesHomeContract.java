package com.djw.douban.ui.home.movies.contract;

import com.djw.douban.base.BasePresenter;
import com.djw.douban.base.BaseView;
import com.djw.douban.data.movies.MoviesHomeData;

import java.util.List;

/**
 * Created by JasonDong on 2017/4/7.
 */

public interface MoviesHomeContract {

    interface View extends BaseView {

        void showMoviesOne(List<MoviesHomeData> list);

    }

    interface Presenter extends BasePresenter<View> {

        void getMoviesOne(int start, int count);

    }

}
