package com.djw.douban.data.movies;

import java.util.List;

/**
 * Created by JasonDong on 2017/4/7.
 */

public class PeopleThree extends PeopleBaseData {

    private List<PeopleTwo> list;

    public PeopleThree(List<PeopleTwo> list) {
        super(PeopleBaseData.THREE);
        this.list = list;
    }

    public List<PeopleTwo> getList() {
        return list;
    }

    public void setList(List<PeopleTwo> list) {
        this.list = list;
    }

}
