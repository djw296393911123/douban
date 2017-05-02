package com.djw.douban.adapter;

import android.support.v4.app.Fragment;

import com.aspsine.fragmentnavigator.FragmentNavigatorAdapter;

import java.util.List;

/**
 * Created by JasonDong
 * <p>
 * on 2017/5/2.
 */

public class MainNavigatorAdapter implements FragmentNavigatorAdapter {

    private List<Fragment> fragments;

    public MainNavigatorAdapter(List<Fragment> fragments) {
        this.fragments = fragments;
    }

    @Override
    public Fragment onCreateFragment(int i) {
        return fragments.get(i);
    }

    @Override
    public String getTag(int i) {
        return fragments.get(i).getClass().getSimpleName();
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
