package com.djw.douban.ui.home.book.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by JasonDong on 2017/4/10.
 */

public class BookViewpagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    private String[] title = {"综合", "文学", "流行", "文化", "生活"};

    public BookViewpagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
