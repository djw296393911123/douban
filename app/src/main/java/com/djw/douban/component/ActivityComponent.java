package com.djw.douban.component;

import android.app.Activity;


import com.djw.douban.http.ActivityScope;
import com.djw.douban.module.ActivityModule;
import com.djw.douban.ui.cloud.activity.UserActivity;
import com.djw.douban.ui.home.book.activity.BookInfoActivity;
import com.djw.douban.ui.home.movies.activity.MoreMovieActivity;
import com.djw.douban.ui.home.movies.activity.MovieInfoActivity;
import com.djw.douban.ui.home.movies.activity.PeopleActivity;
import com.djw.douban.ui.home.movies.activity.Top250Activity;
import com.djw.douban.ui.home.music.activity.MusicInfoActivity;

import dagger.Component;

/**
 * Created by JasonDong on 2017/3/24.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();

    void inject(MovieInfoActivity movieInfoActivity);

    void inject(PeopleActivity peopleActivity);

    void inject(Top250Activity top250Activity);

    void inject(MoreMovieActivity moreMovieActivity);

    void inject(BookInfoActivity bookInfoActivity);

    void inject(MusicInfoActivity musicInfoActivity);

    void inject(UserActivity userActivity);
}