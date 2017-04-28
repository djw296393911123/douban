package com.djw.douban.data.movies;

/**
 * Created by JasonDong on 2017/4/7.
 */

public class PeopleTwo extends PeopleBaseData {


    private String name;

    private String id;

    private String url;

    private String grade;


    public PeopleTwo(String name, String id, String url,String grade) {
        super(PeopleBaseData.TWO);
        this.name = name;
        this.id = id;
        this.url = url;
        this.grade = grade;
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
