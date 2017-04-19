package com.djw.douban.ui.home.music.contract;

import com.djw.douban.base.BasePresenter;
import com.djw.douban.base.BaseView;
import com.djw.douban.data.newmusic.MusicBaseData;
import com.djw.douban.data.newmusic.MusicListData;
import com.djw.douban.data.newmusic.MusicStyleInfoData;
import com.djw.douban.data.newmusic.TopOneData;

import java.util.List;

/**
 * Created by JasonDong on 2017/4/18.
 */

public interface ChooseTypeContract {

    interface View extends BaseView {

        void showChooseType(List<MusicListData> list, boolean isLoadMore);

        void showTopType(List<MusicBaseData> list);

        void showPopData(List<MusicStyleInfoData> list);

        void showRefreshOther(List<MusicStyleInfoData> list);

    }

    interface Presenter extends BasePresenter<View> {

        void getChooseType(String tag, int type, int start, int count, boolean isLoadMore, boolean isShowProgress);

        void getTopType(int position);

        void getPopData();

        void refreshOtherType(String title);
    }

}
