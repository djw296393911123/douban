package com.djw.douban.data.book;

/**
 * Created by JasonDong on 2017/4/11.
 */

public class BookTypeData {
    private String title;

    private boolean isSelect;

    public BookTypeData(String title, boolean isSelect) {
        this.title = title;
        this.isSelect = isSelect;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
