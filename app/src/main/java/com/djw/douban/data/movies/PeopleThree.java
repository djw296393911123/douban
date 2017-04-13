package com.djw.douban.data.movies;

import java.util.List;

/**
 * Created by JasonDong on 2017/4/7.
 */

public class PeopleThree extends PeopleBaseData {

    private String url;

    private String name;

    private String id;

    private List<PeopleTwo> list;

    public PeopleThree(String url, String name, String id,List<PeopleTwo> list) {
        super(PeopleBaseData.THREE);
        this.url = url;
        this.name = name;
        this.id = id;
        this.list = list;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PeopleTwo> getList() {
        return list;
    }

    public void setList(List<PeopleTwo> list) {
        this.list = list;
    }
}
