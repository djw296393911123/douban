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
//    private TextView state;
//    private TextView time;
//    private ProgressBar pb;
//    private ImageView jiantou;

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
//        state = (TextView) view.findViewById(R.id.tv_refresh_state);
//        time = (TextView) view.findViewById(R.id.tv_refresh_time);
//        pb = (ProgressBar) view.findViewById(R.id.pb_header);
//        jiantou = ((ImageView) view.findViewById(R.id.iv_jiantou));
    }


    @Override
    public void onPrepare() {
        Log.i("header", "onPrepare");
//        jiantou.setVisibility(VISIBLE);
//        jiantou.setSelected(true);
//        pb.setVisibility(GONE);
//        state.setText("下拉刷新");
//        time.setText(new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis())));
    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
//        Log.i("move", y + ":" + isComplete + ":" + automatic);
//        if (y > 100)
//            jiantou.setSelected(false);
    }

    @Override
    public void onRelease() {
//        jiantou.setVisibility(GONE);
//        pb.setVisibility(VISIBLE);
        Log.i("header", "onRelease");
    }

    @Override
    public void onComplete() {
        Log.i("header", "onComplete");
//        state.setText("刷新完成");
    }

    @Override
    public void onReset() {
//        jiantou.setSelected(true);
        Log.i("header", "onReset");
    }

    @Override
    public void onRefresh() {
        Log.i("header", "onRefresh");

//        state.setText("正在刷新...");
    }
}
