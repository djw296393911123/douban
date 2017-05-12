package com.djw.douban.ui.message.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.djw.douban.ui.message.fragment.CalendarFragment;

import java.util.List;

/**
 * Created by JasonDong on 2017/5/11.
 */

public class CalendarViewPagerAdapter extends FragmentPagerAdapter {


    public CalendarViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return CalendarFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return 12;
    }
}
