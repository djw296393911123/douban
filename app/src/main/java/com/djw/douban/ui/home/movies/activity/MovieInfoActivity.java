package com.djw.douban.ui.home.movies.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.djw.douban.R;
import com.djw.douban.base.RxActivity;
import com.djw.douban.data.movies.MoviesInfoData;
import com.djw.douban.ui.home.movies.adapter.MoviesInfoAapter;
import com.djw.douban.ui.home.movies.contract.MovieInfoContract;
import com.djw.douban.ui.home.movies.presenter.MovieInfoPresenter;

public class MovieInfoActivity extends RxActivity<MovieInfoPresenter> implements MovieInfoContract.View {

    private ImageView head;
    private TextView grade;
    private TextView num;
    private TextView story;
    private TextView year;
    private TextView name;
    private TextView area;
    private MoviesInfoAapter adapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);
    }

    @Override
    public void initView() {
        head = (ImageView) findViewById(R.id.iv_movie_info);
        grade = ((TextView) findViewById(R.id.tv_info_grade));
        num = (TextView) findViewById(R.id.tv_info_num);
        story = (TextView) findViewById(R.id.tv_story);
        year = (TextView) findViewById(R.id.tv_year);
        name = (TextView) findViewById(R.id.tv_name);
        area = (TextView) findViewById(R.id.tv_area);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_info_movie);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MoviesInfoAapter(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void inject() {
        getActivityComponent().inject(this);
        mPresenter.attachView(this);
        mPresenter.getInfo(getIntent().getExtras().getInt("id"));
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showInfo(MoviesInfoData data) {
        Glide.with(this).load(data.getImages().getLarge()).asBitmap().into(head);
        toolbar.setTitle(data.getTitle());
        name.setText(data.getTitle());
        year.setText(data.getYear() + "上映");
        area.setText(data.getCountries().toString());
        story.setText(data.getGenres().toString());
        grade.setText("评分" + data.getRating().getAverage());
        num.setText(String.valueOf(data.getWish_count()) + "人");
        adapter.notifyDataChange(data);
    }
}
