package com.djw.douban.ui.music.adapter;

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
import com.djw.douban.MainActivity;
import com.djw.douban.R;
import com.djw.douban.data.newmusic.MusicInfoData;
import com.djw.douban.ui.music.activity.MusicInfoActivity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by JasonDong
 * <p>
 * on 2017/4/18.
 */

class MusicContentAdapter extends RecyclerView.Adapter<MusicContentAdapter.MusicContentHolder> implements View.OnClickListener {

    private List<MusicInfoData> list;

    private Context context;

    MusicContentAdapter(List<MusicInfoData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MusicContentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MusicContentHolder(LayoutInflater.from(context).inflate(R.layout.item_music_info, parent, false));
    }

    @Override
    public void onBindViewHolder(MusicContentHolder holder, int position) {
        MusicInfoData musicInfoData = list.get(position);
        holder.name.setText(musicInfoData.getTitle());
        holder.grade.setText(musicInfoData.getGrade());
        holder.singer.setText(musicInfoData.getSinger());
        holder.ratingBar.setRating(((float) (Double.parseDouble(musicInfoData.getGrade()) / 2)));
        Glide.with(context).load(musicInfoData.getUrl()).placeholder(R.mipmap.img_default_meizi).into(holder.head);
        holder.layout.setTag(musicInfoData.getId());
        holder.layout.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        bundle.putString("id", ((String) v.getTag()));
        ((MainActivity) context).startActivity(MusicInfoActivity.class, bundle);
    }

    static class MusicContentHolder extends RecyclerView.ViewHolder {
        private final ImageView head;
        private final TextView name;
        private final RatingBar ratingBar;
        private final TextView grade;
        private final TextView singer;
        private final LinearLayout layout;

        MusicContentHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            head = ((ImageView) itemView.findViewById(R.id.iv_music));
            name = ((TextView) itemView.findViewById(R.id.tv_music_name));
            ratingBar = ((RatingBar) itemView.findViewById(R.id.rb_music));
            grade = ((TextView) itemView.findViewById(R.id.tv_grade));
            singer = ((TextView) itemView.findViewById(R.id.tv_singer));
            layout = ((LinearLayout) itemView.findViewById(R.id.ll_content));
        }
    }
}
