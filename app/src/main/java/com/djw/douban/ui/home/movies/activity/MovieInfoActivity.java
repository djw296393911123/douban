package com.djw.douban.ui.home.movies.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.djw.douban.R;
import com.djw.douban.base.RxActivity;
import com.djw.douban.data.movies.MoviesInfoBaseData;
import com.djw.douban.data.movies.MoviesInfoData;
import com.djw.douban.data.newmovies.MovieInfoTopData;
import com.djw.douban.ui.home.movies.adapter.MoviesInfoAapter;
import com.djw.douban.ui.home.movies.contract.MovieInfoContract;
import com.djw.douban.ui.home.movies.presenter.MovieInfoPresenter;

import java.util.List;

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
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return adapter.isSpan(position);
            }
        });
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
        Bundle bundle = getIntent().getExtras();
        mPresenter.getInfo(bundle.getInt("id"), bundle.getString("direct"));
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showInfo(List<MoviesInfoBaseData> data, MovieInfoTopData topData) {
        if (topData != null) {
            Glide.with(this).load(topData.getUrl()).asBitmap().into(head);
            toolbar.setTitle(topData.getTitle());
            name.setText(topData.getTitle());
            year.setText(topData.getYear());
            area.setText(topData.getCountry());
            story.setText(topData.getType());
            grade.setText(topData.getGrade());
            num.setText(topData.getCount());
        }
        adapter.notifyDataChange(data);
    }
}
