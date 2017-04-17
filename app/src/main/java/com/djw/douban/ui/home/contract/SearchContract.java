package com.djw.douban.ui.home.contract;

import com.djw.douban.base.BasePresenter;
import com.djw.douban.base.BaseView;
import com.djw.douban.data.movies.MoviesItemData;
import com.djw.douban.data.search.SearchBaseData;
import com.djw.douban.data.search.SearchHistoryData;
import com.djw.douban.data.search.SearchNormalData;

import java.util.List;

/**
 * Created by JasonDong on 2017/4/17.
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
