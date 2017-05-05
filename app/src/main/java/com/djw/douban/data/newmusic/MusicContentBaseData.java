package com.djw.douban.data.newmusic;

/**
 * Created by JasonDong
 * <p>
 * on 2017/5/4.
 */

public class MusicContentBaseData {

    public static final int NORMAL = 0x9001;

    public static final int MORE = 0x9002;

    private int type;

    public MusicContentBaseData(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
