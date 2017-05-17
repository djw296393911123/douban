package com.djw.douban.ui.movies.presenter;

import com.djw.douban.base.CommonSubscribers;
import com.djw.douban.base.RxPresenter;
import com.djw.douban.data.movies.TypeData;
import com.djw.douban.http.RetrofitHelper;
import com.djw.douban.ui.movies.contract.TypeContract;
import com.djw.douban.util.RxUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by JasonDong
 * <p>
 * on 2017/4/17.
 */

public class TypePresenter extends RxPresenter<TypeContract.View> implements TypeContract.Presenter {

    private final RetrofitHelper helper;

    @Inject
    TypePresenter(RetrofitHelper helper) {
        this.helper = helper;
    }

    @Override
    public void getType(String q, int start, int count, final boolean isLoadMore, boolean isShowProgress) {
        Subscription subscribe = helper.getType(q, start, count)
                .compose(RxUtil.<TypeData>rxSchedulerHelper())
                .subscribe(new CommonSubscribers<TypeData>(mView, isShowProgress) {
                    @Override
                    public void onNext(TypeData typeData) {
                        mView.showType(typeData.getSubjects(), isLoadMore);
                    }
                });
        addSubscrebe(subscribe);
    }

    @Override
    public void getRule(int type, List<TypeData.SubjectsBean> list) {
        List<TypeData.SubjectsBean> lists = new ArrayList<>();
        lists.addAll(list);
        switch (type) {
            case 0:
                Collections.sort(lists, new Comparator<TypeData.SubjectsBean>() {
                    @Override
                    public int compare(TypeData.SubjectsBean o1, TypeData.SubjectsBean o2) {
                        double average1 = o1.getRating().getAverage();
                        double average2 = o2.getRating().getAverage();
                        if (average1 < average2) {
                            return -1;
                        } else if (average1 > average2) {
                            return 1;
                        } else {
                            return 0;
                        }
                    }
                });
                break;
            case 1:
                Collections.sort(lists, new Comparator<TypeData.SubjectsBean>() {
                    @Override
                    public int compare(TypeData.SubjectsBean o1, TypeData.SubjectsBean o2) {
                        double average1 = o1.getRating().getAverage();
                        double average2 = o2.getRating().getAverage();
                        if (average1 > average2) {
                            return -1;
                        } else if (average1 < average2) {
                            return 1;
                        } else {
                            return 0;
                        }
                    }
                });
                break;
            case 2:
                Collections.sort(lists, new Comparator<TypeData.SubjectsBean>() {
                    @Override
                    public int compare(TypeData.SubjectsBean o1, TypeData.SubjectsBean o2) {
                        int collect_count1 = o1.getCollect_count();
                        int collect_count2 = o2.getCollect_count();
                        if (collect_count1 > collect_count2) {
                            return -1;
                        } else if (collect_count1 < collect_count2) {
                            return 1;
                        } else {
                            return 0;
                        }
                    }
                });
                break;
            case 3:
                Collections.sort(lists, new Comparator<TypeData.SubjectsBean>() {
                    @Override
                    public int compare(TypeData.SubjectsBean o1, TypeData.SubjectsBean o2) {
                        int year1 = Integer.parseInt(o1.getYear());
                        int year2 = Integer.parseInt(o2.getYear());
                        if (year1 > year2) {
                            return -1;
                        } else if (year1 < year2) {
                            return 1;
                        } else {
                            return 0;
                        }
                    }
                });
                break;
        }
        mView.showType(lists, false);
    }
}
