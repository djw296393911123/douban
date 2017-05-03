package com.djw.douban.ui.movies.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.djw.douban.R;
import com.djw.douban.data.newmovies.NewMoviesFour;
import com.djw.douban.ui.movies.activity.MovieInfoActivity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JasonDong
 * <p>
 * on 2017/4/20.
 */

class AlsoListAdapter extends RecyclerView.Adapter<AlsoListAdapter.ViewHolder> implements View.OnClickListener {

    private List<NewMoviesFour> list;

    private Context context;

    AlsoListAdapter(List<NewMoviesFour> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_also_like, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NewMoviesFour newMoviesFour = list.get(position);
        holder.tvGrade.setText(newMoviesFour.getGrade());
        holder.tvLikeName.setText(newMoviesFour.getName());
        Glide.with(context).load(newMoviesFour.getUrl()).asBitmap().into(holder.ivLike);
        holder.llContent.setTag(newMoviesFour.getId());
        holder.llContent.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", Integer.parseInt(((String) v.getTag())));
        ((MovieInfoActivity) context).startActivity(MovieInfoActivity.class, bundle);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_like)
        ImageView ivLike;
        @BindView(R.id.tv_like_name)
        TextView tvLikeName;
        @BindView(R.id.tv_grade)
        TextView tvGrade;
        @BindView(R.id.ll_content)
        LinearLayout llContent;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            AutoUtils.autoSize(view);
        }
    }
}
