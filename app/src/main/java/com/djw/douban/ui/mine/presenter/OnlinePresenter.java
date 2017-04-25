package com.djw.douban.ui.mine.presenter;

import com.djw.douban.base.CommonSubscribers;
import com.djw.douban.base.RxPresenter;
import com.djw.douban.data.mine.MineListData;
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
    public void getUrl(int start, int count, final boolean isLoadMore) {
        Subscription subscribe = helper.getHotMovies(start, count)
                .compose(RxUtil.<MoviesItemData>rxSchedulerHelper())
                .subscribe(new CommonSubscribers<MoviesItemData>(mView, false) {
                    @Override
                    public void onNext(MoviesItemData moviesItemData) {
                        List<MineListData> list = new ArrayList<>();
                        List<MoviesItemData.SubjectsBean> subjects = moviesItemData.getSubjects();
                        for (int i = 0; i < subjects.size(); i++) {
                            MoviesItemData.SubjectsBean subjectsBean = subjects.get(i);
                            list.add(new MineListData(subjectsBean.getId(), subjectsBean.getImages().getLarge(), i % 3 == 0 ? 3 : i % 2 == 0 ? 2 : 1, subjectsBean.getDirectors().get(0).getId()));
                        }

                        mView.showImg(list, isLoadMore);
                    }
                });
        addSubscrebe(subscribe);
    }
}
