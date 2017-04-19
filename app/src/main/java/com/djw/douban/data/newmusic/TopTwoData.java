package com.djw.douban.data.newmusic;

import java.util.List;

/**
 * Created by JasonDong on 2017/4/19.
 */

public class TopTwoData extends MusicBaseData{
    private List<MusicStyleInfoData> list;

    private String title;

    public TopTwoData(String title, List<MusicStyleInfoData> list) {
        super(MusicBaseData.TWO);
        this.list = list;
        this.title = title;
    }

    public List<MusicStyleInfoData> getList() {
        return list;
    }

    public void setList(List<MusicStyleInfoData> list) {
        this.list = list;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
