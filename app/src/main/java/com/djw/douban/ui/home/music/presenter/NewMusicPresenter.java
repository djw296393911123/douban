package com.djw.douban.ui.home.music.presenter;

import com.djw.douban.base.CommonSubscribers;
import com.djw.douban.base.RxPresenter;
import com.djw.douban.data.music.MusicRoot;
import com.djw.douban.data.music.MusicRootHY;
import com.djw.douban.data.music.MusicRootOM;
import com.djw.douban.data.music.MusicRootRH;
import com.djw.douban.data.music.Musics;
import com.djw.douban.data.newmusic.MusicBaseData;
import com.djw.douban.data.newmusic.MusicChooseData;
import com.djw.douban.data.newmusic.MusicContentData;
import com.djw.douban.data.newmusic.MusicInfoData;
import com.djw.douban.data.newmusic.MusicLikeData;
import com.djw.douban.data.newmusic.MusicNewFiveData;
import com.djw.douban.data.newmusic.MusicNoMoreData;
import com.djw.douban.data.newmusic.MusicTypeData;
import com.djw.douban.http.RetrofitHelper;
import com.djw.douban.ui.home.music.contract.NewMusicContract;
import com.djw.douban.util.RxUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;

/**
 * Created by JasonDong on 2017/4/18.
 */

public class NewMusicPresenter extends RxPresenter<NewMusicContract.View> implements NewMusicContract.Presenter {

    private final RetrofitHelper helper;

    private String[] chooses = {"全部", "流行", "摇滚", "民谣", "独立", "古典", "电子", "原声", "爵士", "R&B", "说唱", "轻音乐"};

    @Inject
    NewMusicPresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getMusic(int start, int count) {

        Observable<MusicRootHY> hy = helper.getMusicByTagHY("华语", start, count);
        Observable<MusicRootRH> rh = helper.getMusicByTagRH("日韩", start, count);
        Observable<MusicRootOM> om = helper.getMusicByTagOM("欧美", start, count);
        Observable<MusicRoot> byTag = helper.getMusicByTag("喜欢", start, count);

        Subscription subscribe = Observable.concat(hy, rh, om, byTag)
                .compose(RxUtil.rxSchedulerHelper())
                .subscribe(new CommonSubscribers<Object>(mView, false) {
                    @Override
                    public void onNext(Object o) {
                        List<MusicBaseData> list = new ArrayList<>();
                        if (o instanceof MusicRootHY) {
                            list.add(new MusicTypeData("华语"));
                            List<Musics> musics = ((MusicRootHY) o).getMusics();
                            List<MusicInfoData> infoDatas = new ArrayList<>();
                            for (int i = 0; i < musics.size(); i++) {
                                Musics musics1 = musics.get(i);
                                infoDatas.add(new MusicInfoData(musics1.getTitle(), musics1.getId(), musics1.getImage(), musics1.getRating().getAverage(), musics1.getAuthor().get(0).getName()));
                            }
                            list.add(new MusicContentData(infoDatas));
                        } else if (o instanceof MusicRootRH) {
                            list.add(new MusicTypeData("日韩"));
                            List<Musics> musics = ((MusicRootRH) o).getMusics();
                            List<MusicInfoData> infoDatas = new ArrayList<>();
                            for (int i = 0; i < musics.size(); i++) {
                                Musics musics1 = musics.get(i);
                                infoDatas.add(new MusicInfoData(musics1.getTitle(), musics1.getId(), musics1.getImage(), musics1.getRating().getAverage(), musics1.getAuthor().get(0).getName()));
                            }
                            list.add(new MusicContentData(infoDatas));

                        } else if (o instanceof MusicRootOM) {
                            list.add(new MusicTypeData("欧美"));

                            List<Musics> musics = ((MusicRootOM) o).getMusics();
                            List<MusicInfoData> infoDatas = new ArrayList<>();
                            for (int i = 0; i < musics.size(); i++) {
                                Musics musics1 = musics.get(i);
                                infoDatas.add(new MusicInfoData(musics1.getTitle(), musics1.getId(), musics1.getImage(), musics1.getRating().getAverage(), musics1.getAuthor().get(0).getName()));
                            }
                            list.add(new MusicContentData(infoDatas));

                            list.add(new MusicNoMoreData("选音乐"));

                            List<String> choose = new ArrayList<>();
                            Collections.addAll(choose, chooses);
                            list.add(new MusicChooseData(choose));

                        } else if (o instanceof MusicRoot) {

                            list.add(new MusicNoMoreData("猜你喜欢"));

                            List<Musics> musics = ((MusicRoot) o).getMusics();
                            for (int i = 0; i < musics.size(); i++) {
                                Musics musics1 = musics.get(i);
                                list.add(new MusicLikeData(musics1.getTitle(), musics1.getImage(), musics1.getId(), musics1.getRating().getAverage(), musics1.getAuthor().get(0).getName()));
                            }

                            list.add(new MusicNewFiveData("查看更多"));
                        }
                        mView.showMusic(list);
                    }
                });
        addSubscrebe(subscribe);

    }
}