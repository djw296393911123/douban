package com.djw.douban.data.movies;

/**
 * Created by JasonDong on 2017/4/11.
 */

public class MoviesThreee{
    private String name;

    private String url;

    private double grade;

    private int id;

    public MoviesThreee(String name, String url, double grade, int id) {
        this.name = name;
        this.url = url;
        this.grade = grade;
        this.id = id;
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
}
