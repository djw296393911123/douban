package com.djw.douban.data.mine;

/**
 * Created by JasonDong on 2017/4/14.
 */

public class LikeOrHideData {

    private String name;

    private String url;

    private String grade;

    private String id;

    public LikeOrHideData(String name, String url, String grade, String id) {
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
