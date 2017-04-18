package com.djw.douban.data.newmusic;

/**
 * Created by JasonDong on 2017/4/18.
 */

public class MusicListData extends MusicBaseData {

    private String url;

    private String name;

    private String author;

    private String grade;

    private String id;

    public MusicListData(String url, String name, String author, String grade, String id) {
        super(MusicBaseData.THREE);
        this.url = url;
        this.name = name;
        this.author = author;
        this.grade = grade;
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
