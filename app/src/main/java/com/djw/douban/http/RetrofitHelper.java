package com.djw.douban.http;


import com.djw.douban.data.book.BookInfoData;
import com.djw.douban.data.book.BookRoot;
import com.djw.douban.data.cloud.CloudItemData;
import com.djw.douban.data.cloud.CloudLocData;
import com.djw.douban.data.cloud.UserData;
import com.djw.douban.data.cloud.VisitedData;
import com.djw.douban.data.mine.MineItemData;
import com.djw.douban.data.movies.CommingSoonData;
import com.djw.douban.data.movies.HotData;
import com.djw.douban.data.movies.MoviesActorsData;
import com.djw.douban.data.movies.MoviesInfoData;
import com.djw.douban.data.movies.MoviesItemData;
import com.djw.douban.data.movies.NorthAmericaItemData;
import com.djw.douban.data.movies.Top250Data;
import com.djw.douban.data.movies.TypeData;
import com.djw.douban.data.music.MusicRoot;
import com.djw.douban.data.music.MusicRootHY;
import com.djw.douban.data.music.MusicRootOM;
import com.djw.douban.data.music.MusicRootRH;
import com.djw.douban.data.music.Musics;
import com.djw.douban.http.apis.DoubanApi;

import rx.Observable;

/**
 * Created by JasonDong on 2017/3/23.
 */

public class RetrofitHelper {

    private DoubanApi doubanApi;

    public RetrofitHelper(DoubanApi doubanApi) {
        this.doubanApi = doubanApi;
    }

    public Observable<MoviesItemData> getHotMovies(int start, int count) {
        return doubanApi.getHot(start, count);
    }

    public Observable<HotData> getHotMovie(int start, int count) {
        return doubanApi.getHots(start, count);
    }

    public Observable<MoviesItemData> getCommingSoon(int start, int count) {
        return doubanApi.getCommingSoon(start, count);
    }

    public Observable<CommingSoonData> getCommingSoons(int start, int count) {
        return doubanApi.getCommingSoons(start, count);
    }

    public Observable<MoviesItemData> getNewMovies(int start, int count) {
        return doubanApi.getNewsMovies(start, count);
    }

    public Observable<NorthAmericaItemData> getNorthAmerica(int start, int count) {
        return doubanApi.getNorthAmerica(start, count);
    }

    public Observable<MoviesItemData> getPraiseMovies(int start, int count) {
        return doubanApi.getPraise(start, count);
    }

    public Observable<MoviesItemData> getTop250(int start, int count) {
        return doubanApi.getTop250(start, count);
    }

    public Observable<Top250Data> getTop250s(int start, int count) {
        return doubanApi.getTop250s(start, count);
    }

    public Observable<MoviesInfoData> getMovieInfo(int id) {
        return doubanApi.getMovieInfo(id);
    }

    public Observable<MoviesActorsData> getMoviesPeople(int id) {
        return doubanApi.getMoviePeople(id);
    }

    public Observable<MoviesItemData> getAlbum(int id) {
        return doubanApi.getAlbum(id);
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

    public Observable<MineItemData> getOnline(String cate) {
        return doubanApi.getOnline(cate);
    }

    public Observable<UserData> getUser(String id) {
        return doubanApi.getUser(id);
    }

    public Observable<VisitedData> getVisited(String id) {
        return doubanApi.getVisited(id);
    }

    public Observable<TypeData> getType(String q, int start, int count) {
        return doubanApi.getSearch(q, start, count);
    }
}
