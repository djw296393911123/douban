package com.djw.douban.ui.home.music.activity;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.djw.douban.R;
import com.djw.douban.base.RxActivity;
import com.djw.douban.data.music.Musics;
import com.djw.douban.ui.home.music.contract.MusicInfoContract;
import com.djw.douban.ui.home.music.presenter.MusicInfoPresenter;
import com.djw.douban.util.RxUtil;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

public class MusicInfoActivity extends RxActivity<MusicInfoPresenter> implements MusicInfoContract.View {
    private ImageView head;
    private TextView title;
    private TextView author;
    private TextView out;
    private TextView time;
    private TextView grade;
    private RatingBar ratingBar;
    private TextView num;
    private TextView jianjie;
    private TextView zuozhe;
    private RelativeLayout layout;
    private Toolbar toolbar;
    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_info);
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showMusci(Musics musics) {
        Glide.with(this).load(musics.getImage()).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(final Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                Observable.create(new Observable.OnSubscribe<Palette.Swatch>() {
                    @Override
                    public void call(Subscriber<? super Palette.Swatch> subscriber) {
                        subscriber.onNext(Palette.from(resource).generate().getDarkMutedSwatch());
                    }
                }).compose(RxUtil.<Palette.Swatch>rxSchedulerHelper()).subscribe(new Action1<Palette.Swatch>() {
                    @Override
                    public void call(Palette.Swatch swatch) {
                        if (swatch != null) {
                            layout.setBackgroundColor(swatch.getRgb());
                            toolbar.setBackgroundColor(swatch.getRgb());
                        } else {
                            layout.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                            toolbar.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                        }
                        head.setImageBitmap(resource);
                    }
                });

            }
        });
        title.setText(musics.getTitle());
        name.setText(musics.getTitle());
        author.setText("表演者 : " + musics.getTags().get(0).getName());
        jianjie.setText(musics.getSummary());
        time.setText("发行时间 : " + musics.getAttrs().getPubdate().get(0));
        out.setText("发行商 : " + musics.getAttrs().getPublisher().get(0));
        String string = musics.getAttrs().getTracks().toString();
        zuozhe.setText(string.substring(1, string.length() - 1));
        grade.setText(musics.getRating().getAverage());
        ratingBar.setRating(((float) (Double.parseDouble(musics.getRating().getAverage()) / 2)));
        num.setText(String.valueOf(musics.getRating().getNumRaters()) + "人");
    }

    @Override
    public void initView() {
        toolbar = (Toolbar) findViewById(R.id.tl_base);
        layout = (RelativeLayout) findViewById(R.id.rl_head);
        head = ((ImageView) findViewById(R.id.iv_head));
        title = (TextView) findViewById(R.id.tv_book_title);
        author = (TextView) findViewById(R.id.tv_author);
        out = (TextView) findViewById(R.id.tv_out);
        time = (TextView) findViewById(R.id.tv_time);
        grade = (TextView) findViewById(R.id.tv_grade);
        ratingBar = (RatingBar) findViewById(R.id.rb_movies);
        num = (TextView) findViewById(R.id.tv_num);
        jianjie = (TextView) findViewById(R.id.tv_jianjie);
        zuozhe = (TextView) findViewById(R.id.tv_zuozhe_jianjie);
    }

    @Override
    public void doBusiness() {
        toolbar.setTitle("");
        name = ((TextView) toolbar.findViewById(R.id.tv_toolbar_title));
    }

    @Override
    public void inject() {
        getActivityComponent().inject(this);
        mPresenter.attachView(this);
        mPresenter.getMusic(getIntent().getExtras().getString("id"));
    }
}
