package com.djw.douban.ui.message.presenter;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.djw.douban.base.RxPresenter;
import com.djw.douban.data.calendar.CalendarBaseData;
import com.djw.douban.data.calendar.CalendarDayData;
import com.djw.douban.data.calendar.CalendarWeekData;
import com.djw.douban.data.calendar.CalenderMonthData;
import com.djw.douban.data.things.ThingsBaseData;
import com.djw.douban.data.things.ThingsData;
import com.djw.douban.data.things.ThingsNormalData;
import com.djw.douban.db.DBHelper;
import com.djw.douban.ui.message.contract.CalenderContract;
import com.djw.douban.util.CalendarUtil;
import com.djw.douban.util.LunarCalendar;

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

    @Inject
    DBHelper dbHelper;

    @Override
    public void getCalendar(int year, int month, boolean isCurMonth) {

        LunarCalendar lunarCalendar = new LunarCalendar();

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        List<CalendarBaseData> list = new ArrayList<>();

        list.add(new CalenderMonthData(month + "月"));

        list.add(new CalendarWeekData());

        int before = CalendarUtil.getDaysByYearMonth(year, month - 1);
        int first = CalendarUtil.getWeek(year, month);
        for (int i = first; i > 0; i--) {
            int day = before - i + 1;
            String time = year + "-" + (month - 1) + "-" + day;
            Cursor cursor = database.query(DBHelper.TABLE_THING, null, DBHelper.TIME + "=?", new String[]{time}, null, null, null);
            List<ThingsBaseData> things = new ArrayList<>();
            while (cursor.moveToNext()) {
                String string = cursor.getString(cursor.getColumnIndex(DBHelper.THINGS));
                things.add(new ThingsData(time, string));
            }
            things.add(new ThingsNormalData());
            cursor.close();
            String lunarString = lunarCalendar.getLunarString(month == 1 ? year - 1 : year, month == 1 ? 12 : month - 1, day);
            String[] split = lunarString.split(",");
            if (split.length > 1) things.add(new ThingsData(split[1], "全天"));
            list.add(new CalendarDayData(String.valueOf(day), lunarString, false, things, month - 1, year));
        }

        int content = CalendarUtil.getDaysByYearMonth(year, month);
        for (int i = 0; i < content; i++) {
            String time = year + "-" + month + "-" + i + 1;
            Cursor cursor = database.query(DBHelper.TABLE_THING, null, DBHelper.TIME + " = ?", new String[]{time}, null, null, null);
            List<ThingsBaseData> things = new ArrayList<>();
            while (cursor.moveToNext()) {
                String string = cursor.getString(cursor.getColumnIndex(DBHelper.THINGS));
                things.add(new ThingsData(time, string));
            }
            things.add(new ThingsNormalData());
            cursor.close();
            String lunarString = lunarCalendar.getLunarString(year, month, i + 1);
            String[] split = lunarString.split(",");
            if (split.length > 1) things.add(new ThingsData(split[1], "全天"));
            CalendarDayData data = new CalendarDayData(String.valueOf(i + 1), lunarString, true, things, month, year);
            if ((i + 1) == CalendarUtil.getDay() && isCurMonth) {
                data.setSelect(true);
                data.setCur(true);
            }
            list.add(data);
        }

        int end = first + content > 35 ? 42 : 35;

        for (int i = 0; i < end - first - content; i++) {
            String time = year + "-" + (month + 1) + "-" + i + 1;
            Cursor cursor = database.query(DBHelper.TABLE_THING, null, DBHelper.TIME + "=?", new String[]{time}, null, null, null);
            List<ThingsBaseData> things = new ArrayList<>();
            while (cursor.moveToNext()) {
                String string = cursor.getString(cursor.getColumnIndex(DBHelper.THINGS));
                things.add(new ThingsData(time, string));
            }
            things.add(new ThingsNormalData());
            cursor.close();
            String lunarString = lunarCalendar.getLunarString(month == 12 ? year + 1 : year, month == 12 ? 1 : month + 1, i + 1);
            String[] split = lunarString.split(",");
            if (split.length > 1) things.add(new ThingsData(split[1], "全天"));

            list.add(new CalendarDayData(String.valueOf(i + 1), lunarString, false, things, month, year));
        }

        mView.showCalendar(list);

    }

    @Override
    public long insertThings(String things, String time) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.THINGS, things);
        values.put(DBHelper.TIME, time);
        return database.insert(DBHelper.TABLE_THING, null, values);
    }

}
