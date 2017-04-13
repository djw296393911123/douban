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
import com.djw.douban.base.BaseActivity;
import com.djw.douban.data.movies.MoviesItemData;
import com.djw.douban.ui.home.movies.activity.MovieInfoActivity;
import com.djw.douban.ui.home.movies.activity.Top250Activity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JasonDong on 2017/4/10.
 */

public class Top250Adapter extends RecyclerView.Adapter<Top250Adapter.Top250Holder> {

    private List<MoviesItemData.SubjectsBean> list;

    private Context context;

    public Top250Adapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void notifyDataChange(List<MoviesItemData.SubjectsBean> list, boolean isLoadMore) {
        if (!isLoadMore) this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public Top250Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Top250Holder(LayoutInflater.from(context).inflate(R.layout.item_top250, parent, false));
    }

    @Override
    public void onBindViewHolder(Top250Holder holder, final int position) {
        holder.num.setText(String.valueOf(position + 1));
        MoviesItemData.SubjectsBean subjectsBean = list.get(position);
        holder.title.setText(subjectsBean.getTitle());
        String daoyan = "导演 ：";
        List<MoviesItemData.SubjectsBean.DirectorsBean> directors = subjectsBean.getDirectors();
        for (int i = 0; i < directors.size(); i++) {
            daoyan = daoyan + directors.get(i).getName() + "/";
        }
        holder.daoyan.setText(daoyan);
        String actor = "演员 ：";
        List<MoviesItemData.SubjectsBean.CastsBean> casts = subjectsBean.getCasts();
        for (int i = 0; i < casts.size(); i++) {
            actor = actor + casts.get(i).getName() + "/";
        }
        holder.actiors.setText(actor);
        Glide.with(context).load(subjectsBean.getImages().getLarge()).asBitmap().into(holder.head);
        holder.ratingBar.setRating(((float) (subjectsBean.getRating().getAverage() / 2)));
        holder.pingfen.setText(String.valueOf(subjectsBean.getRating().getAverage()));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", Integer.parseInt(list.get(position).getId()));
                ((BaseActivity) context).startActivity(MovieInfoActivity.class, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Top250Holder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final TextView actiors;
        private final TextView daoyan;
        private final RatingBar ratingBar;
        private final ImageView head;
        private final TextView num;
        private final TextView pingfen;
        private final CardView cardView;

        public Top250Holder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            title = ((TextView) itemView.findViewById(R.id.tv_title));
            actiors = ((TextView) itemView.findViewById(R.id.tv_actors));
            daoyan = ((TextView) itemView.findViewById(R.id.tv_daoyan));
            ratingBar = ((RatingBar) itemView.findViewById(R.id.rb_movies));
            head = ((ImageView) itemView.findViewById(R.id.iv_head));
            num = ((TextView) itemView.findViewById(R.id.tv_num));
            pingfen = ((TextView) itemView.findViewById(R.id.tv_pingfen));
            cardView = ((CardView) itemView.findViewById(R.id.cv_item));
        }
    }
}
