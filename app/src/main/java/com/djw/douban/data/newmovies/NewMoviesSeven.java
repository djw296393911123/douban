package com.djw.douban.data.newmovies;

import java.util.List;

/**
 * Created by JasonDong on 2017/5/3.
 */

public class NewMoviesSeven extends NewMoviesBaseData {

    private List<String> titles;

    private List<Integer> ids;

    public NewMoviesSeven(List<String> titles, List<Integer> ids) {
        super(NewMoviesBaseData.SEVEN);
        this.titles = titles;
        this.ids = ids;
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
