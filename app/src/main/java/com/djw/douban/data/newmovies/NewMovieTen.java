package com.djw.douban.data.newmovies;

import java.util.List;

/**
 * Created by JasonDong on 2017/5/4.
 */

public class NewMovieTen extends NewMoviesBaseData {

    private List<NewMovieNineItemData> list;

    public NewMovieTen(List<NewMovieNineItemData> list) {
        super(NewMoviesBaseData.TEN);
        this.list = list;
    }

    public List<NewMovieNineItemData> getList() {
        return list;
    }

    public void setList(List<NewMovieNineItemData> list) {
        this.list = list;
    }
}
