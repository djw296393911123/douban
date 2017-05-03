package com.djw.douban.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;
import com.djw.douban.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by JasonDong
 * <p>
 * on 2017/5/2.
 */

public class RefreshHeaderTimeView extends RelativeLayout implements SwipeTrigger, SwipeRefreshTrigger {
    private final ProgressBar pb;
    private final ImageView jiantou;
    private TextView time;
    private TextView state;

    public RefreshHeaderTimeView(Context context) {
        this(context, null);
    }

    public RefreshHeaderTimeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshHeaderTimeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.refresh_header, this, false);
        AutoUtils.autoSize(view);
        addView(view);
        time = ((TextView) view.findViewById(R.id.tv_refresh_time));
        state = ((TextView) view.findViewById(R.id.tv_refresh_state));
        pb = (ProgressBar) view.findViewById(R.id.pb_header);
        jiantou = ((ImageView) view.findViewById(R.id.iv_jiantou));
    }


    @Override
    public void onPrepare() {
        jiantou.setVisibility(VISIBLE);
        jiantou.setSelected(true);
        pb.setVisibility(GONE);
        state.setText("下拉刷新");
        String format = new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis()));
        if (SPUtils.getBoolean(SPUtils.IS_FIRST, true)) {
            time.setText("从未刷新");
            SPUtils.putBoolean(SPUtils.IS_FIRST, false);
            SPUtils.putString(SPUtils.REFRESH_TIME, format);
        } else {
            time.setText(SPUtils.getString(SPUtils.REFRESH_TIME, format));
            SPUtils.putString(SPUtils.REFRESH_TIME, format);
        }
    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
    }

    @Override
    public void onRelease() {
        jiantou.setVisibility(GONE);
        pb.setVisibility(VISIBLE);
    }

    @Override
    public void onComplete() {
        state.setText("刷新完成");
    }

    @Override
    public void onReset() {
        jiantou.setSelected(true);
    }

    @Override
    public void onRefresh() {
        state.setText("正在刷新...");
    }
}
