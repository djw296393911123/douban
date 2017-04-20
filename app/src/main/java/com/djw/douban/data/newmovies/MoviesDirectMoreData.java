package com.djw.douban.data.newmovies;

import com.djw.douban.data.movies.MoviesInfoBaseData;

import java.util.List;

/**
 * Created by JasonDong on 2017/4/20.
 */

public class MoviesDirectMoreData extends MoviesInfoBaseData {

    private List<NewMoviesFour> list;

    public MoviesDirectMoreData(List<NewMoviesFour> list) {
        super(MoviesInfoBaseData.FIVE_TYPE);
        this.list = list;
    }

    public List<NewMoviesFour> getList() {
        return list;
    }

    public void setList(List<NewMoviesFour> list) {
        this.list = list;
    }
}
