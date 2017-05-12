package com.djw.douban.ui.message.contract;

import com.djw.douban.base.BasePresenter;
import com.djw.douban.base.BaseView;
import com.djw.douban.data.calendar.CalendarBaseData;

import java.util.List;

/**
 * Created by JasonDong on 2017/5/10.
 */

public interface CalenderContract {

    interface View extends BaseView {

        void showCalendar(List<CalendarBaseData> list);

    }

    interface Presenter extends BasePresenter<View> {

        void getCalendar(int year, int month, boolean isCurMonth);

        long insertThings(String things, String time);

    }

}
