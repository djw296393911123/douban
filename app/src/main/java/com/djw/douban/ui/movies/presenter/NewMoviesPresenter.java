package com.djw.douban.ui.movies.presenter;

import com.djw.douban.R;
import com.djw.douban.base.ApiException;
import com.djw.douban.base.CommonSubscribers;
import com.djw.douban.base.RxPresenter;
import com.djw.douban.data.movies.MoviesItemData;
import com.djw.douban.data.newmovies.MoviesEightItemData;
import com.djw.douban.data.newmovies.NewMovieNineItemData;
import com.djw.douban.data.newmovies.NewMovieOne;
import com.djw.douban.data.newmovies.NewMovieTen;
import com.djw.douban.data.newmovies.NewMoviesBaseData;
import com.djw.douban.data.newmovies.NewMoviesEight;
import com.djw.douban.data.newmovies.NewMoviesFive;
import com.djw.douban.data.newmovies.NewMoviesFour;
import com.djw.douban.data.newmovies.NewMoviesNine;
import com.djw.douban.data.newmovies.NewMoviesSeven;
import com.djw.douban.data.newmovies.NewMoviesThree;
import com.djw.douban.data.newmovies.NewMoviesTwo;
import com.djw.douban.data.newmovies.NewMoviewTwoItemData;
import com.djw.douban.http.RetrofitHelper;
import com.djw.douban.ui.movies.contract.NewMoviesContract;
import com.djw.douban.util.RxUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by JasonDong
 * <p>
 * on 2017/4/7.
 */

public class NewMoviesPresenter extends RxPresenter<NewMoviesContract.View> implements NewMoviesContract.Presenter {

    private final RetrofitHelper helper;
    private int[] twos = {R.mipmap.hot, R.mipmap.jingpin, R.mipmap.tejia, R.mipmap.tuijian, R.mipmap.xuanyi, R.mipmap.dongzuo, R.mipmap.zainan, R.mipmap.juqing};
    private String[] twos_title = {"热门", "喜剧", "犯罪", "爱情", "悬疑", "动作", "灾难", "剧情"};

