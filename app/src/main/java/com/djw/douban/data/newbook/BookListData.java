package com.djw.douban.data.newbook;

/**
 * Created by JasonDong on 2017/5/2.
 */

public class BookListData extends BookBaseData {

    private String name;

    private String grade;

    private String img;

    private String id;

    private String author;

    public BookListData(String name, String grade, String img, String id,String author) {
        super(BookBaseData.LIST);
        this.name = name;
        this.grade = grade;
        this.img = img;
        this.id = id;
        this.author = author;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
