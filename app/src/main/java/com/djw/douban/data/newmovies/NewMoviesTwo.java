package com.djw.douban.data.newmovies;

import java.util.List;

/**
 * Created by JasonDong on 2017/4/14.
 */

public class NewMoviesTwo extends NewMoviesBaseData {

    private List<NewMoviewTwoItemData> list;

    public NewMoviesTwo(List<NewMoviewTwoItemData> list) {
        super(NewMoviesBaseData.TWO);
        this.list = list;
    }

    public List<NewMoviewTwoItemData> getList() {
        return list;
    }

    public void setList(List<NewMoviewTwoItemData> list) {
        this.list = list;
    }
}
