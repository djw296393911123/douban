package com.djw.douban.ui.girl.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.djw.douban.R;
import com.djw.douban.base.SimpleActivity;
import com.djw.douban.util.EasyTransition;

public class GirlLargeActivity extends SimpleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_girl_large);
        EasyTransition.enter(this);
    }

    @Override
    public void initView() {
        ImageView large = (ImageView) findViewById(R.id.iv_img);
        Glide.with(this).load(getIntent().getExtras().getString("url")).into(large);
        large.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onBackPressed() {
        EasyTransition.exit(this);
    }
}
