package com.djw.douban.ui.home.movies.contract;

import com.djw.douban.base.BasePresenter;
import com.djw.douban.base.BaseView;
import com.djw.douban.data.movies.PeopleBaseData;
import com.djw.douban.data.movies.PeopleOne;
import com.djw.douban.data.movies.PeopleThree;

import java.util.List;

/**
 * Created by JasonDong
 * <p>
 * on 2017/4/7.
 */

public interface MoviesPeopleContract {

    interface View extends BaseView {

        void showPeople(PeopleOne peopleOne, List<PeopleBaseData> list, String title);

    }

    interface Presenter extends BasePresenter<View> {
        void getPeople(int id);
    }

}
