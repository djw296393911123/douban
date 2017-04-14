package com.djw.douban.data.newmovies;

import java.util.List;

/**
 * Created by JasonDong on 2017/4/14.
 */

public class NewMoviesTwo extends NewMoviesBaseData {

    private List<String> name;

    private List<String> url;

    private List<String> id;

    public NewMoviesTwo(List<String> name, List<String> url, List<String> id) {
        super(NewMoviesBaseData.TWO);
        this.name = name;
        this.url = url;
        this.id = id;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
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
