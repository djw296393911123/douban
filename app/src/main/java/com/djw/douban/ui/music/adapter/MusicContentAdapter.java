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
import com.djw.douban.data.newmusic.MusicContentBaseData;
import com.djw.douban.data.newmusic.MusicInfoData;
import com.djw.douban.data.newmusic.MusicMoreData;
import com.djw.douban.ui.music.activity.MoreMusicActivity;
import com.djw.douban.ui.music.activity.MusicInfoActivity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JasonDong
 * <p>
 * on 2017/4/18.
 */

class MusicContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private List<MusicContentBaseData> list;

    private Context context;

    MusicContentAdapter(Context context) {
        this.list = new ArrayList<>();
        this.context = context;
    }

    public void notifyDataChange(List<MusicContentBaseData> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return viewType == MusicContentBaseData.NORMAL ?
                new MusicContentHolder(LayoutInflater.from(context).inflate(R.layout.item_music_info_card, parent, false)) :
                new MusicContentMoreHolder(LayoutInflater.from(context).inflate(R.layout.item_music_content_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (list.get(position).getType()) {
            case MusicContentBaseData.NORMAL:
                MusicContentHolder contentHolder = (MusicContentHolder) holder;
                MusicInfoData musicInfoData = ((MusicInfoData) list.get(position));
                contentHolder.name.setText(musicInfoData.getTitle());
                contentHolder.grade.setText(musicInfoData.getGrade());
                contentHolder.singer.setText(musicInfoData.getSinger());
                contentHolder.ratingBar.setRating(((float) (Double.parseDouble(musicInfoData.getGrade()) / 2)));
                Glide.with(context).load(musicInfoData.getUrl()).placeholder(R.mipmap.img_default_meizi).into(contentHolder.head);
                contentHolder.layout.setTag(musicInfoData.getId());
                contentHolder.layout.setOnClickListener(this);
                break;
            case MusicContentBaseData.MORE:
                MusicContentMoreHolder moreHolder = (MusicContentMoreHolder) holder;
                final MusicMoreData moreData = (MusicMoreData) list.get(position);
                Glide.with(context).load(R.mipmap.look_more).asBitmap().into(moreHolder.imageView);
                moreHolder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString("tag", moreData.getName());
                        ((MainActivity) context).startActivity(MoreMusicActivity.class, bundle);
                    }
                });
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        bundle.putString("id", ((String) v.getTag()));
        ((MainActivity) context).startActivity(MusicInfoActivity.class, bundle);
    }

    private static class MusicContentHolder extends RecyclerView.ViewHolder {
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

    private static class MusicContentMoreHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        View view;

        MusicContentMoreHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            view = itemView.findViewById(R.id.ll_more);
            imageView = ((ImageView) itemView.findViewById(R.id.iv_more));
        }
    }

}
