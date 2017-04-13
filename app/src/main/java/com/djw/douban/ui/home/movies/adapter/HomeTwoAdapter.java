package com.djw.douban.ui.home.movies.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.djw.douban.MainActivity;
import com.djw.douban.R;
import com.djw.douban.data.movies.MoviesTwoo;
import com.djw.douban.ui.home.movies.activity.Top250Activity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import retrofit2.http.Url;

/**
 * Created by JasonDong on 2017/4/10.
 */

public class HomeTwoAdapter extends RecyclerView.Adapter<HomeTwoAdapter.HomeTwoHolder> {

    private List<MoviesTwoo> list;

    private Context context;

    public HomeTwoAdapter(List<MoviesTwoo> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public HomeTwoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HomeTwoHolder(LayoutInflater.from(context).inflate(R.layout.item_home_twoo, parent, false));
    }

    @Override
    public void onBindViewHolder(HomeTwoHolder holder, final int position) {
        holder.textview.setText(list.get(position).getName());
        Glide.with(context).load(list.get(position).getUrl()).asBitmap().into(holder.head);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", position);
                ((MainActivity) context).startActivity(Top250Activity.class, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class HomeTwoHolder extends RecyclerView.ViewHolder {

        private final ImageView head;
        private final TextView textview;
        private final CardView cardView;

        public HomeTwoHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            head = ((ImageView) itemView.findViewById(R.id.iv_two));
            textview = ((TextView) itemView.findViewById(R.id.tv_two));
            cardView = ((CardView) itemView.findViewById(R.id.cv_item));
        }
    }


}
