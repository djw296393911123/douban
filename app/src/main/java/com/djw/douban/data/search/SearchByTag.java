package com.djw.douban.data.search;

/**
 * Created by JasonDong on 2017/4/17.
 */

public class SearchByTag extends SearchBaseData {

    private String name;

    private String id;

    public SearchByTag(String name, String id) {
        super(SearchBaseData.ONE);
        this.name = name;
        this.id = id;
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
}
