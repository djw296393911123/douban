package com.djw.douban.data.newmusic;

import java.util.List;

/**
 * Created by JasonDong on 2017/4/18.
 */

public class MusicLikeData extends MusicBaseData {

    private List<MusicInfoData> list;

    public MusicLikeData(List<MusicInfoData> list) {
        super(MusicBaseData.FOUR);
        this.list = list;
    }

    public List<MusicInfoData> getList() {
        return list;
    }

    public void setList(List<MusicInfoData> list) {
        this.list = list;
    }
}
