package com.djw.douban.ui.movies.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.djw.douban.MainActivity;
import com.djw.douban.R;
import com.djw.douban.data.newmovies.NewMovieNineItemData;
import com.djw.douban.ui.movies.activity.MovieInfoActivity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JasonDong
 * <p>
 * on 2017/5/4.
 */

class NewMovieTenAdapter extends RecyclerView.Adapter<NewMovieTenAdapter.NewMovieNineHolder> {

    private List<NewMovieNineItemData> list;

    private Context context;

    NewMovieTenAdapter(Context context) {
        this.list = new ArrayList<>();
        this.context = context;
    }

    public void notifyDataChange(List<NewMovieNineItemData> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public NewMovieNineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewMovieNineHolder(LayoutInflater.from(context).inflate(R.layout.item_new_ten_item, parent, false));
    }

    @Override
    public void onBindViewHolder(NewMovieNineHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImg()).asBitmap().into(holder.imageView);
        holder.view.setTag(list.get(position).getId());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", Integer.parseInt(((String) v.getTag())));
                ((MainActivity) context).startActivity(MovieInfoActivity.class, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class NewMovieNineHolder extends RecyclerView.ViewHolder {

        View view;
        ImageView imageView;

        NewMovieNineHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            imageView = ((ImageView) itemView.findViewById(R.id.iv_nine));
            view = itemView.findViewById(R.id.fl_nine);
        }
    }

}
