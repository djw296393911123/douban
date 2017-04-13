package com.djw.douban.ui.home.music.contract;

import com.djw.douban.base.BasePresenter;
import com.djw.douban.base.BaseView;
import com.djw.douban.data.music.Musics;

/**
 * Created by JasonDong on 2017/4/12.
 */

public interface MusicInfoContract {

    interface View extends BaseView {

        void showMusci(Musics musics);

    }

    interface Presenter extends BasePresenter<View> {

        void getMusic(String id);

    }

}
