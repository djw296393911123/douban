package com.djw.douban.ui.home.movies.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.djw.douban.R;
import com.djw.douban.data.newmovies.NewMoviesTuijianData;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by JasonDong on 2017/5/3.
 */

public class NewMoviesBannerAdapter extends RecyclerView.Adapter<NewMoviesBannerAdapter.BannerHolder> {

    private List<NewMoviesTuijianData> list;

    private Context context;

    public NewMoviesBannerAdapter(List<NewMoviesTuijianData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public BannerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BannerHolder(LayoutInflater.from(context).inflate(R.layout.item_movies_banner, parent, false));
    }

    @Override
    public void onBindViewHolder(BannerHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class BannerHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;

        public BannerHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            imageView = ((ImageView) itemView.findViewById(R.id.iv_banner));
        }
    }
}
