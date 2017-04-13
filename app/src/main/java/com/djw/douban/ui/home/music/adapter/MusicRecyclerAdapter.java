package com.djw.douban.ui.home.music.adapter;

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
import com.djw.douban.data.music.Musics;
import com.djw.douban.ui.home.book.activity.BookInfoActivity;
import com.djw.douban.ui.home.music.activity.MusicInfoActivity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JasonDong on 2017/4/7.
 */

public class MusicRecyclerAdapter extends RecyclerView.Adapter<MusicRecyclerAdapter.MoviesHolder> {

    private Context context;

    private List<Musics> list;

    public MusicRecyclerAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void notifyDataChange(List<Musics> list, boolean isLoadMore) {
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
        final Musics musics = list.get(position);
        Glide.with(context).load(musics.getImage()).asBitmap().into(holder.image);
        holder.name.setText(musics.getTitle());
        holder.grade.setText(String.valueOf(musics.getRating().getAverage()));
        holder.ratingBar.setRating(((float) (Double.parseDouble(musics.getRating().getAverage()) / 2)));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("id", musics.getId());
                ((MainActivity) context).startActivity(MusicInfoActivity.class, bundle);
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

        public MoviesHolder(View itemView) {
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
