package com.djw.douban.data.search;

/**
 * Created by JasonDong on 2017/4/17.
 */

public class SearchBaseData {

    public static final int ONE = 0x9001;
    public static final int TWO = 0x9002;
    public static final int THREE = 0x9003;
    public static final int FOUR = 0x9004;


    private int type;

    public SearchBaseData(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
