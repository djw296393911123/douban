package com.djw.douban.data.search;

/**
 * Created by JasonDong on 2017/4/17.
 */

public class SearchNormalData extends SearchBaseData {

    private final String direct_id;
    private String name;

    private String id;

    private int goWhere;

    public SearchNormalData(String name, String id, int goWhere,String direct_id) {
        super(SearchBaseData.ONE);
        this.name = name;
        this.id = id;
        this.goWhere = goWhere;
        this.direct_id = direct_id;
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

    public int getGoWhere() {
        return goWhere;
    }

    public void setGoWhere(int goWhere) {
        this.goWhere = goWhere;
    }

    public String getDirect_id() {
        return direct_id;
    }
}
