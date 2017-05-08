package com.djw.douban.ui.message.presenter;

import android.util.Log;

import com.djw.douban.base.CommonSubscribers;
import com.djw.douban.base.RxPresenter;
import com.djw.douban.data.girl.GankListItemData;
import com.djw.douban.data.message.MessageBaseData;
import com.djw.douban.data.message.MessageImgData;
import com.djw.douban.data.message.MessageReceiveData;
import com.djw.douban.data.message.MessageSendData;
import com.djw.douban.http.RetrofitHelper;
import com.djw.douban.ui.message.contract.MessageContract;
import com.djw.douban.util.RxUtil;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by JasonDong
 * <p>
 * on 2017/5/5.
 */

public class MessagePresenter extends RxPresenter<MessageContract.View> implements MessageContract.Presenter {

    private final RetrofitHelper helper;

    @Inject
    MessagePresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getReceiveData(String msg) {
        Subscription subscribe = helper.getMessage(msg)
                .compose(RxUtil.<MessageReceiveData>rxSchedulerHelper())
                .subscribe(new CommonSubscribers<MessageReceiveData>(mView, false) {
                    @Override
                    public void onNext(MessageReceiveData data) {
                        Log.i("data", data.toString());
                        if (data.getUrl() == null) {
                            data.setType(MessageBaseData.RECEIVE);
                            mView.showReceiveMessage(data);
                        } else {
                            getGirlData();
                        }

                    }
                });
        addSubscrebe(subscribe);
    }

    @Override
    public void getSendData(String msg) {
        mView.showSendMessage(new MessageSendData(msg));
        getReceiveData(msg);
    }

    @Override
    public void getGirlData() {
        Subscription subscribe = helper.getGirl()
                .compose(RxUtil.<GankListItemData>rxSchedulerHelper())
                .subscribe(new CommonSubscribers<GankListItemData>(mView, false) {
                    @Override
                    public void onNext(GankListItemData gankListItemData) {
                        mView.showGirlMessage(new MessageImgData(gankListItemData.getResults().get(0).getUrl()));
                    }
                });
        addSubscrebe(subscribe);
    }
}
