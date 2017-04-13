package com.djw.douban.ui.home.movies.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by JasonDong on 2017/4/7.
 */

public class MoviewsPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;
    private String[] titles = {"正在热映", "即将上映", "TOP250", "北美票房排行榜"};

    public MoviewsPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
