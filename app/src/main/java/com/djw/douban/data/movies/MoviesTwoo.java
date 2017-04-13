package com.djw.douban.data.movies;

import java.util.List;

/**
 * Created by JasonDong on 2017/4/10.
 */

public class MoviesTwoo{

    private String name;

    private String url;

    public MoviesTwoo(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
