package com.djw.douban.data.movies;

import java.util.List;

/**
 * Created by JasonDong on 2017/4/11.
 */

public class BannerData {

    private List<String> urls;

    private List<String> titles;

    private List<String> ids;

    public BannerData(List<String> urls, List<String> titles, List<String> ids) {
        this.urls = urls;
        this.titles = titles;
        this.ids = ids;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
}
