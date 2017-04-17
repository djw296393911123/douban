package com.djw.douban.data.newmovies;

/**
 * Created by JasonDong on 2017/4/14.
 */

public class NewMoviesSix extends NewMoviesBaseData {

    private String name;


    public NewMoviesSix(String name) {
        super(NewMoviesBaseData.SIX);
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
