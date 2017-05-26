package com.djw.douban.component;

import android.app.Activity;


import com.djw.douban.http.ActivityScope;
import com.djw.douban.module.ActivityModule;
import com.djw.douban.ui.book.activity.BookFromTagActivity;
import com.djw.douban.ui.girl.activity.GirlActivity;
import com.djw.douban.ui.leader.LeaderActivity;
import com.djw.douban.ui.message.CalendarActivity;
import com.djw.douban.ui.message.MessageActivity;
import com.djw.douban.ui.search.activity.SearchActivity;
import com.djw.douban.ui.book.activity.BookInfoActivity;
import com.djw.douban.ui.movies.activity.CommingSoonActivity;
import com.djw.douban.ui.movies.activity.HotActivity;
import com.djw.douban.ui.movies.activity.MovieInfoActivity;
import com.djw.douban.ui.movies.activity.NorthAmericaActivity;
import com.djw.douban.ui.movies.activity.People2Activity;
import com.djw.douban.ui.movies.activity.PeopleActivity;
import com.djw.douban.ui.movies.activity.Top250Activity;
import com.djw.douban.ui.movies.activity.TypeActivity;
import com.djw.douban.ui.music.activity.ChooseTypeActivity;
import com.djw.douban.ui.music.activity.MoreMusicActivity;
import com.djw.douban.ui.music.activity.MusicInfoActivity;

import dagger.Component;

/**
 * Created by JasonDong on 2017/3/24.
 *
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();

    void inject(MovieInfoActivity movieInfoActivity);

    void inject(PeopleActivity peopleActivity);

    void inject(Top250Activity top250Activity);

    void inject(BookInfoActivity bookInfoActivity);

    void inject(MusicInfoActivity musicInfoActivity);

    void inject(CommingSoonActivity commingSoonActivity);

    void inject(NorthAmericaActivity northAmericaActivity);

    void inject(HotActivity hotActivity);

    void inject(TypeActivity typeActivity);

    void inject(SearchActivity searchActivity);

    void inject(MoreMusicActivity moreMusicActivity);

    void inject(ChooseTypeActivity chooseTypeActivity);

    void inject(People2Activity people2Activity);

    void inject(MessageActivity messageActivity);

    void inject(LeaderActivity leaderActivity);

    void inject(CalendarActivity calendarActivity);

    void inject(BookFromTagActivity bookFromTagActivity);

    void inject(GirlActivity girlActivity);
}