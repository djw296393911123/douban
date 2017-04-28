package com.djw.douban.util;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;

/**
 * Created by congl on 2017/4/12.
 */

public class ActivityUtils {

    public static void addFragmentToActitity(FragmentManager fragmentManager, int fragmentId, Fragment fragment) {
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments != null) {

            if (fragments.size() >= 0) {
                for (Fragment fragment1 : fragments) {
                    fragmentManager.beginTransaction().hide(fragment1).commit();
                }
            }
        }
        Fragment fragmentByTag = fragmentManager.findFragmentByTag(fragment.getClass().getSimpleName());
        if (fragmentByTag == null) {
            fragmentByTag = fragment;
            fragmentManager.beginTransaction().add(fragmentId, fragmentByTag, fragmentByTag.getClass().getSimpleName()).commit();
        } else {
            fragmentManager.beginTransaction().show(fragmentByTag).commit();
        }
    }

    public static void addFragmentToActivityToBackStack(FragmentManager fragmentManager, int fragmentId, Fragment fragment) {
        Fragment fragmentByTag = fragmentManager.findFragmentByTag(fragment.getClass().getSimpleName());
        if (fragmentByTag != null) {

        }
        fragmentManager.beginTransaction()
                .add(fragmentId, fragment, fragment.getClass().getSimpleName())
                .addToBackStack(null)
                .commit();
    }

    public static boolean isFragmentAdded(FragmentManager fragmentManager, Fragment fragment) {
        Fragment fragmentByTag = fragmentManager.findFragmentByTag(fragment.getClass().getSimpleName());
        return !(fragmentByTag == null);
    }
}
