package com.djw.douban.ui.message.contract;

import com.djw.douban.base.BasePresenter;
import com.djw.douban.base.BaseView;
import com.djw.douban.data.message.MessageImgData;
import com.djw.douban.data.message.MessageReceiveData;
import com.djw.douban.data.message.MessageSendData;

/**
 * Created by JasonDong
 * <p>
 * on 2017/5/5.
 */

public interface MessageContract {

    interface View extends BaseView {

        void showReceiveMessage(MessageReceiveData data);

        void showSendMessage(MessageSendData data);

        void showGirlMessage(MessageImgData data);

    }

    interface Presenter extends BasePresenter<View> {

        void getReceiveData(String msg);

        void getSendData(String msg);

        void getGirlData();

    }

}
