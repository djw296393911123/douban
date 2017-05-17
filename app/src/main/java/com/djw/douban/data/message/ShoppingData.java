package com.djw.douban.data.message;

/**
 * Created by JasonDong on 2017/5/15.
 */

public class ShoppingData {

    private String name;

    private String time;

    public ShoppingData(String name, String time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
