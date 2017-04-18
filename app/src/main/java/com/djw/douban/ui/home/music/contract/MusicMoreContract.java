package com.djw.douban.ui.home.music.contract;

import com.djw.douban.base.BasePresenter;
import com.djw.douban.base.BaseView;
import com.djw.douban.data.music.MusicRoot;

import java.util.List;

/**
 * Created by JasonDong on 2017/4/18.
 */

public interface MusicMoreContract {

    interface View extends BaseView {

        void showMore(MusicRoot musicRoot, boolean isLoadMore);

    }

    interface Presenter extends BasePresenter<View> {

        void getMore(String tag, int start, int count, boolean isLoadMore, boolean isShowProgress);

    }

}
