package com.djw.douban.ui.message;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.djw.douban.R;
import com.djw.douban.base.SimpleActivity;
import com.djw.douban.ui.message.adapter.CalendarViewPagerAdapter;
import com.djw.douban.util.CalendarUtil;

import butterknife.BindView;

public class NewCalendarActivity extends SimpleActivity {

    @BindView(R.id.vp_calendar)
    ViewPager vpCalendar;
    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.tl_base)
    Toolbar tlBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_calendar);

    }

    @Override
    public void initView() {
        setSupportActionBar(tlBase);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        tlBase.setNavigationIcon(R.mipmap.back);
        tlBase.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvToolbarTitle.setText("历史记录");
        vpCalendar.setAdapter(new CalendarViewPagerAdapter(getSupportFragmentManager()));
        vpCalendar.setCurrentItem(CalendarUtil.getMonth() - 1);
    }

    @Override
    public void doBusiness() {

    }
}
