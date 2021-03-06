package com.djw.douban.ui.movies.contract;

import com.djw.douban.base.BasePresenter;
import com.djw.douban.base.BaseView;
import com.djw.douban.data.movies.MoviesInfoBaseData;
import com.djw.douban.data.newmovies.MovieInfoTopData;

import java.util.List;

/**
 * Created by JasonDong
 * <p>
 * on 2017/4/7.
 */

public interface MovieInfoContract {

    interface View extends BaseView {

        void showInfo(List<MoviesInfoBaseData> data, MovieInfoTopData topData);

    }

    interface Presenter extends BasePresenter<View> {

        void getInfo(int id);

    }

}
