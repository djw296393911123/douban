package com.djw.douban.data.book;

import java.util.List;

/**
 * Created by JasonDong on 2017/4/25.
 */

public class BannerData {
    private List<String> url;

    private List<String> title;

    private List<String> id;

    public BannerData(List<String> url, List<String> title, List<String> id) {
        this.url = url;
        this.title = title;
        this.id = id;
    }

    public List<String> getUrl() {
        return url;
    }

    public void setUrl(List<String> url) {
        this.url = url;
    }

    public List<String> getTitle() {
        return title;
    }

    public void setTitle(List<String> title) {
        this.title = title;
    }

    public List<String> getId() {
        return id;
    }

    public void setId(List<String> id) {
        this.id = id;
    }
}
