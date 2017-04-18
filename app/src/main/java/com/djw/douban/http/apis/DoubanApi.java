package com.djw.douban.http.apis;


import com.djw.douban.data.book.BookInfoData;
import com.djw.douban.data.book.BookRoot;
import com.djw.douban.data.cloud.CloudItemData;
import com.djw.douban.data.cloud.CloudLocData;
import com.djw.douban.data.cloud.UserData;
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

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by JasonDong on 2017/4/7.
 */

public interface DoubanApi {

    String SERVICE = "https://api.douban.com/v2/";

    @GET("book/{id}")
    Observable<BookInfoData> getBookInfo(@Path("id") String id);

    @GET("movie/coming_soon")
    Observable<MoviesItemData> getCommingSoon(@Query("start") int start, @Query("count") int count);

    @GET("movie/coming_soon")
    Observable<CommingSoonData> getCommingSoons(@Query("start") int start, @Query("count") int count);

    @GET("movie/in_theaters")
    Observable<MoviesItemData> getHot(@Query("start") int start, @Query("count") int count);

    @GET("movie/in_theaters")
    Observable<HotData> getHots(@Query("start") int start, @Query("count") int count);

    @GET("movie/top250")
    Observable<MoviesItemData> getTop250(@Query("start") int start, @Query("count") int count);

    @GET("movie/top250")
    Observable<Top250Data> getTop250s(@Query("start") int start, @Query("count") int count);

    @GET("movie/weekly")
    Observable<MoviesItemData> getPraise(@Query("start") int start, @Query("count") int count);

    @GET("movie/us_box")
    Observable<NorthAmericaItemData> getNorthAmerica(@Query("start") int start, @Query("count") int count);

    @GET("movie/new_movies")
    Observable<MoviesItemData> getNewsMovies(@Query("start") int start, @Query("count") int count);

    @GET("movie/subject/{id}")
    Observable<MoviesInfoData> getMovieInfo(@Path("id") int id);

    @GET("movie/celebrity/{id}")
    Observable<MoviesActorsData> getMoviePeople(@Path("id") int id);

    @GET("movie/subject/{id}/photos")
    Observable<MoviesItemData> getAlbum(@Path("id") int id);

    @GET("book/search")
    Observable<BookRoot> searchBookByTag(@Query("tag") String tag, @Query("start") int start, @Query("count") int count);

    @GET("music/search")
    Observable<MusicRoot> searchMusicByTag(@Query("tag") String tag, @Query("start") int start, @Query("count") int count);

    @GET("music/{id}")
    Observable<Musics> getMusicDetail(@Path("id") String id);

    @GET("loc/list")
    Observable<CloudLocData> getCity();

    @GET("event/list")
    Observable<CloudItemData> getActivitys(@Query("loc") String loc, @Query("day_type") String day_type, @Query("type") String type, @Query("start") int start, @Query("count") int count);

    @GET("onlines")
    Observable<MineItemData> getOnline(@Query("cate") String cate);

    @GET("user/{name}")
    Observable<UserData> getUser(@Path("name") String id);

    @GET("movie/search")
    Observable<TypeData> getSearch(@Query("tag") String tag, @Query("start") int start, @Query("count") int count);

    @GET("music/search")
    Observable<MusicRootHY> searchMusicByHY(@Query("tag") String tag, @Query("start") int start, @Query("count") int count);

    @GET("music/search")
    Observable<MusicRootRH> searchMusicByRH(@Query("tag") String tag, @Query("start") int start, @Query("count") int count);

    @GET("music/search")
    Observable<MusicRootOM> searchMusicByOM(@Query("tag") String tag, @Query("start") int start, @Query("count") int count);

}
