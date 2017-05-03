package com.djw.douban.ui.movies.presenter;

import com.djw.douban.base.CommonSubscriber;
import com.djw.douban.base.RxPresenter;
import com.djw.douban.data.movies.MoviesInfoBaseData;
import com.djw.douban.data.movies.MoviesInfoData;
import com.djw.douban.data.movies.MoviesInfoType;
import com.djw.douban.data.movies.MoviesPeople;
import com.djw.douban.data.movies.MoviesTextData;
import com.djw.douban.data.newmovies.MovieInfoTopData;
import com.djw.douban.http.RetrofitHelper;
import com.djw.douban.ui.movies.contract.MovieInfoContract;
import com.djw.douban.util.RxUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by JasonDong
 * <p>
 * on 2017/4/7.
 */

public class MovieInfoPresenter extends RxPresenter<MovieInfoContract.View> implements MovieInfoContract.Presenter {

    private final RetrofitHelper helper;


    @Inject
    MovieInfoPresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getInfo(int id) {
        Subscription subscribe = helper.getMovieInfo(id)
                .compose(RxUtil.rxSchedulerHelper())
                .subscribe(new CommonSubscriber<Object>(mView) {

                    @Override
                    public void onNext(Object o) {
                        List<MoviesInfoBaseData> list = new ArrayList<>();
                        MoviesInfoData moviesInfoData = (MoviesInfoData) o;
                        MovieInfoTopData topData = new MovieInfoTopData(
                                moviesInfoData.getImages().getLarge(),
                                moviesInfoData.getTitle(),
                                moviesInfoData.getYear() + "上映",
                                moviesInfoData.getCountries().toString(),
                                moviesInfoData.getRating().getAverage() == 0.0 ? "暂无评分" : "评分" + moviesInfoData.getRating().getAverage(),
                                String.valueOf(moviesInfoData.getWish_count()) + "人",
                                moviesInfoData.getGenres().toString());

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
                                list.add(new MoviesPeople(avatars == null ? "" : avatars.getLarge(), directorsBean.getName(), Integer.parseInt(directorsBean.getId() == null ? "110" : directorsBean.getId())));
                            }
                        }

                        mView.showInfo(list, topData);
                    }
                });

        addSubscrebe(subscribe);
    }
}
