package com.djw.douban.data.newmusic;

import java.util.List;

/**
 * Created by JasonDong on 2017/4/18.
 */

public class MusicStyleData extends MusicBaseData {

    private String name;

    private List<MusicStyleInfoData> list;

    public MusicStyleData(String name, List<MusicStyleInfoData> list) {
        super(MusicBaseData.ONE);
        this.name = name;
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MusicStyleInfoData> getList() {
        return list;
    }

    public void setList(List<MusicStyleInfoData> list) {
        this.list = list;
    }
}
