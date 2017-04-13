package com.djw.douban.data.movies;

/**
 * Created by JasonDong on 2017/4/7.
 */

public class MoviesInfoType extends MoviesInfoBaseData{

    private String title;

    public MoviesInfoType(String title) {
        super(MoviesInfoBaseData.TYPE_TYPE);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
