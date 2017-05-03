package com.djw.douban.ui.music.presenter;

import com.djw.douban.base.CommonSubscribers;
import com.djw.douban.base.RxPresenter;
import com.djw.douban.data.music.MusicRoot;
import com.djw.douban.http.RetrofitHelper;
import com.djw.douban.ui.music.contract.MusicMoreContract;
import com.djw.douban.util.RxUtil;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by JasonDong
 * <p>
 * on 2017/4/18.
 */

public class MusicMorePresenter extends RxPresenter<MusicMoreContract.View> implements MusicMoreContract.Presenter {

    private final RetrofitHelper helper;

    @Inject
    MusicMorePresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getMore(String tag, int start, int count, final boolean isLoadMore, boolean isShowProgress) {
        Subscription subscribe = helper.getMusicByTag(tag, start, count)
                .compose(RxUtil.<MusicRoot>rxSchedulerHelper())
                .subscribe(new CommonSubscribers<MusicRoot>(mView, isShowProgress) {
                    @Override
                    public void onNext(MusicRoot musicRoot) {
                        mView.showMore(musicRoot, isLoadMore);
                    }
                });
        addSubscrebe(subscribe);
    }
}
