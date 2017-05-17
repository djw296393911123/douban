package com.djw.douban.ui.book.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.djw.douban.R;
import com.djw.douban.data.book.Books;
import com.djw.douban.ui.book.activity.BookFromTagActivity;
import com.djw.douban.ui.book.activity.BookInfoActivity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JasonDong
 * <p>
 * on 2017/5/17.
 */

public class BookTagAdapter extends RecyclerView.Adapter<BookTagAdapter.BookTagHolder> {

    private List<Books> list;

    public BookTagAdapter() {
        this.list = new ArrayList<>();
    }

    public void notifyDataChange(List<Books> list, boolean isLoadMore) {
        if (isLoadMore) {
            this.list.addAll(list);
            notifyItemRangeChanged(getItemCount() + 1, list.size());
        } else {
            this.list.clear();
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }

    @Override
    public BookTagHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BookTagHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_tag, parent, false));
    }

    @Override
    public void onBindViewHolder(BookTagHolder holder, int position) {
        holder.loadData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class BookTagHolder extends RecyclerView.ViewHolder {
        private final Context context;
        @BindView(R.id.iv_hot_head)
        ImageView ivHotHead;
        @BindView(R.id.tv_hot_title)
        TextView tvHotTitle;
        @BindView(R.id.rb_hot)
        RatingBar rbHot;
        @BindView(R.id.tv_hot_grade)
        TextView tvHotGrade;
        @BindView(R.id.ll_hot)
        LinearLayout llLayout;
        @BindView(R.id.tv_hot_direct)
        TextView tvAuthor;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_price)
        TextView tvPrive;

        BookTagHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            AutoUtils.autoSize(itemView);
            context = view.getContext();
        }

        void loadData(final Books listData) {
            Glide.with(context).load(listData.getImages().getLarge()).asBitmap().into(ivHotHead);
            tvHotTitle.setText(listData.getTitle());
            tvPrive.setText("出版社 : " + listData.getPublisher());
            tvTime.setText("出版时间 ：" + listData.getPubdate());
            tvAuthor.setText("作者 ：" + listData.getAuthor().toString().substring(1, listData.getAuthor().toString().length() - 1));
            tvHotGrade.setText(listData.getRating().getAverage());
            rbHot.setRating(((float) (Double.parseDouble(listData.getRating().getAverage()) / 2)));
            llLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("id", listData.getId());
                    ((BookFromTagActivity) context).startActivity(BookInfoActivity.class, bundle);
                }
            });
        }
    }

}
