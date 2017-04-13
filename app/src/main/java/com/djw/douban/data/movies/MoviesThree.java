package com.djw.douban.data.movies;

import java.util.List;

/**
 * Created by JasonDong on 2017/4/11.
 */

public class MoviesThree extends MoviesHomeData {

    private String title;

    private List<MoviesThreee> list;

    public MoviesThree(String title, List<MoviesThreee> list) {
        super(MoviesHomeData.THREE);
        this.title = title;
        this.list = list;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<MoviesThreee> getList() {
        return list;
    }

    public void setList(List<MoviesThreee> list) {
        this.list = list;
    }
}
