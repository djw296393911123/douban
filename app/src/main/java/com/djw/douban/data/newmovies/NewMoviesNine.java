package com.djw.douban.data.newmovies;

import java.util.List;

/**
 * Created by JasonDong on 2017/5/4.
 */

public class NewMoviesNine extends NewMoviesBaseData {

    private List<NewMovieNineItemData> list;

    public NewMoviesNine(List<NewMovieNineItemData> list) {
        super(NewMoviesBaseData.NINE);
        this.list = list;
    }

    public List<NewMovieNineItemData> getList() {
        return list;
    }

    public void setList(List<NewMovieNineItemData> list) {
        this.list = list;
    }
}
