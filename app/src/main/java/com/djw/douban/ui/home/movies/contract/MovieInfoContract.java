package com.djw.douban.ui.home.movies.contract;

import com.djw.douban.base.BasePresenter;
import com.djw.douban.base.BaseView;
import com.djw.douban.data.movies.MoviesInfoData;

/**
 * Created by JasonDong on 2017/4/7.
 */

public interface MovieInfoContract {

    interface View extends BaseView {

        void showInfo(MoviesInfoData data);

    }

    interface Presenter extends BasePresenter<View> {

        void getInfo(int id);

    }

}
