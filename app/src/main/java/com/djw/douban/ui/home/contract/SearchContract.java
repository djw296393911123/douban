package com.djw.douban.ui.home.contract;

import com.djw.douban.base.BasePresenter;
import com.djw.douban.base.BaseView;
import com.djw.douban.data.search.SearchBaseData;

import java.util.List;

/**
 * Created by JasonDong
 * <p>
 * on 2017/4/17.
 */

public interface SearchContract {

    interface View extends BaseView {

        void showHotSearch(List<SearchBaseData> list);

        void showSearchData(List<SearchBaseData> list);

    }

    interface Presenter extends BasePresenter<View> {

        void getHotSearch(int start, int count);

        void getSearchData(String tag, int start, int count);

    }

}
