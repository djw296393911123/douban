package com.djw.douban.data.newmusic;

/**
 * Created by JasonDong on 2017/4/18.
 */

public class MusicBaseData {

    public static final int ONE = 0x9001;

    public static final int TWO = 0x9002;

    public static final int THREE = 0x9003;

    public static final int FOUR = 0x9004;

    public static final int FIVE = 0x9005;

    private int type;

    public MusicBaseData(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
