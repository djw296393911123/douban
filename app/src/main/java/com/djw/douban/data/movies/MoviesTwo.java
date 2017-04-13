package com.djw.douban.data.movies;

import java.util.List;

/**
 * Created by JasonDong on 2017/4/10.
 */

public class MoviesTwo extends MoviesHomeData {

    private String title;

    private List<MoviesTwoo> list;

    public MoviesTwo(String title,List<MoviesTwoo> list) {
        super(MoviesHomeData.TWO);
        this.list = list;
        this.title = title;
    }

    public List<MoviesTwoo> getList() {
        return list;
    }

    public void setList(List<MoviesTwoo> list) {
        this.list = list;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
