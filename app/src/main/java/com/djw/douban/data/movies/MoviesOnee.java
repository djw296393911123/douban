package com.djw.douban.data.movies;

/**
 * Created by JasonDong on 2017/4/10.
 */

public class MoviesOnee {
    private String name;

    private String url;

    private double grade;

    private int id;

    private String direct_id;

    public MoviesOnee(String name, String url, double grade, int id,String direct_id) {
        this.name = name;
        this.url = url;
        this.grade = grade;
        this.id = id;
        this.direct_id = direct_id;
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

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDirect_id() {
        return direct_id;
    }

    public void setDirect_id(String direct_id) {
        this.direct_id = direct_id;
    }
}
