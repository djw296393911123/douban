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
import com.djw.douban.data.newmusic.MusicBaseData;
import com.djw.douban.data.newmusic.MusicListData;
import com.djw.douban.ui.music.activity.ChooseTypeActivity;
import com.djw.douban.ui.music.activity.MusicInfoActivity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JasonDong on 2017/4/18.
 *
 */

public class MusicTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private List<MusicListData> list;

    private Context context;

    public MusicTypeAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void notifyDataChange(List<MusicListData> list, boolean isLoadMore) {
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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case MusicBaseData.THREE:
                return new TypeThreeHolder(LayoutInflater.from(context).inflate(R.layout.item_choose_three, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (list.get(position).getType()) {
            case MusicBaseData.THREE:
                TypeThreeHolder threeHolder = (TypeThreeHolder) holder;
                final MusicListData listData = list.get(position);
                threeHolder.author.setText(listData.getAuthor());
                threeHolder.grade.setText(listData.getGrade());
                threeHolder.title.setText(listData.getName());
                threeHolder.ratingBar.setRating(((float) (Double.parseDouble(listData.getGrade()) / 2)));
                Glide.with(context).load(listData.getUrl()).asBitmap().into(threeHolder.head);
                threeHolder.layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString("id", listData.getId());
                        ((ChooseTypeActivity) context).startActivity(MusicInfoActivity.class, bundle);
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
    }

    private static class TypeThreeHolder extends RecyclerView.ViewHolder {

        private final ImageView head;
        private final TextView title;
        private final RatingBar ratingBar;
        private final TextView grade;
        private final TextView author;
        private final LinearLayout layout;

        TypeThreeHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            head = ((ImageView) itemView.findViewById(R.id.iv_choose_head));
            title = ((TextView) itemView.findViewById(R.id.tv_choose_title));
            ratingBar = ((RatingBar) itemView.findViewById(R.id.rb_choose));
            grade = ((TextView) itemView.findViewById(R.id.tv_choose_grade));
            author = ((TextView) itemView.findViewById(R.id.tv_choose_author));
            layout = ((LinearLayout) itemView.findViewById(R.id.ll_choose_three));
        }
    }
}
