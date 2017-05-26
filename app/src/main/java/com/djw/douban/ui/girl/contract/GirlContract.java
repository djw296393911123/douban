package com.djw.douban.ui.girl.contract;

import com.djw.douban.base.BasePresenter;
import com.djw.douban.base.BaseView;
import com.djw.douban.data.girl.GankListItemData;
import com.djw.douban.data.girl.GirlBaseData;

import java.util.List;

/**
 * Created by JasonDong on 2017/5/26.
 */

public interface GirlContract {

    interface View extends BaseView{

        void showGirl(List<GirlBaseData> list, boolean isLoadMore);

    }

    interface Presenter extends BasePresenter<View>{

        void getGirl(int page,boolean isLoadMore,boolean isShowProgress);

    }


}
