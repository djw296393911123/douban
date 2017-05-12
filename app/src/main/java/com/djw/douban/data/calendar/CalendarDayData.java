package com.djw.douban.data.calendar;

import com.djw.douban.data.things.ThingsBaseData;

import java.util.List;

/**
 * Created by JasonDong on 2017/5/10.
 */

public class CalendarDayData extends CalendarBaseData {

    private String day;

    private int month;

    private int year;

    private boolean isSelect = false;

    private boolean isCur = false;

    private String lunar;

    private boolean isCurMonth;

    private List<ThingsBaseData> list;

    public CalendarDayData(String day, String lunar, boolean isCurMonth, List<ThingsBaseData> list, int month, int year) {
        super(CalendarBaseData.DAY);
        this.day = day;
        this.lunar = lunar;
        this.isCurMonth = isCurMonth;
        this.list = list;
        this.month = month;
        this.year = year;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public boolean isCur() {
        return isCur;
    }

    public void setCur(boolean cur) {
        isCur = cur;
    }

    public String getLunar() {
        return lunar;
    }

    public void setLunar(String lunar) {
        this.lunar = lunar;
    }

    public boolean isCurMonth() {
        return isCurMonth;
    }

    public void setCurMonth(boolean curMonth) {
        isCurMonth = curMonth;
    }

    public List<ThingsBaseData> getList() {
        return list;
    }

    public void setList(List<ThingsBaseData> list) {
        this.list = list;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
