package com.djw.douban.ui.home.music.presenter;

import com.djw.douban.base.CommonSubscriber;
import com.djw.douban.base.CommonSubscribers;
import com.djw.douban.base.RxPresenter;
import com.djw.douban.data.music.MusicRoot;
import com.djw.douban.http.RetrofitHelper;
import com.djw.douban.ui.home.music.contract.MusicContract;
import com.djw.douban.util.RxUtil;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by JasonDong on 2017/4/12.
 */

public class MusicPresenter extends RxPresenter<MusicContract.View> implements MusicContract.Presenter {

    private final RetrofitHelper helper;

    @Inject
    public MusicPresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getMusic(int start, int count, String tag, final boolean isLoadMore, boolean isShowProgress) {
        Subscription subscribe = helper.getMusicByTag(tag, start, count)
                .compose(RxUtil.<MusicRoot>rxSchedulerHelper())
                .subscribe(new CommonSubscribers<MusicRoot>(mView, isShowProgress) {
                    @Override
                    public void onNext(MusicRoot musicRoot) {
                        mView.showMusic(musicRoot, isLoadMore);
                    }
                });
        addSubscrebe(subscribe);

    }
}
