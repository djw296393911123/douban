package com.djw.douban.data.newmusic;

import java.util.List;

/**
 * Created by JasonDong on 2017/4/18.
 */

public class MusicContentData extends MusicBaseData {

    private List<MusicContentBaseData> list;

    public MusicContentData(List<MusicContentBaseData> list) {
        super(MusicBaseData.TWO);
        this.list = list;
    }

    public List<MusicContentBaseData> getList() {
        return list;
    }

    public void setList(List<MusicContentBaseData> list) {
        this.list = list;
    }
}
