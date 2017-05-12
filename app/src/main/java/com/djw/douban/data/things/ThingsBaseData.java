package com.djw.douban.data.things;

/**
 * Created by JasonDong on 2017/5/12.
 */

public class ThingsBaseData {

    public static final int NORMAL = 0x9001;

    public static final int THINGS = 0x9002;

    private int type;

    public ThingsBaseData(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
