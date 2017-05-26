package com.djw.douban.data.girl;

/**
 * Created by JasonDong on 2017/5/26.
 */

public class GirlBaseData {

    public final static int NORMAL = 0x9001;

    public final static int MORE = 0x9002;

    private int type;

    public GirlBaseData(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
