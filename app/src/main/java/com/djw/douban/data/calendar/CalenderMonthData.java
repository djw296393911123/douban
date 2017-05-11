package com.djw.douban.data.calendar;

/**
 * Created by JasonDong on 2017/5/10.
 */

public class CalenderMonthData extends CalendarBaseData {

    private String month;

    public CalenderMonthData(String month) {
        super(CalendarBaseData.MONTH);
        this.month = month;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
