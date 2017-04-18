package com.djw.douban.ui.home.music.presenter;

import android.util.Log;

import com.djw.douban.base.CommonSubscribers;
import com.djw.douban.base.RxPresenter;
import com.djw.douban.data.music.MusicRoot;
import com.djw.douban.data.music.Musics;
import com.djw.douban.data.newmusic.MusicBaseData;
import com.djw.douban.data.newmusic.MusicListData;
import com.djw.douban.data.newmusic.MusicPopData;
import com.djw.douban.data.newmusic.MusicStyleData;
import com.djw.douban.data.newmusic.MusicStyleInfoData;
import com.djw.douban.http.RetrofitHelper;
import com.djw.douban.ui.home.music.contract.ChooseTypeContract;
import com.djw.douban.util.RxUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by JasonDong on 2017/4/18.
 *
 */

public class MusicChooseTypePresenter extends RxPresenter<ChooseTypeContract.View> implements ChooseTypeContract.Presenter {

    private final RetrofitHelper helper;

    private String[] styles = {"全部", "流行", "摇滚", "民谣", "独立", "古典", "电子", "原声", "爵士", "R&B", "说唱", "轻音乐"};

    private String[] places = {"全部", "华语", "欧美", "日本", "韩国", "其他"};

    private String[] others = {"全部", "+自定义表情"};

    private String[] types1 = {"最受欢迎", "评分最高", "最新发行"};

    @Inject
    MusicChooseTypePresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getChooseType(String tag, int start, int count, final boolean isLoadMore, boolean isShowProgress, final int position) {
        Subscription subscribe = helper.getMusicByTag(tag, start, count)
                .compose(RxUtil.<MusicRoot>rxSchedulerHelper())
                .subscribe(new CommonSubscribers<MusicRoot>(mView, isShowProgress) {
                    @Override
                    public void onNext(MusicRoot musicRoot) {
                        List<MusicBaseData> list = new ArrayList<>();

                        if (isLoadMore){
                            List<Musics> musics = musicRoot.getMusics();
                            for (int i = 0; i < musics.size(); i++) {
                                Musics musics1 = musics.get(i);
                                list.add(new MusicListData(musics1.getImage(), musics1.getTitle(), musics1.getAuthor().get(0).getName(), musics1.getRating().getAverage(), musics1.getId()));
                            }
                        }else {
                            List<MusicStyleInfoData> styleInfoDatas = new ArrayList<>();
                            for (int i = 0; i < styles.length; i++) {
                                if (i == position)
                                    styleInfoDatas.add(new MusicStyleInfoData(styles[position], true));
                                else
                                    styleInfoDatas.add(new MusicStyleInfoData(styles[i], false));
                            }
                            list.add(new MusicStyleData("风格:", styleInfoDatas));

                            List<MusicStyleInfoData> styleInfoDatas2 = new ArrayList<>();
                            for (int i = 0; i < places.length; i++) {
                                if (i == 0)
                                    styleInfoDatas2.add(new MusicStyleInfoData(places[0], true));
                                else
                                    styleInfoDatas2.add(new MusicStyleInfoData(places[i], false));
                            }
                            list.add(new MusicStyleData("地区:", styleInfoDatas2));

                            List<MusicStyleInfoData> styleInfoDatas3 = new ArrayList<>();
                            for (int i = 0; i < others.length; i++) {
                                if (i == 0)
                                    styleInfoDatas3.add(new MusicStyleInfoData(others[0], true));
                                else
                                    styleInfoDatas3.add(new MusicStyleInfoData(others[i], false));
                            }
                            list.add(new MusicStyleData("其他:", styleInfoDatas3));

                            List<MusicStyleInfoData> styleInfoDatas4 = new ArrayList<>();
                            for (int i = 0; i < types1.length; i++) {
                                if (i == 0)
                                    styleInfoDatas4.add(new MusicStyleInfoData(types1[0], true));
                                else
                                    styleInfoDatas4.add(new MusicStyleInfoData(types1[i], false));
                            }
                            list.add(new MusicPopData(styleInfoDatas4));

                            List<Musics> musics = musicRoot.getMusics();
                            for (int i = 0; i < musics.size(); i++) {
                                Musics musics1 = musics.get(i);
                                list.add(new MusicListData(musics1.getImage(), musics1.getTitle(), musics1.getAuthor().get(0).getName(), musics1.getRating().getAverage(), musics1.getId()));
                            }
                        }

                        Log.i("list", list.toString());
                        mView.showChooseType(list, isLoadMore);
                    }
                });
        addSubscrebe(subscribe);
    }

    @Override
    public void getSortData(int position, List<MusicBaseData> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getType() == MusicBaseData.THREE) {
                MusicListData musicListData = (MusicListData) list.get(i);
                if (position == 0){
                }else if (position == 1){

                }else if (position == 2){

                }
            }
        }

    }


}
