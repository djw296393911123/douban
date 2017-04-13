package com.djw.douban.data.movies;

/**
 * Created by JasonDong on 2017/4/7.
 */

public class MoviesInfoBaseData {

    public final static int TEXT_TYPE = 0x9001;
    public final static int PEOPLE_TYPE = 0x9002;
    public final static int TYPE_TYPE = 0x9003;

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
