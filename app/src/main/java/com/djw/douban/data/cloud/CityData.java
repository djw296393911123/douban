package com.djw.douban.data.cloud;

/**
 * Created by JasonDong on 2017/4/12.
 */

public class CityData {
    private String title;

    private String id;

    public CityData(String title, String id) {
        this.title = title;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
