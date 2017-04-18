package com.djw.douban.data.newmusic;

/**
 * Created by JasonDong on 2017/4/18.
 */

public class MusicNewFiveData extends MusicBaseData {

    private String name;

    public MusicNewFiveData(String name) {
        super(MusicBaseData.FIVE);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
