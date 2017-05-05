package com.djw.douban.data.newmusic;

/**
 * Created by JasonDong
 * <p>
 * on 2017/5/4.
 */

public class MusicMoreData extends MusicContentBaseData {

    private String name;

    public MusicMoreData(String name) {
        super(MusicContentBaseData.MORE);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