    @Inject
    NewMoviesPresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getNewMovies(final int start, int count, final boolean isLoadMore, boolean isShowProgress) {
        Subscription subscribe = helper.getCommingSoon(start, count)
                .compose(RxUtil.<MoviesItemData>rxSchedulerHelper())
                .subscribe(new CommonSubscribers<MoviesItemData>(mView, isShowProgress) {
                    @Override
                    public void onNext(MoviesItemData moviesItemData) {
                        List<NewMoviesBaseData> list = new LinkedList<>();
                        List<MoviesItemData.SubjectsBean> subjects = moviesItemData.getSubjects();
                        if (isLoadMore) {
                            if (subjects.size() > 0) {
                                for (int i = 0; i < subjects.size(); i++) {
                                    MoviesItemData.SubjectsBean subjectsBean = subjects.get(i);
                                    List<MoviesItemData.SubjectsBean.DirectorsBean> directors = subjectsBean.getDirectors();
                                    list.add(new NewMoviesFour(subjectsBean.getTitle(), subjectsBean.getImages().getLarge(), subjectsBean.getId(), String.valueOf(subjectsBean.getRating().getAverage()), directors.size() > 0 ? directors.get(0).getName() : "JasonDong", Integer.parseInt(subjectsBean.getRating().getStars()) > 10));
                                }
                            } else new ApiException("没有更多数据");
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

//                            list.add(new NewMoviesFive("精彩分类"));

                            List<NewMoviewTwoItemData> twoItemDatas = new ArrayList<>();
                            for (int i = 0; i < twos.length; i++) {
                                twoItemDatas.add(new NewMoviewTwoItemData(twos[i], twos_title[i], String.valueOf(i)));
                            }
                            list.add(new NewMoviesTwo(twoItemDatas));

                            List<String> titles_tuijian = new ArrayList<>();
                            List<Integer> id_tuijian = new ArrayList<>();

                            titles_tuijian.add("经典老电影");
                            id_tuijian.add(0);
                            titles_tuijian.add("影院热映");
                            id_tuijian.add(1);
                            titles_tuijian.add("小编推荐");
                            id_tuijian.add(2);
                            titles_tuijian.add("周星驰");
                            id_tuijian.add(3);
                            titles_tuijian.add("北美票房排行榜---最近");
                            id_tuijian.add(4);
                            titles_tuijian.add("好评如潮，经典耐看，速速围观！");
                            id_tuijian.add(5);

                            list.add(new NewMoviesSeven(titles_tuijian, id_tuijian));

                            list.add(new NewMoviesFive("精选榜单", R.mipmap.paihangbang));

                            List<String> urls_three = new ArrayList<>();
                            List<String> titles_three = new ArrayList<>();
                            List<String> ids_three = new ArrayList<>();
                            for (int i = 5; i < 8; i++) {
                                MoviesItemData.SubjectsBean subjectsBean = subjects.get(i);
                                urls_three.add(subjectsBean.getImages().getLarge());
                                titles_three.add(subjectsBean.getTitle());
                                ids_three.add(subjectsBean.getId());
                            }
                            list.add(new NewMoviesThree(titles_three, urls_three, ids_three));

                            list.add(new NewMoviesFive("口碑好评", R.mipmap.koubei));

                            List<MoviesEightItemData> eights = new ArrayList<>();
                            for (int i = 8; i < 16; i++) {
                                MoviesItemData.SubjectsBean subjectsBean = subjects.get(i);
                                eights.add(new MoviesEightItemData(subjectsBean.getTitle(), subjectsBean.getRating().getAverage() + "分", subjectsBean.getImages().getLarge(), subjectsBean.getId()));
                            }
                            eights.add(new MoviesEightItemData("", "", String.valueOf(R.mipmap.look_more), "12138"));
                            list.add(new NewMoviesEight(eights));

                            List<String> titles_tuijian2 = new ArrayList<>();
                            List<Integer> id_tuijian2 = new ArrayList<>();

                            titles_tuijian2.add("经典老电影");
                            id_tuijian2.add(0);
                            titles_tuijian2.add("影院热映");
                            id_tuijian2.add(1);
                            titles_tuijian2.add("小编推荐");
                            id_tuijian2.add(2);
                            titles_tuijian2.add("周星驰");
                            id_tuijian2.add(3);
                            titles_tuijian2.add("北美票房排行榜---最近");
                            id_tuijian2.add(4);
                            titles_tuijian2.add("好评如潮，经典耐看，速速围观！");
                            id_tuijian2.add(5);

                            list.add(new NewMoviesSeven(titles_tuijian2, id_tuijian2));

                            list.add(new NewMoviesFive("近期热映", R.mipmap.hot));

                            List<NewMovieNineItemData> nines = new ArrayList<>();
                            for (int i = 16; i < 22; i++) {
                                MoviesItemData.SubjectsBean subjectsBean = subjects.get(i);
                                nines.add(new NewMovieNineItemData(subjectsBean.getImages().getLarge(), subjectsBean.getId()));
                            }
                            list.add(new NewMoviesNine(nines));

                            list.add(new NewMoviesFive("小编推荐", R.mipmap.tuijian));

                            List<NewMovieNineItemData> ten = new ArrayList<>();
                            for (int i = 22; i < 30; i++) {
                                MoviesItemData.SubjectsBean subjectsBean = subjects.get(i);
                                ten.add(new NewMovieNineItemData(subjectsBean.getImages().getLarge(), subjectsBean.getId()));
                            }
                            list.add(new NewMovieTen(ten));

                            list.add(new NewMoviesFive("即将上映", R.mipmap.jijiangshangying));

                            for (int i = 30; i < subjects.size(); i++) {
                                MoviesItemData.SubjectsBean subjectsBean = subjects.get(i);
                                list.add(new NewMoviesFour(subjectsBean.getTitle(), subjectsBean.getImages().getLarge(), subjectsBean.getId(), String.valueOf(subjectsBean.getRating().getAverage()), subjectsBean.getDirectors().size() > 0 ? subjectsBean.getDirectors().get(0).getName() : "JasonDong", Integer.parseInt(subjectsBean.getRating().getStars()) > 10));
                            }

//                            list.add(new NewMoviesSix("查看更多"));
                        }

                        mView.showNewMovies(list, isLoadMore);

                    }
                });
        addSubscrebe(subscribe);
    }
}
