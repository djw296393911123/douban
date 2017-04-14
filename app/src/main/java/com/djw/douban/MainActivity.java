package com.djw.douban;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.djw.douban.adapter.MainViewpagerAdapter;
import com.djw.douban.base.SimpleActivity;
import com.djw.douban.ui.cloud.fragment.CloudFragment;
import com.djw.douban.ui.home.fragment.HomeFragment;
import com.djw.douban.ui.mine.fragment.MineFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends SimpleActivity implements BottomNavigationBar.OnTabSelectedListener, ViewPager.OnPageChangeListener {

    private ViewPager pager;
    private BottomNavigationBar bar;
    private long exitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBackEnable(false);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initView() {
        bar = (BottomNavigationBar) findViewById(R.id.bnb_main);
        pager = (ViewPager) findViewById(R.id.vp_main);
    }

    @Override
    public void doBusiness() {
        bar.setMode(BottomNavigationBar.MODE_FIXED);
        bar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bar
                .addItem(new BottomNavigationItem(R.drawable.ic_home_black_24dp, getString(R.string.home)))
                .addItem(new BottomNavigationItem(R.drawable.ic_wb_cloudy_black_24dp, getString(R.string.guangbo)))
                .addItem(new BottomNavigationItem(R.drawable.ic_assignment_ind_black_24dp, getString(R.string.mine)))
                .initialise();
        bar.setTabSelectedListener(this);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new CloudFragment());
        fragments.add(new MineFragment());
        pager.setAdapter(new MainViewpagerAdapter(getSupportFragmentManager(), fragments));
        pager.setOffscreenPageLimit(fragments.size());
        pager.addOnPageChangeListener(this);
    }

    @Override
    public void onTabSelected(int position) {
        pager.setCurrentItem(position);
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        bar.selectTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

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
