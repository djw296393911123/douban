package com.djw.douban.ui.home.movies.presenter;

import com.djw.douban.base.CommonSubscriber;
import com.djw.douban.base.RxPresenter;
import com.djw.douban.data.movies.MoviesActorsData;
import com.djw.douban.data.movies.PeopleBaseData;
import com.djw.douban.data.movies.PeopleFour;
import com.djw.douban.data.movies.PeopleOne;
import com.djw.douban.data.movies.PeopleThree;
import com.djw.douban.data.movies.PeopleTwo;
import com.djw.douban.http.RetrofitHelper;
import com.djw.douban.ui.home.movies.contract.MoviesPeopleContract;
import com.djw.douban.util.RxUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by JasonDong on 2017/4/7.
 */

public class PeoplePresenter extends RxPresenter<MoviesPeopleContract.View> implements MoviesPeopleContract.Presenter {

    private final RetrofitHelper helper;

    @Inject
    public PeoplePresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getPeople(int id) {
        Subscription subscribe = helper.getMoviesPeople(id)
                .compose(RxUtil.<MoviesActorsData>rxSchedulerHelper())
                .subscribe(new CommonSubscriber<MoviesActorsData>(mView) {
                    @Override
                    public void onNext(MoviesActorsData data) {
                        List<PeopleBaseData> list = new ArrayList<>();
                        list.add(new PeopleOne(data.getAvatars().getLarge(), data.getName() + data.getName_en(), "性别：" + data.getGender() + "/出生地:" + data.getBorn_place()));
                        list.add(new PeopleFour("参演作品"));

                        List<MoviesActorsData.WorksBean> works = data.getWorks();
                        List<PeopleTwo> peopleTwos = new ArrayList<>();
                        for (int i = 0; i < works.size(); i++) {
                            MoviesActorsData.WorksBean.SubjectBean subject = works.get(i).getSubject();
                            try {
                                peopleTwos.add(new PeopleTwo(subject.getTitle(), subject.getId(), subject.getImages().getLarge(), String.valueOf(subject.getRating().getAverage()), subject.getDirectors().get(0).getId()));
                            } catch (Exception e) {
                                peopleTwos.add(new PeopleTwo(subject.getTitle(), subject.getId(), subject.getImages().getLarge(), String.valueOf(subject.getRating().getAverage()), ""));
                            }
                        }
                        list.add(new PeopleThree(peopleTwos));

                        mView.showPeople(list, data.getName());
                    }
                });
        addSubscrebe(subscribe);
    }
}
