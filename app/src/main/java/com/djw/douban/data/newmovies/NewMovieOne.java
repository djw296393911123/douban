package com.djw.douban.data.newmovies;

import java.util.List;

/**
 * Created by JasonDong on 2017/4/14.
 */

public class NewMovieOne extends NewMoviesBaseData {

    private List<String> titles;

    private List<String> urls;

    private List<String> ids;

    public NewMovieOne(List<String> titles, List<String> urls, List<String> ids) {
        super(NewMoviesBaseData.ONE);
        this.titles = titles;
        this.urls = urls;
        this.ids = ids;
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

}
