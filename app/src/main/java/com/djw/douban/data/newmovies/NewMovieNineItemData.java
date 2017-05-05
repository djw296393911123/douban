package com.djw.douban.data.newmovies;

/**
 * Created by JasonDong on 2017/5/4.
 */

public class NewMovieNineItemData {

    private String img;

    private String id;

    public NewMovieNineItemData(String img, String id) {
        this.img = img;
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
