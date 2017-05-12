package com.djw.douban.data.things;

/**
 * Created by JasonDong on 2017/5/12.
 */

public class ThingsData extends ThingsBaseData {

    private String time;

    private String things;

    public ThingsData(String time, String things) {
        super(ThingsBaseData.THINGS);
        this.time = time;
        this.things = things;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getThings() {
        return things;
    }

    public void setThings(String things) {
        this.things = things;
    }
}
