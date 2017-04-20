package com.djw.douban.ui.mine.presenter;

import android.util.Log;

import com.djw.douban.base.CommonSubscriber;
import com.djw.douban.base.CommonSubscribers;
import com.djw.douban.base.RxPresenter;
import com.djw.douban.data.mine.LikeOrHideData;
import com.djw.douban.data.mine.MineItemData;
import com.djw.douban.data.movies.MoviesActorsData;
import com.djw.douban.data.movies.MoviesItemData;
import com.djw.douban.http.RetrofitHelper;
import com.djw.douban.ui.mine.contract.OnlineContract;
import com.djw.douban.util.RxUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by JasonDong on 2017/4/13.
 */

public class OnlinePresenter extends RxPresenter<OnlineContract.View> implements OnlineContract.Presenter {

    private final RetrofitHelper helper;

    @Inject
    OnlinePresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getOnline(String cate) {
        Subscription subscribe = helper.getOnline(cate)
                .compose(RxUtil.<MineItemData>rxSchedulerHelper())
                .subscribe(new CommonSubscribers<MineItemData>(mView, false) {
                    @Override
                    public void onNext(MineItemData mineItemData) {
                        Log.i("mineitemdata", mineItemData.toString());
                        mView.showOnline(mineItemData);
                    }
                });
        addSubscrebe(subscribe);
    }

    @Override
    public void getUrl(int start, int count) {
        Subscription subscribe = helper.getTop250(start, count)
                .compose(RxUtil.<MoviesItemData>rxSchedulerHelper())
                .subscribe(new CommonSubscribers<MoviesItemData>(mView, false) {
                    @Override
                    public void onNext(MoviesItemData moviesItemData) {
                        List<LikeOrHideData> list = new ArrayList<>();
                        List<MoviesItemData.SubjectsBean> subjects = moviesItemData.getSubjects();
                        for (int i = 0; i < subjects.size(); i++) {
                            MoviesItemData.SubjectsBean subjectsBean = subjects.get(i);
                            list.add(new LikeOrHideData(subjectsBean.getTitle(), subjectsBean.getImages().getLarge(), String.valueOf(subjectsBean.getRating().getAverage()), subjectsBean.getId()));
                        }
                        mView.showImg(list);
                    }
                });
        addSubscrebe(subscribe);
    }
}
