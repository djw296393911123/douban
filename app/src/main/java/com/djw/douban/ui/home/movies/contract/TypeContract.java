package com.djw.douban.ui.home.movies.contract;

import com.djw.douban.base.BasePresenter;
import com.djw.douban.base.BaseView;
import com.djw.douban.data.movies.TypeData;

import java.util.List;

/**
 * Created by JasonDong on 2017/4/17.
 */

public interface TypeContract {

    interface View extends BaseView {

        void showType(List<TypeData.SubjectsBean> list, boolean isLoadMore);

    }

    interface Presenter extends BasePresenter<View> {

        void getType(String q, int start, int count, boolean isLoadMore, boolean isShowProgress);

    }

}
