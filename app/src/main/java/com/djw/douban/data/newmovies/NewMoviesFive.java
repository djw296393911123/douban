package com.djw.douban.data.newmovies;

/**
 * Created by JasonDong on 2017/4/14.
 */

public class NewMoviesFive extends NewMoviesBaseData {

    private String name;

    private int img;


    public NewMoviesFive(String name,int img) {
        super(NewMoviesBaseData.FIVE);
        this.name = name;
        this.img = img;

    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
