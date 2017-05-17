package com.djw.douban.data.calendar;

/**
 * Created by JasonDong on 2017/5/10.
 */

public class CalenderMonthData extends CalendarBaseData {

    private String month;

    private String year;

    public CalenderMonthData(String month, String year) {
        super(CalendarBaseData.MONTH);
        this.month = month;
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
