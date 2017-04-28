package com.djw.douban.ui.home.movies.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.djw.douban.R;
import com.djw.douban.base.RxActivity;
import com.djw.douban.data.movies.MoviesInfoBaseData;
import com.djw.douban.data.newmovies.MovieInfoTopData;
import com.djw.douban.ui.home.movies.adapter.MoviesInfoAapter;
import com.djw.douban.ui.home.movies.contract.MovieInfoContract;
import com.djw.douban.ui.home.movies.presenter.MovieInfoPresenter;

import java.util.List;

import butterknife.BindView;

public class MovieInfoActivity extends RxActivity<MovieInfoPresenter> implements MovieInfoContract.View, AppBarLayout.OnOffsetChangedListener {

    @BindView(R.id.iv_movie_info)
    ImageView ivMovieInfo;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_info_grade)
    TextView tvInfoGrade;
    @BindView(R.id.tv_info_num)
    TextView tvInfoNum;
    @BindView(R.id.tv_year)
    TextView tvYear;
    @BindView(R.id.tv_story)
    TextView tvStory;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.tv_movies_title)
    TextView tvMoviesTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.rv_info_movie)
    RecyclerView rvInfoMovie;
    private MoviesInfoAapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);
    }

    @Override
    public void initView() {
        appBar.addOnOffsetChangedListener(this);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        rvInfoMovie.setLayoutManager(layoutManager);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return adapter.isSpan(position);
            }
        });
        adapter = new MoviesInfoAapter(this);
        rvInfoMovie.setAdapter(adapter);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void inject() {
        getActivityComponent().inject(this);
        mPresenter.attachView(this);
        Bundle bundle = getIntent().getExtras();
        mPresenter.getInfo(bundle.getInt("id"));
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showInfo(List<MoviesInfoBaseData> data, MovieInfoTopData topData) {
        if (topData != null) {
            Glide.with(this).load(topData.getUrl()).asBitmap().into(ivMovieInfo);
            toolbar.setTitle("");
            tvMoviesTitle.setText(topData.getTitle());
            tvName.setText(topData.getTitle());
            tvYear.setText(topData.getYear());
            tvArea.setText(topData.getCountry());
            tvStory.setText(topData.getType());
            tvInfoGrade.setText(topData.getGrade());
            tvInfoNum.setText(topData.getCount());
        }
        adapter.notifyDataChange(data);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (verticalOffset != 0)
            tvMoviesTitle.setVisibility(View.VISIBLE);
        tvMoviesTitle.animate().alpha(Math.abs(verticalOffset));
    }
}
