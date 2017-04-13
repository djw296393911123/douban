package com.djw.douban.data.movies;

import java.util.List;

/**
 * Created by JasonDong on 2017/4/10.
 */

public class MoviesOne extends MoviesHomeData {

    private String title;

    private List<MoviesOnee> list;

    public MoviesOne(String title, List<MoviesOnee> list) {
        super(MoviesHomeData.ONE);
        this.title = title;
        this.list = list;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<MoviesOnee> getList() {
        return list;
    }

    public void setList(List<MoviesOnee> list) {
        this.list = list;
    }
}
