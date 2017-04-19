package com.djw.douban.data.newmovies;

/**
 * Created by JasonDong on 2017/4/14.
 */

public class NewMoviesFour extends NewMoviesBaseData {

    private String name;

    private String url;

    private String id;

    private String grade;

    private String direct;

    public NewMoviesFour(String name, String url, String id, String grade, String direct) {
        super(NewMoviesBaseData.FOUR);
        this.name = name;
        this.url = url;
        this.id = id;
        this.grade = grade;
        this.direct = direct;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getDirect() {
        return direct;
    }

    public void setDirect(String direct) {
        this.direct = direct;
    }
}
