package com.djw.douban.data.newmovies;

/**
 * Created by JasonDong on 2017/4/20.
 */

public class MovieInfoTopData {

    private String url;

    private String title;

    private String year;

    private String country;

    private String grade;

    private String count;

    private String type;

    public MovieInfoTopData(String url, String title, String year, String country, String grade, String count, String type) {
        this.url = url;
        this.title = title;
        this.year = year;
        this.country = country;
        this.grade = grade;
        this.count = count;
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
