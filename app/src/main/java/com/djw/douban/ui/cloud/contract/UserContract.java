package com.djw.douban.ui.cloud.contract;

import com.djw.douban.base.BasePresenter;
import com.djw.douban.base.BaseView;
import com.djw.douban.data.cloud.UserData;

/**
 * Created by JasonDong on 2017/4/13.
 */

public interface UserContract {

    interface View extends BaseView {

        void showUser(UserData userData);

    }

    interface Presenter extends BasePresenter<View> {

        void getUser(String id);

    }

}
