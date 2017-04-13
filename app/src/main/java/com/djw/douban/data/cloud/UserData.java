package com.djw.douban.data.cloud;

/**
 * Created by JasonDong on 2017/4/13.
 */

public class UserData {
    private String id;

    private String uid;

    private String name;

    private String avatar;

    private String alt;

    private String relation;

    private String created;

    private String loc_id;

    private String loc_name;

    private String desc;

    public UserData(String id, String uid, String name, String avatar, String alt, String relation, String created, String loc_id, String loc_name, String desc) {
        this.id = id;
        this.uid = uid;
        this.name = name;
        this.avatar = avatar;
        this.alt = alt;
        this.relation = relation;
        this.created = created;
        this.loc_id = loc_id;
        this.loc_name = loc_name;
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getLoc_id() {
        return loc_id;
    }

    public void setLoc_id(String loc_id) {
        this.loc_id = loc_id;
    }

    public String getLoc_name() {
        return loc_name;
    }

    public void setLoc_name(String loc_name) {
        this.loc_name = loc_name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id='" + id + '\'' +
                ", uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", alt='" + alt + '\'' +
                ", relation='" + relation + '\'' +
                ", created='" + created + '\'' +
                ", loc_id='" + loc_id + '\'' +
                ", loc_name='" + loc_name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
