package com.djw.douban.ui.cloud.contract;

import com.djw.douban.base.BasePresenter;
import com.djw.douban.base.BaseView;
import com.djw.douban.data.cloud.CityData;
import com.djw.douban.data.cloud.CloudItemData;
import com.djw.douban.data.cloud.CloudLocData;

import java.util.List;

/**
 * Created by JasonDong on 2017/4/12.
 */

public interface CloudContract {

    interface View extends BaseView {

        void showCity(List<CityData> list);

        void showActivitys(List<CloudItemData.EventsBean> list, boolean isLoadMore);

        void showType(List<CityData> list);

        void showTypeDay(List<CityData> list);
    }

    interface Presenter extends BasePresenter<View> {

        void getCity();

        void getActivitys(String loc, String type, String day_type, int start, int count, boolean isLoadMore, boolean isShowProgress);

        void getType();

        void getTypeData();
    }

}
