package com.djw.douban.http.apis;

import com.djw.douban.data.girl.GankListItemData;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by JasonDong on 2017/5/5.
 */

public interface GirlApi {

    String SERVICE = "http://gank.io/api/";

    @GET("random/data/福利/{num}")
    Observable<GankListItemData> getRadomMeizi(@Path("num") String num);

    @GET("data/福利/20/{page}")
    Observable<GankListItemData> getMeizi(@Path("page") String page);

}
