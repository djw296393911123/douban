package com.djw.douban.data.newmovies;

import com.djw.douban.data.movies.MoviesInfoBaseData;

import java.util.List;

/**
 * Created by JasonDong on 2017/4/20.
 */

public class MoviesInfoAlsoLikeData extends MoviesInfoBaseData {

    private List<NewMoviesFour> list;


    public MoviesInfoAlsoLikeData(List<NewMoviesFour> list) {
        super(MoviesInfoBaseData.FOUR_TYPE);
        this.list = list;
    }

    public List<NewMoviesFour> getList() {
        return list;
    }

    public void setList(List<NewMoviesFour> list) {
        this.list = list;
    }
}
