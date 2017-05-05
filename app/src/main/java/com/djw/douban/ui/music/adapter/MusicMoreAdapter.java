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
import com.djw.douban.R;
import com.djw.douban.data.music.Musics;
import com.djw.douban.ui.music.activity.MoreMusicActivity;
import com.djw.douban.ui.music.activity.MusicInfoActivity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JasonDong
 * <p>
 * on 2017/4/18.
 */

public class MusicMoreAdapter extends RecyclerView.Adapter<MusicMoreAdapter.MusicMoreHolder> implements View.OnClickListener {

    private List<Musics> list;

    private Context context;

    public MusicMoreAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void notifyDataChange(List<Musics> list, boolean isLoadMore) {
        if (isLoadMore) {
            this.list.addAll(list);
            notifyItemRangeChanged(getItemCount() + 1, list.size());
        } else {
            this.list.clear();
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }

    @Override
    public MusicMoreHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MusicMoreHolder(LayoutInflater.from(context).inflate(R.layout.item_music_more, parent, false));
    }

    @Override
    public void onBindViewHolder(MusicMoreHolder holder, int position) {
        Musics musics = list.get(position);
        holder.tvHotDirect.setText(musics.getAuthor().get(0).getName());
        holder.tvHotGrade.setText(musics.getRating().getAverage());
        holder.tvHotCast.setText(musics.getAttrs().getPubdate().get(0));
        holder.tvHotTitle.setText(musics.getTitle());
        holder.tvHotNum.setText(String.valueOf(musics.getRating().getNumRaters()));
        holder.rbHot.setRating(((float) (Double.parseDouble(musics.getRating().getAverage()) / 2)));
        Glide.with(context).load(musics.getImage()).asBitmap().into(holder.ivHotHead);
        holder.llLayout.setTag(musics.getId());
        holder.llLayout.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        bundle.putString("id", ((String) v.getTag()));
        ((MoreMusicActivity) context).startActivity(MusicInfoActivity.class, bundle);
    }

    static class MusicMoreHolder extends RecyclerView.ViewHolder {

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

        MusicMoreHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            AutoUtils.autoSize(itemView);
        }
    }
}
