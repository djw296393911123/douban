package com.djw.douban.data.movies;

/**
 * Created by JasonDong on 2017/4/7.
 */

public class PeopleTwo extends PeopleBaseData {


    private String name;

    private String id;

    private String url;

    private String grade;

    private String direct_id;

    public PeopleTwo(String name, String id, String url,String grade,String direct_id) {
        super(PeopleBaseData.TWO);
        this.name = name;
        this.id = id;
        this.url = url;
        this.grade = grade;
        this.direct_id = direct_id;
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

    public String getDirect_id() {
        return direct_id;
    }

    public void setDirect_id(String direct_id) {
        this.direct_id = direct_id;
    }
}
