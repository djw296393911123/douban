package com.djw.douban.data.newmovies;

/**
 * Created by JasonDong on 2017/5/4.
 */

public class MoviesEightItemData {

    private String name;

    private String grade;

    private String image;

    private String id;

    public MoviesEightItemData(String name, String grade, String image, String id) {
        this.name = name;
        this.grade = grade;
        this.image = image;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
