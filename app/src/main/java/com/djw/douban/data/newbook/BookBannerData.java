package com.djw.douban.data.newbook;

import java.util.List;

/**
 * Created by JasonDong on 2017/5/2.
 */

public class BookBannerData extends BookBaseData {

    private List<String> title;

    private List<String> img;

    private List<String> id;

    public BookBannerData(List<String> title, List<String> img, List<String> id) {
        super(BookBaseData.BANNER);
        this.title = title;
        this.img = img;
        this.id = id;
    }

    public List<String> getTitle() {
        return title;
    }

    public void setTitle(List<String> title) {
        this.title = title;
    }

    public List<String> getImg() {
        return img;
    }

    public void setImg(List<String> img) {
        this.img = img;
    }

    public List<String> getId() {
        return id;
    }

    public void setId(List<String> id) {
        this.id = id;
    }
}
