package com.djw.douban.util;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;
import com.bumptech.glide.Glide;
import com.djw.douban.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by JasonDong on 2017/4/28.
 */

public class CustomRefreshHeader extends RelativeLayout implements SwipeTrigger, SwipeRefreshTrigger {

    public CustomRefreshHeader(Context context) {
        this(context, null);
    }

    public CustomRefreshHeader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomRefreshHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.refresh_header_two, null);
        AutoUtils.autoSize(view);
        addView(view);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_refresh);
        Glide.with(context).load(R.mipmap.refresh_header_kid).asGif().into(imageView);
    }


    @Override
    public void onPrepare() {
    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
    }

    @Override
    public void onRelease() {
    }

    @Override
    public void onComplete() {
    }

    @Override
    public void onReset() {
    }

    @Override
    public void onRefresh() {
    }
}
