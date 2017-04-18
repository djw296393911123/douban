package com.djw.douban.data.newmusic;

import java.util.List;

/**
 * Created by JasonDong on 2017/4/18.
 */

public class MusicPopData extends MusicBaseData {

    private List<MusicStyleInfoData> list;


    public MusicPopData(List<MusicStyleInfoData> list) {
        super(MusicBaseData.TWO);
        this.list = list;
    }

    public List<MusicStyleInfoData> getList() {
        return list;
    }

    public void setList(List<MusicStyleInfoData> list) {
        this.list = list;
    }
}
