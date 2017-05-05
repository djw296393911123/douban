package com.djw.douban.data.newmovies;

/**
 * Created by JasonDong on 2017/5/4.
 */

public class NewMoviewTwoItemData {
    private int img;

    private String title;

    private String id;

    public NewMoviewTwoItemData(int img, String title, String id) {
        this.img = img;
        this.title = title;
        this.id = id;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
