package com.djw.douban.data.movies;

/**
 * Created by JasonDong on 2017/4/7.
 */

public class MoviesTextData extends MoviesInfoBaseData {

    private String content;

    public MoviesTextData(String content) {
        super(MoviesInfoBaseData.TEXT_TYPE);
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
