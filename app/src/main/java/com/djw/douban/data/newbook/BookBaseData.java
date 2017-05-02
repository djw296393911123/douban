package com.djw.douban.data.newbook;

/**
 * Created by JasonDong on 2017/5/2.
 */

public class BookBaseData {

    public static final int BANNER = 0x9001;

    public static final int LIST = 0x9002;

    private int type;

    public BookBaseData(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
