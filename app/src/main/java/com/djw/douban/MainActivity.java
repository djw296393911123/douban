package com.djw.douban;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.aspsine.fragmentnavigator.FragmentNavigator;
import com.djw.douban.adapter.MainNavigatorAdapter;
import com.djw.douban.base.SimpleActivity;
import com.djw.douban.ui.cloud.fragment.CloudFragment;
import com.djw.douban.ui.book.fragment.BookFragment;
import com.djw.douban.ui.movies.fragment.NewMoviesFragment;
import com.djw.douban.ui.music.fragment.NewMusicFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends SimpleActivity implements BottomNavigationBar.OnTabSelectedListener {

    private long exitTime;
    private FragmentNavigator navigator;
    private static final int DEFAULT_POSITION = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBackEnable(false);
        setContentView(R.layout.activity_main);
        initNavigator(savedInstanceState);
    }

    private void initNavigator(Bundle savedInstanceState) {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new NewMoviesFragment());
        fragments.add(new BookFragment());
        fragments.add(new NewMusicFragment());
        fragments.add(new CloudFragment());
        navigator = new FragmentNavigator(getSupportFragmentManager(), new MainNavigatorAdapter(fragments), R.id.fl_main);
        navigator.setDefaultPosition(DEFAULT_POSITION);
        navigator.onCreate(savedInstanceState);
        BottomNavigationBar bar = (BottomNavigationBar) findViewById(R.id.bnb_main);
        bar.setMode(BottomNavigationBar.MODE_FIXED);
        bar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bar
                .addItem(new BottomNavigationItem(R.mipmap.movies, getString(R.string.movies)))
                .addItem(new BottomNavigationItem(R.mipmap.books, getString(R.string.books)))
                .addItem(new BottomNavigationItem(R.mipmap.music, getString(R.string.music)))
                .addItem(new BottomNavigationItem(R.mipmap.activity, getString(R.string.guangbo)))
                .initialise();
        bar.setTabSelectedListener(this);
        setCurrentTab(navigator.getCurrentPosition());
    }

    @Override
    public void initView() {

    }

    private void setCurrentTab(int position) {
        navigator.showFragment(position);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        navigator.onSaveInstanceState(outState);
    }

    @Override
    public void doBusiness() {


    }

    @Override
    public void onTabSelected(int position) {
        setCurrentTab(position);
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if (System.currentTimeMillis() - exitTime > 2000) {
                Toast.makeText(this, "再次点击退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
