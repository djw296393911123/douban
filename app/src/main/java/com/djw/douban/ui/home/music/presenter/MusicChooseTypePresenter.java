package com.djw.douban.ui.home.music.presenter;

import android.util.Log;

import com.djw.douban.base.CommonSubscribers;
import com.djw.douban.base.RxPresenter;
import com.djw.douban.data.music.MusicRoot;
import com.djw.douban.data.music.Musics;
import com.djw.douban.data.newmusic.MusicBaseData;
import com.djw.douban.data.newmusic.MusicListData;
import com.djw.douban.data.newmusic.MusicStyleInfoData;
import com.djw.douban.data.newmusic.TopOneData;
import com.djw.douban.data.newmusic.TopThreeData;
import com.djw.douban.data.newmusic.TopTwoData;
import com.djw.douban.http.RetrofitHelper;
import com.djw.douban.ui.home.music.contract.ChooseTypeContract;
import com.djw.douban.util.RxUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by JasonDong on 2017/4/18.
 */

public class MusicChooseTypePresenter extends RxPresenter<ChooseTypeContract.View> implements ChooseTypeContract.Presenter {

    private final RetrofitHelper helper;

    private String[] styles = {"全部", "流行", "摇滚", "民谣", "独立", "古典", "电子", "原声", "爵士", "R&B", "说唱", "轻音乐"};

    private String[] places = {"全部", "华语", "欧美", "日本", "韩国", "其他"};

    private String[] others = {"全部", "+自定义标签"};

    private String[] types1 = {"最受欢迎", "评分最高", "最新发行"};

    @Inject
    MusicChooseTypePresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getChooseType(String tag, final int type, int start, int count, final boolean isLoadMore, boolean isShowProgress) {
        Subscription subscribe = helper.getMusicByTag(tag, start, count)
                .compose(RxUtil.<MusicRoot>rxSchedulerHelper())
                .subscribe(new CommonSubscribers<MusicRoot>(mView, isShowProgress) {
                    @Override
                    public void onNext(MusicRoot musicRoot) {
                        List<MusicListData> list = new ArrayList<>();

                        List<Musics> musics = musicRoot.getMusics();
                        for (int i = 0; i < musics.size(); i++) {
                            Musics musics1 = musics.get(i);
                            list.add(new MusicListData(musics1.getImage(), musics1.getTitle(), musics1.getAuthor().get(0).getName(), musics1.getRating().getAverage(), musics1.getId(), musics1.getRating().getNumRaters(), musics1.getAttrs().getPubdate().get(0)));
                        }

                        switch (type) {
                            case 0:
                                Collections.sort(list, new Comparator<MusicListData>() {
                                    @Override
                                    public int compare(MusicListData o1, MusicListData o2) {
                                        if (o1.getStart() > o2.getStart()) {
                                            return -1;
                                        } else if (o1.getStart() < o2.getStart()) {
                                            return 1;
                                        } else {
                                            return 0;
                                        }
                                    }
                                });
                                break;
                            case 1:
                                Collections.sort(list, new Comparator<MusicListData>() {
                                    @Override
                                    public int compare(MusicListData o1, MusicListData o2) {
                                        if (Double.parseDouble(o1.getGrade()) > Double.parseDouble(o2.getGrade())) {
                                            return -1;
                                        } else if (Double.parseDouble(o1.getGrade()) < Double.parseDouble(o2.getGrade())) {
                                            return 1;
                                        } else {
                                            return 0;
                                        }
                                    }
                                });
                                break;
                            case 2:
                                Collections.sort(list, new Comparator<MusicListData>() {
                                    @Override
                                    public int compare(MusicListData o1, MusicListData o2) {
                                        if (Integer.parseInt(o1.getYear().substring(0, 4)) > Integer.parseInt(o2.getYear().substring(0, 4))) {
                                            return -1;
                                        } else if (Integer.parseInt(o1.getYear().substring(0, 4)) > Integer.parseInt(o2.getYear().substring(0, 4))) {
                                            return 1;
                                        } else {
                                            return 0;
                                        }
                                    }
                                });
                                break;
                        }

                        mView.showChooseType(list, isLoadMore);
                    }
                });
        addSubscrebe(subscribe);
    }

    @Override
    public void getTopType(int position) {
        List<MusicBaseData> list = new ArrayList<>();

        List<MusicStyleInfoData> styleInfoDatas = new ArrayList<>();
        for (int i = 0; i < styles.length; i++) {
            if (i == position)
                styleInfoDatas.add(new MusicStyleInfoData(styles[position], true));
            else
                styleInfoDatas.add(new MusicStyleInfoData(styles[i], false));
        }
        list.add(new TopOneData("风格:", styleInfoDatas, 1));

        List<MusicStyleInfoData> styleInfoDatas2 = new ArrayList<>();
        for (int i = 0; i < places.length; i++) {
            if (i == 0)
                styleInfoDatas2.add(new MusicStyleInfoData(places[0], true));
            else
                styleInfoDatas2.add(new MusicStyleInfoData(places[i], false));
        }
        list.add(new TopTwoData("地区:", styleInfoDatas2));

        List<MusicStyleInfoData> styleInfoDatas3 = new ArrayList<>();
        for (int i = 0; i < others.length; i++) {
            if (i == 0)
                styleInfoDatas3.add(new MusicStyleInfoData(others[0], true));
            else
                styleInfoDatas3.add(new MusicStyleInfoData(others[i], false));
        }
        list.add(new TopThreeData("其他:", styleInfoDatas3));

        mView.showTopType(list);

    }

    @Override
    public void getPopData() {
        List<MusicStyleInfoData> list = new ArrayList<>();

        for (int i = 0; i < types1.length; i++) {
            if (i == 0)
                list.add(new MusicStyleInfoData(types1[0], true));
            else
                list.add(new MusicStyleInfoData(types1[i], false));
        }


        mView.showPopData(list);
    }

    @Override
    public void refreshOtherType(String title) {
        List<MusicStyleInfoData> styleInfoDatas = new ArrayList<>();
        styleInfoDatas.add(new MusicStyleInfoData(title, false));
        mView.showRefreshOther(styleInfoDatas);
    }


}
