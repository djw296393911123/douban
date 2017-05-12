package com.djw.douban.util;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.djw.douban.R;

import razerdp.basepopup.BasePopupWindow;

/**
 * Created by JasonDong
 * <p>
 * on 2017/5/10.
 */

public class MessageMorePopWindow extends BasePopupWindow {

    public MessageMorePopWindow(Activity context) {
        this(context, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    private MessageMorePopWindow(Activity context, int w, int h) {
        super(context, w, h);
        setBackPressEnable(false);
        setDismissWhenTouchOuside(true);
    }

    @Override
    protected Animation initShowAnimation() {
        return getScaleAnimation(0.0f, 1.0f, 1.0f, 1.0f, Animation.RELATIVE_TO_SELF, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f);
    }

    @Override
    public View getClickToDismissView() {
        return getPopupWindowView();
    }

    @Override
    public View onCreatePopupView() {
        return createPopupById(R.layout.item_message_more);
    }

    @Override
    protected Animation initExitAnimation() {
        return getScaleAnimation(1.0f, 0.0f, 1.0f, 1.0f, Animation.RELATIVE_TO_SELF, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f);
    }

    @Override
    public View initAnimaView() {
        return findViewById(R.id.ll_more);
    }


}
