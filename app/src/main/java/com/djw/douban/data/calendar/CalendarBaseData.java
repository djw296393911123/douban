package com.djw.douban.data.calendar;

/**
 * Created by JasonDong on 2017/5/10.
 */

public class CalendarBaseData {

    public static final int MONTH = 0x9001;

    public static final int DAY = 0x9002;

    public static final int WEEK = 0x9003;

    private int type;

    public CalendarBaseData(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
