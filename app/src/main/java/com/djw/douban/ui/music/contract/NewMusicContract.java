package com.djw.douban.ui.music.contract;

import com.djw.douban.base.BasePresenter;
import com.djw.douban.base.BaseView;
import com.djw.douban.data.newmusic.MusicBaseData;

import java.util.List;

/**
 * Created by JasonDong
 * <p>
 * on 2017/4/18.
 */

public interface NewMusicContract {

    interface View extends BaseView {

        void showMusic(List<MusicBaseData> list);

    }

    interface Presenter extends BasePresenter<View> {

        void getMusic(int start, int count);

    }

}
