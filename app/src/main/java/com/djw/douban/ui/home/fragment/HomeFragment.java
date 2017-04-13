package com.djw.douban.ui.home.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.djw.douban.R;
import com.djw.douban.adapter.MainViewpagerAdapter;
import com.djw.douban.base.SimpleFragment;
import com.djw.douban.ui.home.book.fragment.BookFragment;
import com.djw.douban.ui.home.movies.fragment.MoviesFragment;
import com.djw.douban.ui.home.music.fragment.MusicFragment;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends SimpleFragment {


    private SlidingTabLayout tabLayout;
    private ViewPager pager;
    private Toolbar toolbar;

    @Override
    protected void initView(View view) {
        tabLayout = ((SlidingTabLayout) view.findViewById(R.id.stl_home));
        pager = ((ViewPager) view.findViewById(R.id.vp_home));
        toolbar = ((Toolbar) view.findViewById(R.id.tl_base));
    }

    @Override
    protected void doBusiness() {
        toolbar.setTitle("");
        ((TextView) toolbar.findViewById(R.id.tv_toolbar_title)).setText(getString(R.string.home));
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new MoviesFragment());
        fragments.add(new BookFragment());
        fragments.add(new MusicFragment());
        pager.setAdapter(new MainViewpagerAdapter(getChildFragmentManager(), fragments));
        tabLayout.setViewPager(pager);
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_home;
    }

}
