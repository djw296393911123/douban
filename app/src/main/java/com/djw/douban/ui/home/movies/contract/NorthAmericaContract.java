package com.djw.douban.ui.home.movies.contract;

import com.djw.douban.base.BasePresenter;
import com.djw.douban.base.BaseView;
import com.djw.douban.data.movies.NorthAmericaItemData;

import java.util.List;

/**
 * Created by JasonDong on 2017/4/7.
 */

public interface NorthAmericaContract {

    interface View extends BaseView {

        void showMoviesList(List<NorthAmericaItemData.SubjectsBean> list, boolean isLoadMore);

    }

    interface Presenter extends BasePresenter<View> {

        void getMoviesList(int start, int count, boolean isLoadMore);

    }

}
