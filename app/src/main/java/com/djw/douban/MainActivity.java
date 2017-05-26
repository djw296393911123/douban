package com.djw.douban;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.aspsine.fragmentnavigator.FragmentNavigator;
import com.djw.douban.adapter.MainNavigatorAdapter;
import com.djw.douban.base.SimpleActivity;
import com.djw.douban.ui.book.activity.BookFromTagActivity;
import com.djw.douban.ui.book.fragment.BookFragment;
import com.djw.douban.ui.cloud.fragment.CloudFragment;
import com.djw.douban.ui.girl.activity.GirlActivity;
import com.djw.douban.ui.movies.activity.HotActivity;
import com.djw.douban.ui.movies.fragment.NewMoviesFragment;
import com.djw.douban.ui.music.activity.MoreMusicActivity;
import com.djw.douban.ui.music.fragment.NewMusicFragment;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.ArrayList;

public class MainActivity extends SimpleActivity implements BottomNavigationBar.OnTabSelectedListener {

    private long exitTime;
    private FragmentNavigator navigator;
    private static final int DEFAULT_POSITION = 0;
    private ArrayList<Fragment> fragments;
    private Drawer drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBackEnable(false);
        setContentView(R.layout.activity_main);
        initNavigator(savedInstanceState);
    }

    private void initNavigator(Bundle savedInstanceState) {
        fragments = new ArrayList<>();
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
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.mipmap.wd_beijing)
                .addProfiles(
                        new ProfileDrawerItem().withName("JasonDong").withEmail("296393911@qq.com").withIcon(getResources().getDrawable(R.mipmap.boy))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();
        SecondaryDrawerItem douban = new SecondaryDrawerItem().withName("豆瓣").withSelectedTextColor(Color.BLACK).withEnabled(false);
        SecondaryDrawerItem fuli = new SecondaryDrawerItem().withName("福利").withSelectedTextColor(Color.BLACK).withEnabled(false);
        SecondaryDrawerItem movies = new SecondaryDrawerItem().withIdentifier(1).withName("热门电影").withSelectedTextColor(Color.RED).withIcon(R.mipmap.movies);
        SecondaryDrawerItem book = new SecondaryDrawerItem().withIdentifier(2).withName("精选图书").withSelectedTextColor(Color.RED).withIcon(R.mipmap.books);
        SecondaryDrawerItem music = new SecondaryDrawerItem().withIdentifier(3).withName("中国风音乐").withSelectedTextColor(Color.RED).withIcon(R.mipmap.music);
        SecondaryDrawerItem girl = new SecondaryDrawerItem().withIdentifier(4).withName("美女").withSelectedTextColor(Color.RED).withIcon(R.mipmap.girl);
        drawer = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(headerResult)
                .addDrawerItems(douban, movies, book, music, new DividerDrawerItem(), fuli, girl)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        switch (((int) drawerItem.getIdentifier())) {
                            case 1:
                                startActivity(HotActivity.class);
                                break;
                            case 2:
                                Bundle bundle = new Bundle();
                                bundle.putString("tag", "精选");
                                startActivity(BookFromTagActivity.class, bundle);
                                break;
                            case 3:
                                Bundle music = new Bundle();
                                music.putString("tag", "中国风");
                                startActivity(MoreMusicActivity.class, music);
                                break;
                            case 4:
                                startActivity(GirlActivity.class);
                                break;
                        }
                        return true;
                    }
                })
                .build();
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
        drawer.closeDrawer();
        setCurrentTab(position);

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {
        switch (position) {
            case 0:
                ((NewMoviesFragment) fragments.get(position)).scrollToTop();
                break;
            case 1:
                ((BookFragment) fragments.get(position)).scrollToTop();
                break;
            case 2:
                ((NewMusicFragment) fragments.get(position)).scrollToTop();
                break;
            case 3:
                ((CloudFragment) fragments.get(position)).scrollToTop();
                break;
        }
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
