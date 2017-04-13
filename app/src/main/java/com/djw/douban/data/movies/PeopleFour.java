package com.djw.douban.data.movies;

/**
 * Created by JasonDong on 2017/4/7.
 */

public class PeopleFour extends PeopleBaseData {
    private String title;

    public PeopleFour(String title) {
        super(PeopleBaseData.FOUR);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
