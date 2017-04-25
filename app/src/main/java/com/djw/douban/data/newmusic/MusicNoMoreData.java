package com.djw.douban.data.newmusic;

/**
 * Created by JasonDong on 2017/4/25.
 */

public class MusicNoMoreData extends MusicBaseData {

    private String title;

    public MusicNoMoreData(String title) {
        super(MusicBaseData.SIX);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
