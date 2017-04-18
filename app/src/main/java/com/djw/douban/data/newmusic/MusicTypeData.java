package com.djw.douban.data.newmusic;

/**
 * Created by JasonDong on 2017/4/18.
 */

public class MusicTypeData extends MusicBaseData {

    private String name;

    public MusicTypeData(String name) {
        super(MusicBaseData.ONE);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
