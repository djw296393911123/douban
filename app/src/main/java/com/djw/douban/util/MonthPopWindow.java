package com.djw.douban.util;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import com.djw.douban.R;
import com.djw.douban.data.cloud.CityData;
import com.djw.douban.ui.cloud.adapter.CityAdapter;
import com.djw.douban.ui.message.adapter.MonthAdapter;

import java.util.ArrayList;
import java.util.List;

import razerdp.basepopup.BasePopupWindow;

/**
 * Created by JasonDong
 * <p>
 * on 2017/5/10.
 */

public abstract class MonthPopWindow extends BasePopupWindow {

    protected MonthPopWindow(Activity context) {
        super(context);
        setBackPressEnable(false);
        setDismissWhenTouchOuside(true);
        List<String> list = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            list.add(String.valueOf(i));
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_city);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new MonthAdapter(list) {
            @Override
            public void onItemClick(String month) {
                onItemClicks(Integer.parseInt(month));
            }
        });
    }

    @Override
    protected Animation initShowAnimation() {
        TranslateAnimation translateAnimation = new TranslateAnimation(0f, 0f, -DimensUtils.dipToPx(getContext(), 350f), 0);
        translateAnimation.setDuration(450);
//        translateAnimation.setInterpolator(new OvershootInterpolator(1));
        return translateAnimation;
    }

    @Override
    public View getClickToDismissView() {
        return getPopupWindowView();
    }

    @Override
    public View onCreatePopupView() {
        return createPopupById(R.layout.item_city);
    }

    @Override
    protected Animation initExitAnimation() {
        TranslateAnimation translateAnimation = new TranslateAnimation(0f, 0f, 0, -DimensUtils.dipToPx(getContext(), 350f));
        translateAnimation.setDuration(450);
//        translateAnimation.setInterpolator(new OvershootInterpolator(-4));
        return translateAnimation;
    }

    @Override
    public View initAnimaView() {
        return findViewById(R.id.rv_city);
    }

    public abstract void onItemClicks(int month);

}
