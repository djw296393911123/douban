package com.djw.douban.ui.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.djw.douban.R;
import com.djw.douban.data.mine.LikeOrHideData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JasonDong on 2017/4/14.
 */

public class MineAdapter extends RecyclerView.Adapter<MineAdapter.MineHolder> {

    private List<LikeOrHideData> list;

    private Context context;

    public MineAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void notifyDataChange(List<LikeOrHideData> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public MineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MineHolder(LayoutInflater.from(context).inflate(R.layout.item_mine, parent, false));
    }

    @Override
    public void onBindViewHolder(MineHolder holder, int position) {
        LikeOrHideData likeOrHideData = list.get(position);
        holder.grade.setText(likeOrHideData.getGrade());
        holder.name.setText(likeOrHideData.getName());
        holder.ratingBar.setRating((float) (Double.parseDouble(likeOrHideData.getGrade()) / 2));
        Glide.with(context).load(likeOrHideData.getUrl()).asBitmap().into(holder.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MineHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView grade;
        private final RatingBar ratingBar;
        private final ImageView image;

        public MineHolder(View itemView) {
            super(itemView);
            name = ((TextView) itemView.findViewById(R.id.tv_foru_name));
            grade = ((TextView) itemView.findViewById(R.id.tv_four_grade));
            ratingBar = ((RatingBar) itemView.findViewById(R.id.rb_four));
            image = ((ImageView) itemView.findViewById(R.id.iv_four));
        }
    }

}
