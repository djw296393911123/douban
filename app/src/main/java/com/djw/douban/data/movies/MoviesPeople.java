package com.djw.douban.data.movies;

/**
 * Created by JasonDong on 2017/4/7.
 */

public class MoviesPeople extends MoviesInfoBaseData {

    private String url;

    private String name;

    private int id;

    public MoviesPeople(String url, String name,int id) {
        super(MoviesInfoBaseData.PEOPLE_TYPE);
        this.url = url;
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
