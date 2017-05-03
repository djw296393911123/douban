package com.djw.douban.ui.movies.adapter;

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
import com.djw.douban.data.movies.NorthAmericaItemData;
import com.djw.douban.ui.movies.activity.MovieInfoActivity;
import com.djw.douban.ui.movies.activity.NorthAmericaActivity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JasonDong
 * <p>
 * on 2017/4/7.
 */

public class NorthAmericaRecyclerAdapter extends RecyclerView.Adapter<NorthAmericaRecyclerAdapter.MoviesHolder> {

    private Context context;

    private List<NorthAmericaItemData.SubjectsBean> list;

    public NorthAmericaRecyclerAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void notifyDataChange(List<NorthAmericaItemData.SubjectsBean> list, boolean isLoadMore) {
        if (!isLoadMore) this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public MoviesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MoviesHolder(LayoutInflater.from(context).inflate(R.layout.item_top250, parent, false));
    }

    @Override
    public void onBindViewHolder(MoviesHolder holder, int position) {
        holder.num.setText(String.valueOf(position + 1));
        final NorthAmericaItemData.SubjectsBean.SubjectBean subjectBean = list.get(position).getSubject();
        NorthAmericaItemData.SubjectsBean.SubjectBean.ImagesBean images = subjectBean.getImages();
        Glide.with(context).load(images.getLarge() == null ? images.getMedium() == null ? images.getSmall() : images.getMedium() : images.getLarge()).asBitmap().into(holder.head);
        holder.title.setText(subjectBean.getTitle());
        holder.pingfen.setText(String.valueOf(subjectBean.getRating().getAverage()));
        holder.ratingBar.setRating(((float) (subjectBean.getRating().getAverage() / 2)));
        String daoyan = "导演 ：";
        List<NorthAmericaItemData.SubjectsBean.SubjectBean.DirectorsBean> directors = subjectBean.getDirectors();
        if (directors.size() > 0) {
            for (int i = 0; i < directors.size(); i++) {
                daoyan = daoyan + directors.get(i).getName() + "/";
            }
            holder.daoyan.setText(daoyan.substring(0, daoyan.length() - 1));
        }
        String actor = "演员 ：";
        List<NorthAmericaItemData.SubjectsBean.SubjectBean.CastsBean> casts = subjectBean.getCasts();
        for (int i = 0; i < casts.size(); i++) {
            actor = actor + casts.get(i).getName() + "/";
        }
        holder.actiors.setText(actor.substring(0, actor.length() - 1));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", Integer.parseInt(subjectBean.getId()));
                ((NorthAmericaActivity) context).startActivity(MovieInfoActivity.class, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MoviesHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final TextView actiors;
        private final TextView daoyan;
        private final RatingBar ratingBar;
        private final ImageView head;
        private final TextView num;
        private final TextView pingfen;
        private final CardView cardView;

        MoviesHolder(View itemView) {
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
