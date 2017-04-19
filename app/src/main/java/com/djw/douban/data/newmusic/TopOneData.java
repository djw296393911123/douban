package com.djw.douban.data.newmusic;

import java.util.List;

/**
 * Created by JasonDong on 2017/4/19.
 */

public class TopOneData extends MusicBaseData {

    private String title;

    private int where;

    private List<MusicStyleInfoData> list;

    public TopOneData(String title, List<MusicStyleInfoData> list, int where) {
        super(MusicBaseData.ONE);
        this.list = list;
        this.title = title;
        this.where = where;
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

    public int getWhere() {
        return where;
    }

    public void setWhere(int where) {
        this.where = where;
    }
}
