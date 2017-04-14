package com.djw.douban.component;

import android.app.Activity;


import com.djw.douban.http.FragmentScope;
import com.djw.douban.module.FragmentModule;
import com.djw.douban.ui.cloud.fragment.CloudFragment;
import com.djw.douban.ui.home.book.fragment.BookFragment;
import com.djw.douban.ui.home.movies.fragment.MoviesFragment;
import com.djw.douban.ui.home.movies.fragment.NewMoviesFragment;
import com.djw.douban.ui.home.music.fragment.MusicFragment;
import com.djw.douban.ui.mine.fragment.MineFragment;

import dagger.Component;

/**
 * Created by JasonDong on 2017/3/23.
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();


    void inject(MoviesFragment moviesFragment);

    void inject(BookFragment bookFragment);

    void inject(MusicFragment musicFragment);

    void inject(CloudFragment cloudFragment);

    void inject(MineFragment mineFragment);

    void inject(NewMoviesFragment newMoviesFragment);
}