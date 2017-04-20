package com.djw.douban.ui.home.movies.presenter;

import com.djw.douban.base.CommonSubscriber;
import com.djw.douban.base.RxPresenter;
import com.djw.douban.data.ParamsData;
import com.djw.douban.data.movies.CommingSoonData;
import com.djw.douban.data.movies.HotData;
import com.djw.douban.data.movies.MoviesHomeData;
import com.djw.douban.data.movies.MoviesOne;
import com.djw.douban.data.movies.MoviesOnee;
import com.djw.douban.data.movies.MoviesThree;
import com.djw.douban.data.movies.MoviesThreee;
import com.djw.douban.data.movies.MoviesTwo;
import com.djw.douban.data.movies.MoviesTwoo;
import com.djw.douban.data.movies.NorthAmericaItemData;
import com.djw.douban.data.movies.Top250Data;
import com.djw.douban.http.RetrofitHelper;
import com.djw.douban.ui.home.movies.contract.MoviesHomeContract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by JasonDong on 2017/4/10.
 */

public class MoviesPresenter extends RxPresenter<MoviesHomeContract.View> implements MoviesHomeContract.Presenter {

    private final RetrofitHelper helper;

    @Inject
    public MoviesPresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getMoviesOne(int start, int count) {
        Observable<HotData> hotMovies = helper.getHotMovie(start, count);
        Observable<CommingSoonData> commingSoon = helper.getCommingSoons(start, count);
        Observable<Top250Data> top250 = helper.getTop250s(start, ParamsData.COUNT_TWO);
        Observable<NorthAmericaItemData> northAmerica = helper.getNorthAmerica(start, ParamsData.COUNT_THREE);
        Subscription subscribe = Observable.merge(hotMovies, commingSoon, top250, northAmerica)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CommonSubscriber<Object>(mView) {
                    @Override
                    public void onNext(Object o) {
                        List<MoviesHomeData> list = new ArrayList<>();
                        if (o instanceof HotData) {
                            List<MoviesOnee> ones = new ArrayList<>();
                            List<HotData.SubjectsBean> subjects = ((HotData) o).getSubjects();
                            for (int i = 0; i < subjects.size(); i++) {
                                HotData.SubjectsBean subjectsBean = subjects.get(i);
                                MoviesOnee moviesOnee = new MoviesOnee(subjectsBean.getTitle(), subjectsBean.getImages().getLarge(), subjectsBean.getRating().getAverage(), Integer.parseInt(subjectsBean.getId()), subjectsBean.getDirectors().get(0).getId());
                                ones.add(moviesOnee);
                            }
                            list.add(new MoviesOne("影院热映", ones));
                        } else if (o instanceof CommingSoonData) {
                            List<MoviesOnee> ones = new ArrayList<>();
                            List<CommingSoonData.SubjectsBean> subjects = ((CommingSoonData) o).getSubjects();
                            for (int i = 0; i < subjects.size(); i++) {
                                CommingSoonData.SubjectsBean subjectsBean = subjects.get(i);
                                MoviesOnee moviesOnee = new MoviesOnee(subjectsBean.getTitle(), subjectsBean.getImages().getLarge(), subjectsBean.getRating().getAverage(), Integer.parseInt(subjectsBean.getId()), subjectsBean.getDirectors().get(0).getId());
                                ones.add(moviesOnee);
                            }
                            list.add(new MoviesOne("院线即将上映", ones));
                        } else if (o instanceof Top250Data) {
                            List<MoviesTwoo> twoos = new ArrayList<>();
                            List<Top250Data.SubjectsBean> subjects = ((Top250Data) o).getSubjects();
                            MoviesTwoo top250 = new MoviesTwoo("豆瓣Top250", subjects.get(0).getImages().getLarge());
                            MoviesTwoo top2500 = new MoviesTwoo("本周口碑榜", subjects.get(1).getImages().getLarge());
                            MoviesTwoo top25000 = new MoviesTwoo("新片榜", subjects.get(2).getImages().getLarge());
                            MoviesTwoo top250000 = new MoviesTwoo("票房榜", subjects.get(3).getImages().getLarge());
                            twoos.add(top250);
                            twoos.add(top2500);
                            twoos.add(top25000);
                            twoos.add(top250000);
                            list.add(new MoviesTwo("精选榜单", twoos));
                        } else {
                            List<MoviesThreee> threes = new ArrayList<>();
                            List<NorthAmericaItemData.SubjectsBean> subjects = ((NorthAmericaItemData) o).getSubjects();
                            for (int i = 0; i < subjects.size(); i++) {
                                NorthAmericaItemData.SubjectsBean subjectsBean = subjects.get(i);
                                NorthAmericaItemData.SubjectsBean.SubjectBean subject = subjectsBean.getSubject();
                                NorthAmericaItemData.SubjectsBean.SubjectBean.ImagesBean images = subject.getImages();
                                MoviesThreee moviesThreee = new MoviesThreee(subject.getTitle(), images == null ? "" : images.getLarge(), subject.getRating().getAverage(), Integer.parseInt(subject.getId()), subject.getDirectors().get(0).getId());
                                threes.add(moviesThreee);
                            }
                            list.add(new MoviesThree("猜您喜欢", threes));
                        }
                        mView.showMoviesOne(list);
                    }
                });
        addSubscrebe(subscribe);
    }
}
