package com.djw.douban.data.newbook;

/**
 * Created by JasonDong on 2017/5/5.
 */

public class BookListItemData {
    private String name;

    private String grade;

    private String img;

    private String id;

    public BookListItemData(String name, String grade, String img, String id) {
        this.name = name;
        this.grade = grade;
        this.img = img;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
