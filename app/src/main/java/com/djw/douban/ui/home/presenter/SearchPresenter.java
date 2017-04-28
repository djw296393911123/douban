package com.djw.douban.ui.home.presenter;

import com.djw.douban.base.CommonSubscribers;
import com.djw.douban.base.RxPresenter;
import com.djw.douban.data.ParamsData;
import com.djw.douban.data.book.BookRoot;
import com.djw.douban.data.book.Books;
import com.djw.douban.data.movies.MoviesItemData;
import com.djw.douban.data.movies.TypeData;
import com.djw.douban.data.music.MusicRoot;
import com.djw.douban.data.music.Musics;
import com.djw.douban.data.search.SearchBaseData;
import com.djw.douban.data.search.SearchByTag;
import com.djw.douban.data.search.SearchNormalData;
import com.djw.douban.data.search.SearchTypeData;
import com.djw.douban.http.RetrofitHelper;
import com.djw.douban.ui.home.contract.SearchContract;
import com.djw.douban.util.RxUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;

/**
 * Created by JasonDong on 2017/4/17.
 */

public class SearchPresenter extends RxPresenter<SearchContract.View> implements SearchContract.Presenter {

    private final RetrofitHelper helper;

    @Inject
    SearchPresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getHotSearch(int start, int count) {
        Subscription subscribe = helper.getHotMovies(start, count)
                .compose(RxUtil.<MoviesItemData>rxSchedulerHelper())
                .subscribe(new CommonSubscribers<MoviesItemData>(mView, true) {
                    @Override
                    public void onNext(MoviesItemData moviesItemData) {
                        List<SearchBaseData> list = new ArrayList<>();

                        list.add(new SearchTypeData("热门搜索"));

                        List<MoviesItemData.SubjectsBean> subjects = moviesItemData.getSubjects();
                        for (int i = 0; i < subjects.size(); i++) {
                            MoviesItemData.SubjectsBean subjectsBean = subjects.get(i);
                            list.add(new SearchNormalData(subjectsBean.getTitle(), subjectsBean.getId(), ParamsData.GO_MOVIES, subjectsBean.getDirectors().get(0).getId()));
                        }
                        mView.showHotSearch(list);
                    }
                });
        addSubscrebe(subscribe);
    }

    @Override
    public void getSearchData(String tag, int start, int count) {
        Observable<TypeData> type = helper.getType(tag, start, count);
        Observable<BookRoot> bookByTag = helper.getBookByTag(tag, start, count);
        Observable<MusicRoot> musicByTag = helper.getMusicByTag(tag, start, count);
        Subscription subscribe = Observable.concat(type, bookByTag, musicByTag)
                .compose(RxUtil.rxSchedulerHelper())
                .subscribe(new CommonSubscribers<Object>(mView, false) {
                    @Override
                    public void onNext(Object o) {
                        List<SearchBaseData> list = new ArrayList<>();
                        if (o instanceof TypeData) {

                            List<TypeData.SubjectsBean> subjects = ((TypeData) o).getSubjects();

                            if (subjects.size() > 0) list.add(new SearchTypeData("电影"));

                            for (int i = 0; i < subjects.size(); i++) {
                                TypeData.SubjectsBean subjectsBean = subjects.get(i);
                                list.add(new SearchNormalData(subjectsBean.getTitle(), subjectsBean.getId(), ParamsData.GO_MOVIES, subjectsBean.getDirectors().get(0).getId()));
                            }
                        } else if (o instanceof BookRoot) {
                            List<Books> books = ((BookRoot) o).getBooks();

                            if (books.size() > 0) list.add(new SearchTypeData("图书"));

                            for (int i = 0; i < books.size(); i++) {
                                Books book = books.get(i);
                                list.add(new SearchNormalData(book.getTitle(), book.getId(), ParamsData.GO_BOOKS, ""));
                            }
                        } else if (o instanceof MusicRoot) {
                            List<Musics> musics = ((MusicRoot) o).getMusics();

                            if (musics.size() > 0) list.add(new SearchTypeData("专辑"));

                            for (int i = 0; i < musics.size(); i++) {
                                Musics music = musics.get(i);
                                list.add(new SearchNormalData(music.getTitle(), music.getId(), ParamsData.GO_MUSICS, ""));
                            }
                        }
                        mView.showSearchData(list);
                    }
                });
        addSubscrebe(subscribe);
    }
}
