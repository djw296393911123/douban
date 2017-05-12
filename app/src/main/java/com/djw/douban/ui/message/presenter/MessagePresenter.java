package com.djw.douban.ui.message.presenter;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.djw.douban.base.CommonSubscribers;
import com.djw.douban.base.RxPresenter;
import com.djw.douban.data.girl.GankListItemData;
import com.djw.douban.data.message.MessageBaseData;
import com.djw.douban.data.message.MessageImgData;
import com.djw.douban.data.message.MessageReceiveData;
import com.djw.douban.data.message.MessageSendData;
import com.djw.douban.data.message.MessageTimeData;
import com.djw.douban.data.message.MessageUrlData;
import com.djw.douban.db.DBHelper;
import com.djw.douban.http.RetrofitHelper;
import com.djw.douban.ui.message.contract.MessageContract;
import com.djw.douban.util.RxUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by JasonDong
 * <p>
 * on 2017/5/5.
 */

public class MessagePresenter extends RxPresenter<MessageContract.View> implements MessageContract.Presenter {

    private final RetrofitHelper helper;

    private SQLiteDatabase database;

    @Inject
    DBHelper dbHelper;
    private boolean isFirst = true;
    private String format;

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
                        if (data.getUrl() == null) {
                            insert(data.getText(), MessageBaseData.RECEIVE);
                            data.setType(MessageBaseData.RECEIVE);
                            mView.showReceiveMessage(data);
                        } else {
                            getGirlData();
                            insert(data.getUrl(), MessageBaseData.URL);
                            data.setType(MessageBaseData.URL);
                            mView.showReceiveMessage(new MessageUrlData(data.getUrl()));
                        }

                    }
                });
        addSubscrebe(subscribe);
    }

    @Override
    public void getSendData(String msg) {
        if (isFirst) {
            format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
            insert(format, MessageBaseData.TIME);
            getTime();
            isFirst = false;
        }
        insert(msg, MessageBaseData.SEND);
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
                        insert(gankListItemData.getResults().get(0).getUrl(), MessageBaseData.IMAGE);
                        mView.showGirlMessage(new MessageImgData(gankListItemData.getResults().get(0).getUrl()));
                    }
                });
        addSubscrebe(subscribe);
    }

    @Override
    public void getHistory() {
        List<MessageBaseData> list = new ArrayList<>();
        database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(DBHelper.TABLE_MESSAGE, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int type = cursor.getInt(cursor.getColumnIndex(DBHelper.TYPE));
            String msg = cursor.getString(cursor.getColumnIndex(DBHelper.MESSAGE));
            switch (type) {
                case MessageBaseData.IMAGE:
                    list.add(new MessageImgData(msg));
                    break;
                case MessageBaseData.RECEIVE:
                    list.add(new MessageReceiveData(msg, 1, null));
                    break;
                case MessageBaseData.SEND:
                    list.add(new MessageSendData(msg));
                    break;
                case MessageBaseData.TIME:
                    list.add(new MessageTimeData(msg));
                    break;
                case MessageBaseData.URL:
                    list.add(new MessageUrlData(msg));
                    break;
            }
        }
        mView.showHistory(list);
        cursor.close();
    }

    @Override
    public void getTime() {
        mView.showTime(new MessageTimeData(format));
    }

    @Override
    public void deleteHistory() {
        database.delete(DBHelper.TABLE_MESSAGE, null, null);
        mView.showDelete();
    }

    private void insert(String msg, int type) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.TYPE, type);
        values.put(DBHelper.MESSAGE, msg);
        database.insert(DBHelper.TABLE_MESSAGE, null, values);
    }

}
