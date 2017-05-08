package com.djw.douban.ui.leader.contract;

import com.djw.douban.base.BasePresenter;
import com.djw.douban.base.BaseView;

/**
 * Created by JasonDong
 * <p>
 * on 2017/5/5.
 */

public interface LeaderContract {

    interface View extends BaseView{

        void showGirl(String img,String author);

    }

    interface Presenter extends BasePresenter<View>{

        void getGril();

    }

}
