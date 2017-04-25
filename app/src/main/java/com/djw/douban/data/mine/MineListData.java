package com.djw.douban.data.mine;

/**
 * Created by JasonDong on 2017/4/25.
 */

public class MineListData {

    private String id;

    private String url;

    private int type;

    private String direct_id;

    public MineListData(String id, String url, int type,String direct_id) {
        this.id = id;
        this.url = url;
        this.type = type;
        this.direct_id = direct_id;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDirect_id() {
        return direct_id;
    }

    public void setDirect_id(String direct_id) {
        this.direct_id = direct_id;
    }
}
