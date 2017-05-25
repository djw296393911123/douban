package com.djw.douban.data.newbook;

/**
 * Created by JasonDong on 2017/5/24.
 */

public class BookErrorData extends BookBaseData{

    private String title;

    public BookErrorData(String title) {
        super(BookBaseData.ERROR);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
