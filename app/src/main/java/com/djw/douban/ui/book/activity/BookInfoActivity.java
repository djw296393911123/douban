package com.djw.douban.ui.book.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
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
import com.djw.douban.base.RxToolbarActivity;
import com.djw.douban.data.book.BookInfoData;
import com.djw.douban.ui.book.contract.BookInfoContract;
import com.djw.douban.ui.book.presenter.BookInfoPresenter;
import com.djw.douban.util.RxUtil;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

public class BookInfoActivity extends RxToolbarActivity<BookInfoPresenter> implements BookInfoContract.View {

    private ImageView head;
    private TextView title;
    private TextView author;
    private TextView out;
    private TextView time;
    private TextView grade;
    private RatingBar ratingBar;
    private TextView num;
    private TextView zuozhe;
    private RelativeLayout layout;
    private Toolbar toolbar;
    private ExpandableTextView etv;

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
        zuozhe = (TextView) findViewById(R.id.tv_zuozhe_jianjie);
        etv = (ExpandableTextView)findViewById(R.id.expand_text_view);
    }

    @Override
    protected void scrollToTop() {

    }

    @Override
    public void doBusiness() {
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
                            layout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
                            toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
                        }
                        head.setImageBitmap(resource);
                    }
                });
            }
        });
        title.setText(bookInfoData.getTitle());
        setToolBarTitle(bookInfoData.getTitle());
        author.setText("作者 : " + (bookInfoData.getAuthor().size() > 0 ? bookInfoData.getAuthor().get(0) : "未知"));
        out.setText("出版社 : " + bookInfoData.getPublisher());
        etv.setText(bookInfoData.getSummary());
        time.setText("出版时间 : " + bookInfoData.getPubdate());
        zuozhe.setText(bookInfoData.getAuthor_intro());
        grade.setText(bookInfoData.getRating().getAverage());
        ratingBar.setRating(((float) (Double.parseDouble(bookInfoData.getRating().getAverage()) / 2)));
        num.setText(String.valueOf(bookInfoData.getRating().getNumRaters()) + "人");
    }
}
