package com.djw.douban.ui.home.movies.adapter;

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
import com.djw.douban.R;
import com.djw.douban.data.movies.MoviesItemData;
import com.djw.douban.ui.home.movies.activity.HotActivity;
import com.djw.douban.ui.home.movies.activity.MovieInfoActivity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JasonDong on 2017/4/17.
 */

public class HotAdapter extends RecyclerView.Adapter<HotAdapter.HotHolder> {

    private List<MoviesItemData.SubjectsBean> list;

    private Context context;

    public HotAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void notifyDataChange(List<MoviesItemData.SubjectsBean> list, boolean isLoadMore) {
        if (!isLoadMore) this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public HotHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HotHolder(LayoutInflater.from(context).inflate(R.layout.item_hot, parent, false));
    }

    @Override
    public void onBindViewHolder(HotHolder holder, int position) {
        final MoviesItemData.SubjectsBean subjectBean = list.get(position);
        Glide.with(context).load(subjectBean.getImages().getLarge()).asBitmap().into(holder.image);
        holder.name.setText(subjectBean.getTitle());
        holder.grade.setText(String.valueOf(subjectBean.getRating().getAverage()));
        holder.ratingBar.setRating(((float) (subjectBean.getRating().getAverage() / 2)));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", Integer.parseInt(subjectBean.getId()));
                ((HotActivity) context).startActivity(MovieInfoActivity.class, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class HotHolder extends RecyclerView.ViewHolder {

        private final ImageView image;
        private final TextView name;
        private final TextView grade;
        private final CardView cardView;
        private final RatingBar ratingBar;

        HotHolder(View itemView) {
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
