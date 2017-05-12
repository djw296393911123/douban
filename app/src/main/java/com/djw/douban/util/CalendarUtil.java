package com.djw.douban.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by JasonDong on 2017/5/10.
 */

public class CalendarUtil {
    //  星期
    private static String[] week = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
    //  农历月份
    private static String[] lunarMonth = {"正月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "冬月", "腊月"};
    //  农历日
    private static String[] lunarDay = {"初一", "初二", "初三", "初四", "初五", "初六", "初七", "初八", "初九", "初十",
            "十一", "十二", "十三", "十四", "十五", "十六", "十七", "十八", "十九", "二十",
            "廿一", "廿二", "廿三", "廿四", "廿五", "廿六", "廿七", "廿八", "廿九", "三十"};

    /**
     * 获得当天time点时间戳
     */
    public static long getSignTime(int time) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, time);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (cal.getTimeInMillis() / 1000);
    }

    /**
     * 时间段格式化 hh:mm:ss 用来做倒计时
     */
    public static String timeFormat(long time) {
        int hours = (int) time / 3600;
        String hourStr;
        if (hours < 10) {
            hourStr = "0" + hours;

        } else {
            hourStr = hours + "";
        }
        int min = (int) (time - hours * 3600) / 60;
        String minStr;
        if (min < 10) {
            minStr = "0" + min;

        } else {
            minStr = min + "";
        }
        int second = (int) (time - (time / 60) * 60);
        String secondStr;
        if (second < 10) {
            secondStr = "0" + second;

        } else {
            secondStr = second + "";
        }
        String timeStr = (hourStr + ":" + minStr + ":" + secondStr);

        return timeStr;
    }

    /**
     * 获取年月日 格式yyyy-MM-dd
     */
    public static String getDate() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = simpleDateFormat.format(date);
        return dateStr;
    }

    /**
     * 获取年、月 格式 yyyy-MM
     *
     * @return
     */
    public static String getMonths() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        String month = simpleDateFormat.format(date);
        return month;
    }

    /**
     * 获取当月日期
     *
     * @return DAY of month
     */
    public static int getDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getCurrentMonthDay() {

        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        return a.get(Calendar.DATE);
    }

    /**
     * 根据年 月 获取对应的月份 天数
     */
    public static int getDaysByYearMonth(int year, int month) {

        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        return a.get(Calendar.DATE);
    }

    /**
     * 获取星期几
     */
    public static int getWeek(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }

    /**
     * 获取当前月份
     */
    public static int getMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前年份
     */
    public static String getYear() {
        Calendar calendar = Calendar.getInstance();
        return String.valueOf(calendar.get(Calendar.YEAR));
    }


//    /**
//     * 获取农历月份
//     *
//     * @return
//     */
//    public static String getLunarMonth() {
//        Calendar calendar = Calendar.getInstance();
//        int year = calendar.get(Calendar.YEAR);
//        int month = calendar.get(Calendar.MONTH) + 1;
//        int day = calendar.get(Calendar.DAY_OF_MONTH);
//        int[] lunarDate = LunarCalendar.solarToLunar(year, month, day);
//        return lunarMonth[lunarDate[1] - 1];
//    }
//
//    /**
//     * 获取农历日
//     *
//     * @return
//     */
//    public static String getLunarDay(int d) {
//        Calendar calendar = Calendar.getInstance();
//        int year = calendar.get(Calendar.YEAR);
//        int month = calendar.get(Calendar.MONTH) + 1;
//        int day = calendar.get(Calendar.DAY_OF_MONTH);
//        int[] lunarDate = LunarCalendar.solarToLunar(year, month, day);
//        return lunarDay[lunarDate[2] - 1];
//    }

}
