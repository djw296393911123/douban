package com.djw.douban.ui.home.music.contract;

import com.djw.douban.base.BasePresenter;
import com.djw.douban.base.BaseView;
import com.djw.douban.data.newmusic.MusicBaseData;

import java.util.List;

/**
 * Created by JasonDong on 2017/4/18.
 */

public interface NewMusicContract {

    interface View extends BaseView {

        void showMusic(List<MusicBaseData> list);

    }

    interface Presenter extends BasePresenter<View> {

        void getMusic(String tag, int start, int count);

    }

}
