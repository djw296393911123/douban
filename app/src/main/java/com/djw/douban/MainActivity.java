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
import com.djw.douban.ui.home.fragment.HomeFragment;
import com.djw.douban.ui.mine.fragment.MineFragment;

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
        fragments.add(new HomeFragment());
        fragments.add(new CloudFragment());
        fragments.add(new MineFragment());
        navigator = new FragmentNavigator(getSupportFragmentManager(), new MainNavigatorAdapter(fragments), R.id.fl_main);
        navigator.setDefaultPosition(DEFAULT_POSITION);
        navigator.onCreate(savedInstanceState);
        BottomNavigationBar bar = (BottomNavigationBar) findViewById(R.id.bnb_main);
        bar.setMode(BottomNavigationBar.MODE_FIXED);
        bar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bar
                .addItem(new BottomNavigationItem(R.drawable.ic_home_black_24dp, getString(R.string.home)))
                .addItem(new BottomNavigationItem(R.drawable.ic_wb_cloudy_black_24dp, getString(R.string.guangbo)))
                .addItem(new BottomNavigationItem(R.drawable.ic_assignment_ind_black_24dp, getString(R.string.mine)))
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
