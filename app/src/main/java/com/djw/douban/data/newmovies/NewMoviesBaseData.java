package com.djw.douban.data.newmovies;

/**
 * Created by JasonDong on 2017/4/14.
 */

public class NewMoviesBaseData {

    public static final int ONE = 0x90001;
    public static final int TWO = 0x90002;
    public static final int THREE = 0x90003;
    public static final int FOUR = 0x90004;
    public static final int FIVE = 0x90005;
    public static final int SIX = 0x90006;
    public static final int SEVEN = 0x90007;
    public static final int EIGHT = 0x90008;

    private int type;

    public NewMoviesBaseData(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
