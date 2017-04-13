package com.djw.douban.data.mine;

import java.util.List;

/**
 * Created by JasonDong on 2017/4/13.
 */

public class MineItemData {

    /**
     * id : 11038343
     * alt : https://www.douban.com/online/11038343/
     * title : 新的截图猜电影，来！
     * desc : 截图猜电影
     * 猜中后描述改为：
     * 《电影名》 by （猜中者名字）
     * 请遵守规则！
     * <p>
     * 附注：相关网址是，听>配乐>猜电影友情活动，尽请参加！
     * tags : ["截图","电影","交友","猜图"]
     * created : 2012-02-24 11:49:32
     * begin_time : 2012-02-24 11:00:00
     * end_time : 2012-05-23 11:00:00
     * related_url : https://www.douban.com/online/10999361/
     * shuo_topic : 新的截图猜电影，来！
     * cascade_invite : true
     * group_id : 0
     * album_id : 65606728
     * participant_count : 13881
     * photo_count : 63281
     * liked_count : 2127
     * recs_count : 417
     * icon : https://img3.doubanio.com/bpic/o590273.jpg
     * thumb : https://img3.doubanio.com/spic/o590273.jpg
     * cover : https://img3.doubanio.com/tpic/o590273.jpg
     * image : https://img3.doubanio.com/lpic/o590273.jpg
     * owner : User
     * liked : false
     * joined : false
     */

    private String id;
    private String alt;
    private String title;
    private String desc;
    private String created;
    private String begin_time;
    private String end_time;
    private String related_url;
    private String shuo_topic;
    private boolean cascade_invite;
    private String group_id;
    private String album_id;
    private int participant_count;
    private int photo_count;
    private int liked_count;
    private int recs_count;
    private String icon;
    private String thumb;
    private String cover;
    private String image;
    private String owner;
    private boolean liked;
    private boolean joined;
    private List<String> tags;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getBegin_time() {
        return begin_time;
    }

    public void setBegin_time(String begin_time) {
        this.begin_time = begin_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getRelated_url() {
        return related_url;
    }

    public void setRelated_url(String related_url) {
        this.related_url = related_url;
    }

    public String getShuo_topic() {
        return shuo_topic;
    }

    public void setShuo_topic(String shuo_topic) {
        this.shuo_topic = shuo_topic;
    }

    public boolean isCascade_invite() {
        return cascade_invite;
    }

    public void setCascade_invite(boolean cascade_invite) {
        this.cascade_invite = cascade_invite;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(String album_id) {
        this.album_id = album_id;
    }

    public int getParticipant_count() {
        return participant_count;
    }

    public void setParticipant_count(int participant_count) {
        this.participant_count = participant_count;
    }

    public int getPhoto_count() {
        return photo_count;
    }

    public void setPhoto_count(int photo_count) {
        this.photo_count = photo_count;
    }

    public int getLiked_count() {
        return liked_count;
    }

    public void setLiked_count(int liked_count) {
        this.liked_count = liked_count;
    }

    public int getRecs_count() {
        return recs_count;
    }

    public void setRecs_count(int recs_count) {
        this.recs_count = recs_count;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public boolean isJoined() {
        return joined;
    }

    public void setJoined(boolean joined) {
        this.joined = joined;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "MineItemData{" +
                "id='" + id + '\'' +
                ", alt='" + alt + '\'' +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", created='" + created + '\'' +
                ", begin_time='" + begin_time + '\'' +
                ", end_time='" + end_time + '\'' +
                ", related_url='" + related_url + '\'' +
                ", shuo_topic='" + shuo_topic + '\'' +
                ", cascade_invite=" + cascade_invite +
                ", group_id='" + group_id + '\'' +
                ", album_id='" + album_id + '\'' +
                ", participant_count=" + participant_count +
                ", photo_count=" + photo_count +
                ", liked_count=" + liked_count +
                ", recs_count=" + recs_count +
                ", icon='" + icon + '\'' +
                ", thumb='" + thumb + '\'' +
                ", cover='" + cover + '\'' +
                ", image='" + image + '\'' +
                ", owner='" + owner + '\'' +
                ", liked=" + liked +
                ", joined=" + joined +
                ", tags=" + tags +
                '}';
    }
}
