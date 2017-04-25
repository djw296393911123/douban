package com.djw.douban.ui.mine.contract;

import com.djw.douban.base.BasePresenter;
import com.djw.douban.base.BaseView;
import com.djw.douban.data.mine.LikeOrHideData;
import com.djw.douban.data.mine.MineItemData;
import com.djw.douban.data.mine.MineListData;
import com.djw.douban.data.movies.MoviesItemData;

import java.util.List;

/**
 * Created by JasonDong on 2017/4/13.
 */

public interface OnlineContract {

    interface View extends BaseView {


        void showImg(List<MineListData> url, boolean isLoadMore);

    }

    interface Presenter extends BasePresenter<View> {

        void getUrl(int start, int count, boolean isLoadMore);

    }

}
