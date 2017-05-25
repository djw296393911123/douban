package com.djw.douban.data.newmovies;

/**
 * Created by JasonDong on 2017/5/22.
 */

public class NewMoviesError extends NewMoviesBaseData {

    private String msg;

    public NewMoviesError(String msg) {
        super(NewMoviesBaseData.ELEVEN);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
