package com.djw.douban.ui.mine.contract;

import com.djw.douban.base.BasePresenter;
import com.djw.douban.base.BaseView;
import com.djw.douban.data.mine.MineItemData;

/**
 * Created by JasonDong on 2017/4/13.
 */

public interface OnlineContract {

    interface View extends BaseView {

        void showOnline(MineItemData mineItemData);

        void showImg(String url);

    }

    interface Presenter extends BasePresenter<View> {

        void getOnline(String cate);

        void getUrl();

    }

}
