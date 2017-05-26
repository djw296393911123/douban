package com.djw.douban.http;


import com.djw.douban.data.book.BookInfoData;
import com.djw.douban.data.book.BookRoot;
import com.djw.douban.data.cloud.CloudItemData;
import com.djw.douban.data.cloud.CloudLocData;
import com.djw.douban.data.cloud.VisitedData;
import com.djw.douban.data.girl.GankListItemData;
import com.djw.douban.data.message.MessageReceiveData;
import com.djw.douban.data.movies.MoviesActorsData;
import com.djw.douban.data.movies.MoviesInfoData;
import com.djw.douban.data.movies.MoviesItemData;
import com.djw.douban.data.movies.NorthAmericaItemData;
import com.djw.douban.data.movies.TypeData;
import com.djw.douban.data.music.MusicRoot;
import com.djw.douban.data.music.MusicRootHY;
import com.djw.douban.data.music.MusicRootOM;
import com.djw.douban.data.music.MusicRootRH;
import com.djw.douban.data.music.Musics;
import com.djw.douban.http.apis.DoubanApi;
import com.djw.douban.http.apis.GirlApi;
import com.djw.douban.http.apis.MessageApi;

import rx.Observable;

/**
 * Created by JasonDong
 * <p>
 * on 2017/3/23.
 */

public class RetrofitHelper {

    private DoubanApi doubanApi;

    private GirlApi girlApi;

    private MessageApi messageApi;

    public RetrofitHelper(DoubanApi doubanApi, GirlApi girlApi, MessageApi messageApi) {
        this.doubanApi = doubanApi;
        this.girlApi = girlApi;
        this.messageApi = messageApi;
    }

    public Observable<MoviesItemData> getHotMovies(int start, int count) {
        return doubanApi.getHot(start, count);
    }

    public Observable<MoviesItemData> getCommingSoon(int start, int count) {
        return doubanApi.getCommingSoon(start, count);
    }

    public Observable<NorthAmericaItemData> getNorthAmerica(int start, int count) {
        return doubanApi.getNorthAmerica(start, count);
    }

    public Observable<MoviesItemData> getTop250(int start, int count) {
        return doubanApi.getTop250(start, count);
    }

    public Observable<MoviesInfoData> getMovieInfo(int id) {
        return doubanApi.getMovieInfo(id);
    }

    public Observable<MoviesActorsData> getMoviesPeople(int id) {
        return doubanApi.getMoviePeople(id);
    }

    public Observable<BookRoot> getBookByTag(String tag, int start, int count) {
        return doubanApi.searchBookByTag(tag, start, count);
    }

    public Observable<MusicRoot> getMusicByTag(String tag, int start, int count) {
        return doubanApi.searchMusicByTag(tag, start, count);
    }

    public Observable<MusicRootHY> getMusicByTagHY(String tag, int start, int count) {
        return doubanApi.searchMusicByHY(tag, start, count);
    }

    public Observable<MusicRootRH> getMusicByTagRH(String tag, int start, int count) {
        return doubanApi.searchMusicByRH(tag, start, count);
    }

    public Observable<MusicRootOM> getMusicByTagOM(String tag, int start, int count) {
        return doubanApi.searchMusicByOM(tag, start, count);
    }

    public Observable<BookInfoData> getBookInfo(String id) {
        return doubanApi.getBookInfo(id);
    }

    public Observable<Musics> getMusicInfo(String id) {
        return doubanApi.getMusicDetail(id);
    }

    public Observable<CloudLocData> getCity() {
        return doubanApi.getCity();
    }

    public Observable<CloudItemData> getActivitys(String loc, String day_type, String type, int start, int count) {
        return doubanApi.getActivitys(loc, day_type, type, start, count);
    }

    public Observable<VisitedData> getVisited(String id) {
        return doubanApi.getVisited(id);
    }

    public Observable<TypeData> getType(String q, int start, int count) {
        return doubanApi.getSearch(q, start, count);
    }

    public Observable<GankListItemData> getGirl() {
        return girlApi.getRadomMeizi("1");
    }

    public Observable<MessageReceiveData> getMessage(String msg) {
        return messageApi.getMessage("3b693076b1314c6ca2b55f40031783b4", msg);
    }

    public Observable<GankListItemData> getGirls(int page) {
        return girlApi.getMeizi(String.valueOf(page));
    }

}
