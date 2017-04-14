package com.djw.douban.ui.home.movies.presenter;

import com.djw.douban.base.CommonSubscribers;
import com.djw.douban.base.RxPresenter;
import com.djw.douban.data.movies.MoviesItemData;
import com.djw.douban.data.newmovies.NewMovieOne;
import com.djw.douban.data.newmovies.NewMoviesBaseData;
import com.djw.douban.data.newmovies.NewMoviesFive;
import com.djw.douban.data.newmovies.NewMoviesFour;
import com.djw.douban.data.newmovies.NewMoviesThree;
import com.djw.douban.data.newmovies.NewMoviesTwo;
import com.djw.douban.http.RetrofitHelper;
import com.djw.douban.ui.home.movies.contract.NewMoviesContract;
import com.djw.douban.util.RxUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by JasonDong on 2017/4/7.
 */

public class NewMoviesPresenter extends RxPresenter<NewMoviesContract.View> implements NewMoviesContract.Presenter {

    private final RetrofitHelper helper;

    @Inject
    NewMoviesPresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getNewMovies(int start, int count, final boolean isLoadMore, boolean isShowProgress) {
        Subscription subscribe = helper.getHotMovies(start, count)
                .compose(RxUtil.<MoviesItemData>rxSchedulerHelper())
                .subscribe(new CommonSubscribers<MoviesItemData>(mView, isShowProgress) {
                    @Override
                    public void onNext(MoviesItemData moviesItemData) {
                        List<NewMoviesBaseData> list = new LinkedList<>();
                        List<MoviesItemData.SubjectsBean> subjects = moviesItemData.getSubjects();
                        if (isLoadMore) {
                            for (int i = 0; i < subjects.size(); i++) {
                                MoviesItemData.SubjectsBean subjectsBean = subjects.get(i);
                                list.add(new NewMoviesFour(subjectsBean.getTitle(), subjectsBean.getImages().getLarge(), subjectsBean.getId(), String.valueOf(subjectsBean.getRating().getAverage())));
                            }
                        } else {
                            List<String> urls = new ArrayList<>();
                            List<String> titles = new ArrayList<>();
                            List<String> ids = new ArrayList<>();
                            for (int i = 0; i < 5; i++) {
                                titles.add(subjects.get(i).getTitle());
                                urls.add(subjects.get(i).getImages().getLarge());
                                ids.add(subjects.get(i).getId());
                            }
                            list.add(new NewMovieOne(titles, urls, ids));

                            list.add(new NewMoviesFive("精彩分类"));

                            List<String> urls_two = new ArrayList<>();
                            List<String> titles_two = new ArrayList<>();
                            List<String> ids_two = new ArrayList<>();
                            for (int i = 5; i < 9; i++) {
                                MoviesItemData.SubjectsBean subjectsBean = subjects.get(i);
                                urls_two.add(subjectsBean.getImages().getLarge());
                                titles_two.add(subjectsBean.getTitle());
                                ids_two.add(subjectsBean.getId());
                            }
                            list.add(new NewMoviesTwo(titles_two, urls_two, ids_two));

                            list.add(new NewMoviesFive("小编推荐"));

                            List<String> urls_three = new ArrayList<>();
                            List<String> titles_three = new ArrayList<>();
                            List<String> ids_three = new ArrayList<>();
                            for (int i = 9; i < 12; i++) {
                                MoviesItemData.SubjectsBean subjectsBean = subjects.get(i);
                                urls_three.add(subjectsBean.getImages().getLarge());
                                titles_three.add(subjectsBean.getTitle());
                                ids_three.add(subjectsBean.getId());
                            }
                            list.add(new NewMoviesThree(titles_three, urls_three, ids_three));

                            list.add(new NewMoviesFive("猜你喜欢"));

                            for (int i = 12; i < subjects.size(); i++) {
                                MoviesItemData.SubjectsBean subjectsBean = subjects.get(i);
                                list.add(new NewMoviesFour(subjectsBean.getTitle(), subjectsBean.getImages().getLarge(), subjectsBean.getId(), String.valueOf(subjectsBean.getRating().getAverage())));
                            }
                        }

                        mView.showNewMovies(list, isLoadMore);

                    }
                });
        addSubscrebe(subscribe);
    }
}
