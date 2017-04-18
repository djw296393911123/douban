package com.djw.douban.data.newmusic;

import java.util.List;

/**
 * Created by JasonDong on 2017/4/18.
 */

public class MusicChooseData extends MusicBaseData {

    private List<String> name;

    public MusicChooseData(List<String> name) {
        super(MusicBaseData.THREE);
        this.name = name;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }
}
