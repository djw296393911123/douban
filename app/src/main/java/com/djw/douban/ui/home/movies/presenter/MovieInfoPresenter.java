package com.djw.douban.ui.home.movies.presenter;

import com.djw.douban.base.CommonSubscriber;
import com.djw.douban.base.RxPresenter;
import com.djw.douban.data.ParamsData;
import com.djw.douban.data.movies.MoviesActorsData;
import com.djw.douban.data.movies.MoviesInfoBaseData;
import com.djw.douban.data.movies.MoviesInfoData;
import com.djw.douban.data.movies.MoviesInfoType;
import com.djw.douban.data.movies.MoviesItemData;
import com.djw.douban.data.movies.MoviesPeople;
import com.djw.douban.data.movies.MoviesTextData;
import com.djw.douban.data.newmovies.MovieInfoTopData;
import com.djw.douban.data.newmovies.MoviesInfoAlsoLikeData;
import com.djw.douban.data.newmovies.NewMoviesFour;
import com.djw.douban.http.RetrofitHelper;
import com.djw.douban.ui.home.movies.contract.MovieInfoContract;
import com.djw.douban.util.RxUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;

/**
 * Created by JasonDong on 2017/4/7.
 */

public class MovieInfoPresenter extends RxPresenter<MovieInfoContract.View> implements MovieInfoContract.Presenter {

    private final RetrofitHelper helper;


    @Inject
    MovieInfoPresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getInfo(int id, String direct) {
        Observable<MoviesItemData> hotMovies = helper.getHotMovies(ParamsData.START, ParamsData.COUNT);
        Observable<MoviesInfoData> movieInfo = helper.getMovieInfo(id);
        Observable<MoviesActorsData> moviesPeople = helper.getMoviesPeople(Integer.parseInt(direct));
        Subscription subscribe = Observable.concat(movieInfo, moviesPeople, hotMovies)
                .compose(RxUtil.rxSchedulerHelper())
                .subscribe(new CommonSubscriber<Object>(mView) {

                    @Override
                    public void onNext(Object o) {

                        List<MoviesInfoBaseData> list = new ArrayList<>();
                        MovieInfoTopData topData = null;

                        if (o instanceof MoviesInfoData) {
                            MoviesInfoData moviesInfoData = (MoviesInfoData) o;
                            topData = new MovieInfoTopData(moviesInfoData.getImages().getLarge(), moviesInfoData.getTitle(), moviesInfoData.getYear() + "上映", moviesInfoData.getCountries().toString(), "评分" + moviesInfoData.getRating().getAverage(), String.valueOf(moviesInfoData.getWish_count()) + "人", moviesInfoData.getGenres().toString());

                            list.add(new MoviesInfoType("简介"));
                            list.add(new MoviesTextData(moviesInfoData.getSummary()));
                            List<MoviesInfoData.CastsBean> casts = moviesInfoData.getCasts();
                            if (casts.size() > 0) {
                                list.add(new MoviesInfoType("主演"));
                                for (int i = 0; i < casts.size(); i++) {
                                    MoviesInfoData.CastsBean castsBean = casts.get(i);
                                    try {
                                        list.add(new MoviesPeople(castsBean.getAvatars().getLarge(), castsBean.getName(), Integer.parseInt(castsBean.getId())));
                                    } catch (Exception e) {
                                        list.add(new MoviesPeople("", castsBean.getName(), 1));
                                    }
                                }
                            }

                            List<MoviesInfoData.DirectorsBean> directors = moviesInfoData.getDirectors();
                            if (directors.size() > 0) {
                                list.add(new MoviesInfoType("导演"));
                                for (int i = 0; i < directors.size(); i++) {
                                    MoviesInfoData.DirectorsBean directorsBean = directors.get(i);
                                    MoviesInfoData.DirectorsBean.AvatarsBeanX avatars = directorsBean.getAvatars();
                                    list.add(new MoviesPeople(avatars.getLarge(), directorsBean.getName(), Integer.parseInt(directorsBean.getId())));
                                }
                            }

                        } else if (o instanceof MoviesActorsData) {


                            List<MoviesActorsData.WorksBean> works = ((MoviesActorsData) o).getWorks();
                            if (works.size() > 0) {
                                list.add(new MoviesInfoType("该导演的其他热门电影"));

                                List<NewMoviesFour> fours = new ArrayList<>();

                                for (int i = 0; i < works.size(); i++) {
                                    MoviesActorsData.WorksBean.SubjectBean subjectsBean = works.get(i).getSubject();
                                    fours.add(new NewMoviesFour(subjectsBean.getTitle(), subjectsBean.getImages().getLarge(), subjectsBean.getId(), String.valueOf(subjectsBean.getRating().getAverage()) + "分", subjectsBean.getDirectors().size() > 0 ? subjectsBean.getDirectors().get(0).getName() : "djw", subjectsBean.getDirectors().size() > 0 ? subjectsBean.getDirectors().get(0).getId() : "12138"));
                                }

                                list.add(new MoviesInfoAlsoLikeData(fours));
                            }

                        } else if (o instanceof MoviesItemData) {


                            List<MoviesItemData.SubjectsBean> subjects = ((MoviesItemData) o).getSubjects();

                            if (subjects.size() > 0) {
                                list.add(new MoviesInfoType("喜欢这部电影的也喜欢"));
                                List<NewMoviesFour> fours = new ArrayList<>();
                                for (int i = 0; i < subjects.size(); i++) {
                                    MoviesItemData.SubjectsBean subjectsBean = subjects.get(i);
                                    fours.add(new NewMoviesFour(subjectsBean.getTitle(), subjectsBean.getImages().getLarge(), subjectsBean.getId(), String.valueOf(subjectsBean.getRating().getAverage()) + "分", subjectsBean.getDirectors().get(0).getName(), subjectsBean.getDirectors().get(0).getId()));
                                }

                                list.add(new MoviesInfoAlsoLikeData(fours));

                            }

                        }

                        mView.showInfo(list, topData);
                    }
                });

        addSubscrebe(subscribe);
    }
}
