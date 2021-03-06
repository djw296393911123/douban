package com.djw.douban.module;


import com.djw.douban.base.BaseApplication;
import com.djw.douban.http.DoubanUrl;
import com.djw.douban.http.GirlUrl;
import com.djw.douban.http.apis.DoubanApi;
import com.djw.douban.http.apis.GirlApi;
import com.djw.douban.http.apis.MessageApi;
import com.djw.douban.http.MessageUrl;

import java.io.File;
import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by JasonDong
 * <p>
 * on 2017/3/23.
 */
@Module
public class HttpMoudel {

    @Singleton
    @Provides
    Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }


    @Singleton
    @Provides
    OkHttpClient.Builder provideOkHttpBuilder() {
        return new OkHttpClient.Builder();
    }


    @Singleton
    @Provides
    @DoubanUrl
    Retrofit provideDoubanRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, DoubanApi.SERVICE);
    }

    @Singleton
    @Provides
    @GirlUrl
    Retrofit provideGirlRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, GirlApi.SERVICE);
    }

    @Singleton
    @Provides
    @MessageUrl
    Retrofit provideMessageRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, MessageApi.SERVICE);
    }


    @Singleton
    @Provides
    OkHttpClient provideClient(OkHttpClient.Builder builder, Cache cache, Interceptor interceptor) {

        return builder.cache(cache).addNetworkInterceptor(interceptor).build();
    }

    @Singleton
    @Provides
    Cache provideCache() {
        return new Cache(new File(BaseApplication.getInstance().getApplicationContext().getCacheDir(), "yaeryou"), 1024 * 1024 * 50);
    }

    @Singleton
    @Provides
    Interceptor provideInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "max-age=" + 60 * 60)
                        .build();
            }
        };
    }


    @Singleton
    @Provides
    DoubanApi provideDoubanService(@DoubanUrl Retrofit retrofit) {
        return retrofit.create(DoubanApi.class);
    }

    @Singleton
    @Provides
    GirlApi provideGirlService(@GirlUrl Retrofit retrofit) {
        return retrofit.create(GirlApi.class);
    }

    @Singleton
    @Provides
    MessageApi provideMessageService(@MessageUrl Retrofit retrofit) {
        return retrofit.create(MessageApi.class);
    }


    private Retrofit createRetrofit(Retrofit.Builder builder, OkHttpClient client, String url) {


        return builder
                .baseUrl(url)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
