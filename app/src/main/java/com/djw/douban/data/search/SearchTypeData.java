package com.djw.douban.data.search;

/**
 * Created by JasonDong on 2017/4/17.
 */

public class SearchTypeData extends SearchBaseData {
    private String name;

    public SearchTypeData(String name) {
        super(SearchBaseData.TWO);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
