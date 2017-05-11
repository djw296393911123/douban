package com.djw.douban.util;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.djw.douban.R;

import razerdp.basepopup.BasePopupWindow;

/**
 * Created by JasonDong
 * <p>
 * on 2017/5/10.
 */

public class CommingLikePopWindow extends BasePopupWindow implements View.OnClickListener {

    private ImageView like;
    private ImageView hate;
    private AnimationSet mAnimationSet;

    public CommingLikePopWindow(Activity context) {
        this(context, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    private CommingLikePopWindow(Activity context, int w, int h) {
        super(context, w, h);
        findViewById(R.id.ll_pop_like).setOnClickListener(this);
        findViewById(R.id.ll_pop_hate).setOnClickListener(this);
        like = (ImageView) findViewById(R.id.iv_like);
        hate = (ImageView) findViewById(R.id.iv_hate);
        buildAnima();
    }

    @Override
    protected Animation initShowAnimation() {
        return getScaleAnimation(0.0f, 1.0f, 1.0f, 1.0f, Animation.RELATIVE_TO_SELF, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f);
    }

    @Override
    public View getClickToDismissView() {
        return null;
    }

    @Override
    protected Animation initExitAnimation() {
        return getScaleAnimation(1.0f, 0.0f, 1.0f, 1.0f, Animation.RELATIVE_TO_SELF, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f);
    }

    @Override
    public void showPopupWindow(View v) {
        setOffsetX(-getPopupViewWidth() - v.getWidth() / 2);
        setOffsetY(-v.getHeight() + v.getHeight() / 2);
        super.showPopupWindow(v);
    }

    @Override
    public View onCreatePopupView() {
        return createPopupById(R.layout.item_like_pop);
    }

    @Override
    public View initAnimaView() {
        return findViewById(R.id.ll_like_pop);
    }

    private void buildAnima() {
        ScaleAnimation mScaleAnimation = new ScaleAnimation(1f, 2f, 1f, 2f, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        mScaleAnimation.setDuration(200);
        mScaleAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        mScaleAnimation.setFillAfter(false);

        AlphaAnimation mAlphaAnimation = new AlphaAnimation(1, .2f);
        mAlphaAnimation.setDuration(400);
        mAlphaAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        mAlphaAnimation.setFillAfter(false);

        mAnimationSet = new AnimationSet(false);
        mAnimationSet.setDuration(400);
        mAnimationSet.addAnimation(mScaleAnimation);
        mAnimationSet.addAnimation(mAlphaAnimation);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_pop_like:
                like.clearAnimation();
                like.startAnimation(mAnimationSet);
                break;
            case R.id.ll_pop_hate:
                hate.clearAnimation();
                hate.startAnimation(mAnimationSet);
                break;
        }
    }
}
