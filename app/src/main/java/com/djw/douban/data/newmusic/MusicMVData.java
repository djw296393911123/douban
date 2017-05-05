package com.djw.douban.data.newmusic;

import java.util.List;

/**
 * Created by JasonDong on 2017/5/4.
 */

public class MusicMVData extends MusicBaseData {

    private List<String> titles;

    private List<String> types;

    public MusicMVData(List<String> titles, List<String> types) {
        super(MusicBaseData.SEVEN);
        this.titles = titles;
        this.types = types;
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
}
