package com.djw.douban.data.movies;

/**
 * Created by JasonDong on 2017/4/7.
 */

public class PeopleOne extends PeopleBaseData {

    private String url;

    private String name;

    private String jianjie;

    public PeopleOne(String url, String name, String jianjie) {
        super(PeopleBaseData.ONE);
        this.url = url;
        this.name = name;
        this.jianjie = jianjie;
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

    public String getJianjie() {
        return jianjie;
    }

    public void setJianjie(String jianjie) {
        this.jianjie = jianjie;
    }
}
