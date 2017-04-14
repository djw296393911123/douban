package com.djw.douban.data.newmovies;

import java.util.List;

/**
 * Created by JasonDong on 2017/4/14.
 */

public class NewMoviesThree extends NewMoviesBaseData {

    private List<String> title;

    private List<String> url;

    private List<String> id;

    public NewMoviesThree(List<String> title, List<String> url, List<String> id) {
        super(NewMoviesBaseData.THREE);
        this.title = title;
        this.url = url;
        this.id = id;
    }

    public List<String> getTitle() {
        return title;
    }

    public void setTitle(List<String> title) {
        this.title = title;
    }

    public List<String> getUrl() {
        return url;
    }

    public void setUrl(List<String> url) {
        this.url = url;
    }

    public List<String> getId() {
        return id;
    }

    public void setId(List<String> id) {
        this.id = id;
    }
}
