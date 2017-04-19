package com.djw.douban.data.newmusic;

/**
 * Created by JasonDong on 2017/4/18.
 */

public class MusicLikeData extends MusicBaseData {

    private String name;

    private String url;

    private String id;

    private String grade;

    private String singer;

    public MusicLikeData(String name, String url, String id, String grade,String singer) {
        super(MusicBaseData.FOUR);
        this.name = name;
        this.url = url;
        this.id = id;
        this.grade = grade;
        this.singer = singer;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }
}
