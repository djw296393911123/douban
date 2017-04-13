package com.djw.douban.data.movies;

/**
 * Created by JasonDong on 2017/4/7.
 */

public class PeopleBaseData {
    public final static int ONE = 0x9001;
    public final static int TWO = 0x9002;
    public final static int THREE = 0x9003;
    public final static int FOUR = 0x9004;

    private int type;

    public PeopleBaseData(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
