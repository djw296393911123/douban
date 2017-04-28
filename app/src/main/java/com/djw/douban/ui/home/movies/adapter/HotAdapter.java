package com.djw.douban.ui.home.movies.adapter;

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
import com.djw.douban.data.movies.MoviesItemData;
import com.djw.douban.ui.home.movies.activity.HotActivity;
import com.djw.douban.ui.home.movies.activity.MovieInfoActivity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JasonDong
 * <p>
 * on 2017/4/17.
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
        Glide.with(context).load(subjectBean.getImages().getLarge()).asBitmap().into(holder.ivHotHead);
        holder.tvHotTitle.setText(subjectBean.getTitle());
        holder.tvHotGrade.setText(String.valueOf(subjectBean.getRating().getAverage()));
        holder.rbHot.setRating(((float) (subjectBean.getRating().getAverage() / 2)));
        holder.tvHotNum.setText(String.valueOf(subjectBean.getCollect_count()));
        String daoyan = "导演 ：";
        List<MoviesItemData.SubjectsBean.DirectorsBean> directors = subjectBean.getDirectors();
        if (directors.size() > 0) {
            for (int i = 0; i < directors.size(); i++) {
                daoyan = daoyan + directors.get(i).getName() + "/";
            }
            holder.tvHotDirect.setText(daoyan.substring(0, daoyan.length() - 1));
        }
        String actor = "演员 ：";
        List<MoviesItemData.SubjectsBean.CastsBean> casts = subjectBean.getCasts();
        for (int i = 0; i < casts.size(); i++) {
            actor = actor + casts.get(i).getName() + "/";
        }
        holder.tvHotCast.setText(actor.substring(0, actor.length() - 1));
        holder.llLayout.setOnClickListener(new View.OnClickListener() {
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

    static class HotHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_hot_head)
        ImageView ivHotHead;
        @BindView(R.id.tv_hot_title)
        TextView tvHotTitle;
        @BindView(R.id.rb_hot)
        RatingBar rbHot;
        @BindView(R.id.tv_hot_grade)
        TextView tvHotGrade;
        @BindView(R.id.tv_hot_direct)
        TextView tvHotDirect;
        @BindView(R.id.tv_hot_cast)
        TextView tvHotCast;
        @BindView(R.id.tv_hot_num)
        TextView tvHotNum;
        @BindView(R.id.ll_hot)
        LinearLayout llLayout;

        HotHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            AutoUtils.autoSize(itemView);
        }
    }
}
