package com.djw.douban.ui.message.presenter;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.djw.douban.base.RxPresenter;
import com.djw.douban.data.calendar.CalendarBaseData;
import com.djw.douban.data.calendar.CalendarDayData;
import com.djw.douban.data.calendar.CalendarWeekData;
import com.djw.douban.data.calendar.CalenderMonthData;
import com.djw.douban.data.message.MessageBaseData;
import com.djw.douban.db.DBHelper;
import com.djw.douban.ui.message.contract.CalenderContract;
import com.djw.douban.util.CalendarUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by JasonDong
 * <p>
 * on 2017/5/10.
 */

public class CalendarPresenter extends RxPresenter<CalenderContract.View> implements CalenderContract.Presenter {

    @Inject
    CalendarPresenter() {
    }

    @Override
    public void getCalendar(int year, int month, boolean isCurMonth) {

        List<CalendarBaseData> list = new ArrayList<>();

        list.add(new CalenderMonthData(month + "月"));

        list.add(new CalendarWeekData());

        for (int i = 0; i < CalendarUtil.getWeek(year, month); i++) {
            list.add(new CalendarDayData(""));
        }

        for (int i = 0; i < CalendarUtil.getDaysByYearMonth(year, month); i++) {
            CalendarDayData data = new CalendarDayData(String.valueOf(i + 1));
            if ((i + 1) == CalendarUtil.getDay() && isCurMonth) {
                data.setSelect(true);
                data.setCur(true);
            }
            list.add(data);
        }

        mView.showCalendar(list);

    }

}
