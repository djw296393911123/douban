package com.djw.douban.ui.home.book.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.djw.douban.R;
import com.djw.douban.base.RxActivity;
import com.djw.douban.data.book.BookInfoData;
import com.djw.douban.ui.home.book.contract.BookInfoContract;
import com.djw.douban.ui.home.book.presenter.BookInfoPresenter;
import com.djw.douban.util.RxUtil;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

public class BookInfoActivity extends RxActivity<BookInfoPresenter> implements BookInfoContract.View {

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
        setContentView(R.layout.activity_book_info);
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
        mPresenter.getContent(getIntent().getExtras().getString("id"));
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showContent(BookInfoData bookInfoData) {
        Glide.with(this).load(bookInfoData.getImages().getLarge()).asBitmap().into(new SimpleTarget<Bitmap>() {
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
        title.setText(bookInfoData.getTitle());
        name.setText(bookInfoData.getTitle());
        author.setText("作者 : " + bookInfoData.getAuthor().get(0));
        out.setText("出版社 : " + bookInfoData.getPublisher());
        jianjie.setText(bookInfoData.getSummary());
        time.setText("出版时间 : " + bookInfoData.getPubdate());
        zuozhe.setText(bookInfoData.getAuthor_intro());
        grade.setText(bookInfoData.getRating().getAverage());
        ratingBar.setRating(((float) (Double.parseDouble(bookInfoData.getRating().getAverage()) / 2)));
        num.setText(String.valueOf(bookInfoData.getRating().getNumRaters()) + "人");
    }
}
