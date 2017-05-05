package com.djw.douban.data.newmovies;

import java.util.List;

/**
 * Created by JasonDong on 2017/5/5.
 */

public class NewMoviesEleven extends NewMoviesBaseData {

    private List<NewMovieNineItemData> list;

    public NewMoviesEleven(List<NewMovieNineItemData> list) {
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
