package com.djw.douban.ui.home.music.contract;

import com.djw.douban.base.BasePresenter;
import com.djw.douban.base.BaseView;
import com.djw.douban.data.newmusic.MusicBaseData;

import java.util.List;

/**
 * Created by JasonDong on 2017/4/18.
 */

public interface ChooseTypeContract {

    interface View extends BaseView {

        void showChooseType(List<MusicBaseData> list, boolean isLoadMore);

        void showSortData(List<MusicBaseData> list);

    }

    interface Presenter extends BasePresenter<View> {

        void getChooseType(String tag, int start, int count, boolean isLoadMore, boolean isShowProgress, int position);

        void getSortData(int position, List<MusicBaseData> list);

    }

}
