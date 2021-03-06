package com.djw.douban.ui.search.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.djw.douban.MainActivity;
import com.djw.douban.R;
import com.djw.douban.adapter.MainViewpagerAdapter;
import com.djw.douban.base.SimpleFragment;
import com.djw.douban.ui.search.activity.SearchActivity;
import com.djw.douban.ui.book.fragment.BookFragment;
import com.djw.douban.ui.movies.fragment.NewMoviesFragment;
import com.djw.douban.ui.music.fragment.NewMusicFragment;
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
        setHasOptionsMenu(true);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayShowTitleEnabled(false);
        ((TextView) toolbar.findViewById(R.id.tv_toolbar_title)).setText(getString(R.string.home));
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new NewMoviesFragment());
        fragments.add(new BookFragment());
        fragments.add(new NewMusicFragment());
        pager.setAdapter(new MainViewpagerAdapter(getChildFragmentManager(), fragments));
        tabLayout.setViewPager(pager);
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        startActivity(SearchActivity.class);
        return super.onOptionsItemSelected(item);
    }
}
