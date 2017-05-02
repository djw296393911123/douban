package com.djw.douban.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;
import com.djw.douban.R;
import com.zhy.autolayout.utils.AutoUtils;

/**
 * Created by JasonDong on 2017/5/2.
 */

public class LoadMoreFooterView extends RelativeLayout implements SwipeTrigger, SwipeRefreshTrigger {
    private final TextView state;

    public LoadMoreFooterView(Context context) {
        this(context, null);
    }

    public LoadMoreFooterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadMoreFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.loadmore_footer, this, false);
        AutoUtils.autoSize(view);
        addView(view);
        state = (TextView) view.findViewById(R.id.tv_refresh_state);
    }


    @Override
    public void onPrepare() {
        state.setText("上拉加载");
    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {

    }

    @Override
    public void onRelease() {
        state.setText("正在加载");
    }

    @Override
    public void onComplete() {
        state.setText("加载完成");
    }

    @Override
    public void onReset() {

    }

    @Override
    public void onRefresh() {
        state.setText("正在加载");
    }
}
