package com.djw.douban.data.movies;

/**
 * Created by JasonDong on 2017/4/7.
 */

public class MoviesInfoBaseData {

    public final static int TEXT_TYPE = 0x9001;
    public final static int PEOPLE_TYPE = 0x9002;
    public final static int TYPE_TYPE = 0x9003;
    public final static int FOUR_TYPE = 0x9004;
    public final static int FIVE_TYPE = 0x9005;

    private int type;

    public MoviesInfoBaseData(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
