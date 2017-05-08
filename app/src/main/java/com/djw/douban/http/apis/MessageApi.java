package com.djw.douban.http.apis;

import com.djw.douban.data.message.MessageReceiveData;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by JasonDong on 2017/5/5.
 */

public interface MessageApi {

    String SERVICE = "http://www.tuling123.com/";

    @GET("openapi/api")
    Observable<MessageReceiveData> getMessage(@Query("key") String key, @Query("info") String msg);

}
