package com.djw.douban.data.newbook;

/**
 * Created by JasonDong on 2017/5/5.
 */

public class BookTypeData extends BookBaseData {

    private String title;

    public BookTypeData(String title) {
        super(BookBaseData.TYPE);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
