package com.djw.douban.module;

import android.app.Activity;
import android.support.v4.app.Fragment;


import com.djw.douban.http.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by JasonDong
 * <p>
 * on 2017/3/23.
 */
@Module
public class FragmentModule {

    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity provideActivity() {
        return fragment.getActivity();
    }
}