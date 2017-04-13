package com.djw.douban.ui.home.music.presenter;

import com.djw.douban.base.CommonSubscriber;
import com.djw.douban.base.RxPresenter;
import com.djw.douban.data.music.Musics;
import com.djw.douban.http.RetrofitHelper;
import com.djw.douban.ui.home.music.contract.MusicInfoContract;
import com.djw.douban.util.RxUtil;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by JasonDong on 2017/4/12.
 */

public class MusicInfoPresenter extends RxPresenter<MusicInfoContract.View> implements MusicInfoContract.Presenter {

    private final RetrofitHelper helper;

    @Inject
    public MusicInfoPresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getMusic(String id) {
        Subscription subscribe = helper.getMusicInfo(id)
                .compose(RxUtil.<Musics>rxSchedulerHelper())
                .subscribe(new CommonSubscriber<Musics>(mView) {
                    @Override
                    public void onNext(Musics musics) {
                        mView.showMusci(musics);
                    }
                });
        addSubscrebe(subscribe);
    }
}
