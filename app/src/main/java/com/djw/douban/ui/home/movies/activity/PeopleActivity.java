package com.djw.douban.ui.home.movies.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.djw.douban.R;
import com.djw.douban.base.RxActivity;
import com.djw.douban.data.movies.PeopleBaseData;
import com.djw.douban.data.movies.PeopleOne;
import com.djw.douban.ui.home.movies.adapter.PeopleAdapter;
import com.djw.douban.ui.home.movies.contract.MoviesPeopleContract;
import com.djw.douban.ui.home.movies.presenter.PeoplePresenter;

import java.util.List;

import butterknife.BindView;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class PeopleActivity extends RxActivity<PeoplePresenter> implements MoviesPeopleContract.View {

    @BindView(R.id.rv_people)
    RecyclerView rvPeople;
    @BindView(R.id.iv_small)
    ImageView ivSmall;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_works)
    TextView tvWorks;

    private PeopleAdapter adapter;

    // 控制ToolBar的变量
    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR = 0.9f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS = 0.3f;

    private static final int ALPHA_ANIMATIONS_DURATION = 200;

    private boolean mIsTheTitleVisible = false;
    private boolean mIsTheTitleContainerVisible = true;

    @BindView(R.id.main_iv_placeholder)
    ImageView mIvPlaceholder; // 大图片

    @BindView(R.id.main_ll_title_container)
    LinearLayout mLlTitleContainer; // Title的LinearLayout

    @BindView(R.id.main_fl_title)
    FrameLayout mFlTitleContainer; // Title的FrameLayout

    @BindView(R.id.main_abl_app_bar)
    AppBarLayout mAblAppBar; // 整个可以滑动的AppBar

    @BindView(R.id.main_tv_toolbar_title)
    TextView mTvToolbarTitle; // 标题栏Title

    @BindView(R.id.main_tb_toolbar)
    Toolbar mTbToolbar; // 工具栏

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.people_new_layout);
    }

    @Override
    public void initView() {

        rvPeople.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PeopleAdapter(this);
        rvPeople.setAdapter(adapter);
    }

    @Override
    public void doBusiness() {
        mTbToolbar.setTitle("");

        // AppBar的监听
        mAblAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int maxScroll = appBarLayout.getTotalScrollRange();
                float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;
                handleAlphaOnTitle(percentage);
                handleToolbarTitleVisibility(percentage);
            }
        });

        initParallaxValues(); // 自动滑动效果
    }

    // 设置自动滑动的动画效果
    private void initParallaxValues() {
        CollapsingToolbarLayout.LayoutParams petDetailsLp =
                (CollapsingToolbarLayout.LayoutParams) mIvPlaceholder.getLayoutParams();

        CollapsingToolbarLayout.LayoutParams petBackgroundLp =
                (CollapsingToolbarLayout.LayoutParams) mFlTitleContainer.getLayoutParams();

        petDetailsLp.setParallaxMultiplier(0.9f);
        petBackgroundLp.setParallaxMultiplier(0.3f);

        mIvPlaceholder.setLayoutParams(petDetailsLp);
        mFlTitleContainer.setLayoutParams(petBackgroundLp);
    }

    // 处理ToolBar的显示
    private void handleToolbarTitleVisibility(float percentage) {
        if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {
            if (!mIsTheTitleVisible) {
                startAlphaAnimation(mTvToolbarTitle, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleVisible = true;
            }
        } else {
            if (mIsTheTitleVisible) {
                startAlphaAnimation(mTvToolbarTitle, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleVisible = false;
            }
        }
    }

    // 控制Title的显示
    private void handleAlphaOnTitle(float percentage) {
        if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {
            if (mIsTheTitleContainerVisible) {
                startAlphaAnimation(mLlTitleContainer, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleContainerVisible = false;
            }
        } else {
            if (!mIsTheTitleContainerVisible) {
                startAlphaAnimation(mLlTitleContainer, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleContainerVisible = true;
            }
        }
    }

    // 设置渐变的动画
    public static void startAlphaAnimation(View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }

    @Override
    public void inject() {
        getActivityComponent().inject(this);
        mPresenter.attachView(this);
        mPresenter.getPeople(getIntent().getExtras().getInt("id"));
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showPeople(PeopleOne peopleOne, List<PeopleBaseData> list, String name) {
        mTvToolbarTitle.setText(name);
        tvName.setText(name);
        tvWorks.setText(peopleOne.getJianjie());
        Glide.with(this).load(peopleOne.getUrl()).bitmapTransform(new CropCircleTransformation(context)).into(ivSmall);
        Glide.with(this).load(peopleOne.getUrl()).asBitmap().into(mIvPlaceholder);
        adapter.notifyDataChange(list);
    }
}
