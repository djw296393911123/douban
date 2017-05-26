package com.djw.douban.data.girl;

/**
 * Created by JasonDong on 2017/5/26.
 */

public class GirlNormalData extends GirlBaseData {

    private String url;

    public GirlNormalData(String url) {
        super(GirlBaseData.NORMAL);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
