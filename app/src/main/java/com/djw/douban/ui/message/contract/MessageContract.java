package com.djw.douban.ui.message.contract;

import com.djw.douban.base.BasePresenter;
import com.djw.douban.base.BaseView;
import com.djw.douban.data.message.MessageBaseData;
import com.djw.douban.data.message.MessageImgData;
import com.djw.douban.data.message.MessageSendData;
import com.djw.douban.data.message.MessageTimeData;

import java.util.List;

/**
 * Created by JasonDong
 * <p>
 * on 2017/5/5.
 */

public interface MessageContract {

    interface View extends BaseView {

        void showReceiveMessage(MessageBaseData data);

        void showSendMessage(MessageSendData data);

        void showGirlMessage(MessageImgData data);

        void showHistory(List<MessageBaseData> list);

        void showTime(MessageTimeData data);

        void showDelete();

    }

    interface Presenter extends BasePresenter<View> {

        void getReceiveData(String msg);

        void getSendData(String msg);

        void getGirlData();

        void getHistory();

        void getTime();

        void deleteHistory();

    }

}
