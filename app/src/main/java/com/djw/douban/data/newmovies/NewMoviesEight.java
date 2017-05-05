package com.djw.douban.data.newmovies;

import java.util.List;

/**
 * Created by JasonDong on 2017/5/4.
 */

public class NewMoviesEight extends NewMoviesBaseData {

    private List<MoviesEightItemData> imgs;

    public NewMoviesEight(List<MoviesEightItemData> imgs) {
        super(NewMoviesBaseData.EIGHT);
        this.imgs = imgs;
    }

    public List<MoviesEightItemData> getImgs() {
        return imgs;
    }

    public void setImgs(List<MoviesEightItemData> imgs) {
        this.imgs = imgs;
    }
}
