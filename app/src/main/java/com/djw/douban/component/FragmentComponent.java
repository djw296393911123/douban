package com.djw.douban.component;

import android.app.Activity;


import com.djw.douban.http.FragmentScope;
import com.djw.douban.module.FragmentModule;
import com.djw.douban.ui.cloud.fragment.CloudFragment;
import com.djw.douban.ui.home.book.fragment.BookFragment;
import com.djw.douban.ui.home.movies.fragment.CommingsoonFragment;
import com.djw.douban.ui.home.movies.fragment.HotFragment;
import com.djw.douban.ui.home.movies.fragment.MoviesFragment;
import com.djw.douban.ui.home.movies.fragment.NewMoviesFragment;
import com.djw.douban.ui.home.movies.fragment.NorthAmericaFragment;
import com.djw.douban.ui.home.movies.fragment.Top250Fragment;
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

    void inject(HotFragment hotFragment);

    void inject(CommingsoonFragment commingsoonFragment);

    void inject(NewMoviesFragment newMoviesFragment);

    void inject(Top250Fragment top250Fragment);

    void inject(NorthAmericaFragment northAmericaFragment);

    void inject(MoviesFragment moviesFragment);

    void inject(BookFragment bookFragment);

    void inject(MusicFragment musicFragment);

    void inject(CloudFragment cloudFragment);

    void inject(MineFragment mineFragment);
}