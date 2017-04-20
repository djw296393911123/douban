package com.djw.douban.ui.home.book.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.djw.douban.MainActivity;
import com.djw.douban.R;
import com.djw.douban.data.book.Books;
import com.djw.douban.data.book.Images;
import com.djw.douban.ui.home.book.activity.BookInfoActivity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JasonDong on 2017/4/7.
 */

public class BookRecyclerAdapter extends RecyclerView.Adapter<BookRecyclerAdapter.MoviesHolder> {

    private Context context;

    private List<Books> list;

    public BookRecyclerAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void notifyDataChange(List<Books> list, boolean isLoadMore) {
        if (!isLoadMore) this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public MoviesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MoviesHolder(LayoutInflater.from(context).inflate(R.layout.item_book, parent, false));
    }

    @Override
    public void onBindViewHolder(MoviesHolder holder, int position) {
        final Books books = list.get(position);
        Images images = books.getImages();
        Glide.with(context).load(images.getLarge() == null ? images.getMedium() == null ? images.getSmall() : images.getMedium() : images.getLarge()).asBitmap().into(holder.image);
        holder.name.setText(books.getTitle());
        holder.grade.setText(String.valueOf(books.getRating().getAverage()));
        holder.ratingBar.setRating(((float) (Double.parseDouble(books.getRating().getAverage()) / 2)));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("id", books.getId());
                ((MainActivity) context).startActivity(BookInfoActivity.class, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MoviesHolder extends RecyclerView.ViewHolder {

        private final ImageView image;
        private final TextView name;
        private final TextView grade;
        private final CardView cardView;
        private final RatingBar ratingBar;

        MoviesHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            cardView = ((CardView) itemView.findViewById(R.id.cv_item));
            image = ((ImageView) itemView.findViewById(R.id.iv_movie));
            name = ((TextView) itemView.findViewById(R.id.tv_name));
            grade = ((TextView) itemView.findViewById(R.id.tv_grade));
            ratingBar = ((RatingBar) itemView.findViewById(R.id.rb_movies));
        }
    }

}
