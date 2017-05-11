package com.djw.douban.data.calendar;

/**
 * Created by JasonDong on 2017/5/10.
 */

public class CalendarDayData extends CalendarBaseData {

    private String day;

    private boolean isSelect = false;

    private boolean isCur = false;

    public CalendarDayData(String day) {
        super(CalendarBaseData.DAY);
        this.day = day;
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
}
