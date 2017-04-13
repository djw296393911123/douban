package com.djw.douban.data.music;

import java.util.List;


public class Attrs {

    private List<String> publisher ;

    private List<String> singer ;

    private List<String> version ;

    private List<String> pubdate ;

    private List<String> title ;

    private List<String> media ;

    private List<String> tracks ;

    private List<String> discs ;

    public List<String> getPublisher() {
        return publisher;
    }

    public void setPublisher(List<String> publisher) {
        this.publisher = publisher;
    }

    public List<String> getSinger() {
        return singer;
    }

    public void setSinger(List<String> singer) {
        this.singer = singer;
    }

    public List<String> getVersion() {
        return version;
    }

    public void setVersion(List<String> version) {
        this.version = version;
    }

    public List<String> getPubdate() {
        return pubdate;
    }

    public void setPubdate(List<String> pubdate) {
        this.pubdate = pubdate;
    }

    public List<String> getTitle() {
        return title;
    }

    public void setTitle(List<String> title) {
        this.title = title;
    }

    public List<String> getMedia() {
        return media;
    }

    public void setMedia(List<String> media) {
        this.media = media;
    }

    public List<String> getTracks() {
        return tracks;
    }

    public void setTracks(List<String> tracks) {
        this.tracks = tracks;
    }

    public List<String> getDiscs() {
        return discs;
    }

    public void setDiscs(List<String> discs) {
        this.discs = discs;
    }

    @Override
    public String toString() {
        return "Attrs{" +
                "publisher=" + publisher +
                ", singer=" + singer +
                ", version=" + version +
                ", pubdate=" + pubdate +
                ", title=" + title +
                ", media=" + media +
                ", tracks=" + tracks +
                ", discs=" + discs +
                '}';
    }
}
