package com.djw.douban.ui.cloud.presenter;

import com.djw.douban.base.CommonSubscribers;
import com.djw.douban.base.RxPresenter;
import com.djw.douban.data.cloud.CityData;
import com.djw.douban.data.cloud.CloudItemData;
import com.djw.douban.data.cloud.CloudLocData;
import com.djw.douban.http.RetrofitHelper;
import com.djw.douban.ui.cloud.contract.CloudContract;
import com.djw.douban.util.RxUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by JasonDong on 2017/4/12.
 */

public class CloudPresenter extends RxPresenter<CloudContract.View> implements CloudContract.Presenter {

    private final RetrofitHelper helper;

    @Inject
    public CloudPresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getCity() {
        Subscription subscribe = helper.getCity()
                .compose(RxUtil.<CloudLocData>rxSchedulerHelper())
                .subscribe(new CommonSubscribers<CloudLocData>(mView, false) {
                    @Override
                    public void onNext(CloudLocData cloudLocData) {
                        List<CityData> list = new ArrayList<>();
                        List<CloudLocData.LocsBean> locs = cloudLocData.getLocs();
                        for (int i = 0; i < locs.size(); i++) {
                            list.add(new CityData(locs.get(i).getName(), locs.get(i).getId()));
                        }
                        mView.showCity(list);
                    }
                });
        addSubscrebe(subscribe);
    }

    /**
     * @param loc            城市id
     * @param type           分类类型
     * @param day_type       时间类型
     * @param start          从第几个获取数据
     * @param count          获取的数据数量
     * @param isLoadMore     是否加载更多
     * @param isShowProgress 是否显示progress
     */
    @Override
    public void getActivitys(String loc, String type, String day_type, int start, int count, final boolean isLoadMore, boolean isShowProgress) {
        Subscription subscribe = helper.getActivitys(loc, day_type, type, start, count)
                .compose(RxUtil.<CloudItemData>rxSchedulerHelper())
                .subscribe(new CommonSubscribers<CloudItemData>(mView, isShowProgress) {
                    @Override
                    public void onNext(CloudItemData cloudItemData) {
                        mView.showActivitys(cloudItemData.getEvents(), isLoadMore);
                    }
                });
        addSubscrebe(subscribe);
    }

    @Override
    public void getType() {
        List<CityData> list = new ArrayList<>();
        list.add(new CityData("所有", "all"));
        list.add(new CityData("音乐", "music"));
        list.add(new CityData("电影", "film"));
        list.add(new CityData("戏剧", "drama"));
        list.add(new CityData("公益", "commonweal"));
        list.add(new CityData("讲座", "salon"));
        list.add(new CityData("展览", "exhibition"));
        list.add(new CityData("聚会", "party"));
        list.add(new CityData("运动", "sports"));
        list.add(new CityData("旅行", "travel"));
        list.add(new CityData("其他", "others"));
        mView.showType(list);
    }

    @Override
    public void getTypeData() {
        List<CityData> list = new ArrayList<>();
        list.add(new CityData("今天", "today"));
        list.add(new CityData("明天", "tomorrow"));
        list.add(new CityData("一周之内", "week"));
        list.add(new CityData("周末", "weekend"));
        list.add(new CityData("近期", "future"));
        mView.showTypeDay(list);
    }
}
