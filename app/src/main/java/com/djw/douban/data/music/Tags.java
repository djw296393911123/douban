package com.djw.douban.data.music;


public class Tags {

    private int count;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Tags{" +
                "count=" + count +
                ", name='" + name + '\'' +
                '}';
    }
}
