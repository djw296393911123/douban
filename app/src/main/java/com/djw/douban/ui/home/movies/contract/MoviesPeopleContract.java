package com.djw.douban.ui.home.movies.contract;

import com.djw.douban.base.BasePresenter;
import com.djw.douban.base.BaseView;
import com.djw.douban.data.movies.MoviesActorsData;

/**
 * Created by JasonDong on 2017/4/7.
 */

public interface MoviesPeopleContract {

    interface View extends BaseView {

        void showPeople(MoviesActorsData data);

    }

    interface Presenter extends BasePresenter<View> {
        void getPeople(int id);
    }

}
