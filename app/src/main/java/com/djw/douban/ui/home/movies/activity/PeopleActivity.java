package com.djw.douban.ui.home.movies.activity;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class PeopleActivity extends RxActivity<PeoplePresenter> implements MoviesPeopleContract.View {

    @BindView(R.id.iv_large)
    ImageView ivLarge;
    @BindView(R.id.iv_small)
    ImageView ivSmall;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_miaoshu)
    TextView tvMiaoshu;
    @BindView(R.id.rv_people)
    RecyclerView rvPeople;
    @BindView(R.id.cv_item)
    CardView cvItem;
    @BindView(R.id.v_black)
    View vBlack;
    private PeopleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_new);
    }

    @Override
    public void initView() {

        rvPeople.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PeopleAdapter(this);
        rvPeople.setAdapter(adapter);

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
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showPeople(PeopleOne peopleOne, List<PeopleBaseData> list, String name) {
        tvName.setText(name);
        tvMiaoshu.setText(peopleOne.getJianjie());
        Glide.with(this).load(peopleOne.getUrl()).bitmapTransform(new CropCircleTransformation(context)).into(ivSmall);
        Glide.with(this).load(peopleOne.getUrl()).asBitmap().into(ivLarge);
        adapter.notifyDataChange(list);
        vBlack.setBackgroundColor(getResources().getColor(R.color.colorAccent));
    }
}
