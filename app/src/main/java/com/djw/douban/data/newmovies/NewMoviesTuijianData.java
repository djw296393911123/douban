package com.djw.douban.data.newmovies;

/**
 * Created by JasonDong on 2017/5/3.
 */

public class NewMoviesTuijianData {

    private String name;

    private String id;

    public NewMoviesTuijianData(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
