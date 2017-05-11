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

import java.util.List;

import razerdp.basepopup.BasePopupWindow;

/**
 * Created by JasonDong
 * <p>
 * on 2017/5/10.
 */

public abstract class CloudDownPopWindow extends BasePopupWindow {

    protected CloudDownPopWindow(Activity context, List<CityData> list) {
        super(context);
        setBackPressEnable(false);
        setDismissWhenTouchOuside(true);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_city);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new CityAdapter(list) {
            @Override
            public void onItemClick(String id, String title) {
                onItemClicks(id, title);
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

    public abstract void onItemClicks(String id, String title);

}
