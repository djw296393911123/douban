package com.djw.douban.ui.home.movies.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.djw.douban.R;
import com.djw.douban.base.RxActivity;
import com.djw.douban.data.movies.MoviesActorsData;
import com.djw.douban.data.movies.PeopleBaseData;
import com.djw.douban.ui.home.movies.adapter.PeopleAdapter;
import com.djw.douban.ui.home.movies.contract.MoviesPeopleContract;
import com.djw.douban.ui.home.movies.presenter.PeoplePresenter;

import java.util.List;

public class PeopleActivity extends RxActivity<PeoplePresenter> implements MoviesPeopleContract.View {

    private PeopleAdapter adapter;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
    }

    @Override
    public void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tl_base);
        toolbar.setTitle("");
        title = (TextView) findViewById(R.id.tv_toolbar_title);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_people);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PeopleAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void doBusiness() {

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
    public void showPeople(List<PeopleBaseData> list,String name) {
        title.setText(name);
        adapter.notifyDataChange(list);
    }
}
