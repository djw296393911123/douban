package com.djw.douban.data.newmusic;

import java.util.List;

/**
 * Created by JasonDong on 2017/4/18.
 */

public class MusicContentData extends MusicBaseData {

    private List<MusicInfoData> list;

    public MusicContentData(List<MusicInfoData> list) {
        super(MusicBaseData.TWO);
        this.list = list;
    }

    public List<MusicInfoData> getList() {
        return list;
    }

    public void setList(List<MusicInfoData> list) {
        this.list = list;
    }
}
