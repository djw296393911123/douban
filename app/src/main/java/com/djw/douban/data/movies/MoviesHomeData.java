package com.djw.douban.data.movies;

/**
 * Created by JasonDong on 2017/4/10.
 */

public class MoviesHomeData {
    public static final int ONE = 0x9001;
    public static final int TWO = 0x9002;
    public static final int THREE = 0x9003;

    private int type;

    public MoviesHomeData(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
