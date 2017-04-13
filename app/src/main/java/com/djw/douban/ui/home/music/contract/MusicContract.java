package com.djw.douban.ui.home.music.contract;

import com.djw.douban.base.BasePresenter;
import com.djw.douban.base.BaseView;
import com.djw.douban.data.music.MusicRoot;

/**
 * Created by JasonDong on 2017/4/12.
 */

public interface MusicContract {

    interface View extends BaseView {
        void showMusic(MusicRoot musicRoot, boolean isLoadMore);
    }

    interface Presenter extends BasePresenter<View> {

        void getMusic(int start, int count, String tag, boolean isLoadMore);

    }

}
