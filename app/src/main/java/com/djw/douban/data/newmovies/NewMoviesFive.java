package com.djw.douban.data.newmovies;

/**
 * Created by JasonDong on 2017/4/14.
 */

public class NewMoviesFive extends NewMoviesBaseData {

    private String name;


    public NewMoviesFive(String name) {
        super(NewMoviesBaseData.FIVE);
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
