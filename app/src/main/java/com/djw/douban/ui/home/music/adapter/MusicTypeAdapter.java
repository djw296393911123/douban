package com.djw.douban.ui.home.music.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.djw.douban.R;
import com.djw.douban.data.newmusic.MusicBaseData;
import com.djw.douban.data.newmusic.MusicListData;
import com.djw.douban.data.newmusic.MusicPopData;
import com.djw.douban.data.newmusic.MusicStyleData;
import com.djw.douban.data.newmusic.MusicStyleInfoData;
import com.djw.douban.ui.home.music.activity.ChooseTypeActivity;
import com.djw.douban.ui.home.music.activity.MusicInfoActivity;
import com.djw.douban.util.CityPopWindows;
import com.djw.douban.util.TypePopWindows;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JasonDong on 2017/4/18.
 */

public abstract class MusicTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private List<MusicBaseData> list;

    private Context context;
    private TypePopWindows typePopWindows;

    public MusicTypeAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void notifyDataChange(List<MusicBaseData> list, boolean isLoadMore) {
        if (!isLoadMore) this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case MusicBaseData.ONE:
                return new TypeOneHolder(LayoutInflater.from(context).inflate(R.layout.item_choose_one, parent, false));
            case MusicBaseData.TWO:
                return new TypeTwoHolder(LayoutInflater.from(context).inflate(R.layout.item_choose_two, parent, false));
            case MusicBaseData.THREE:
                return new TypeThreeHolder(LayoutInflater.from(context).inflate(R.layout.item_choose_three, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (list.get(position).getType()) {
            case MusicBaseData.ONE:
                TypeOneHolder oneHolder = (TypeOneHolder) holder;
                MusicStyleData musicStyleData = (MusicStyleData) list.get(position);
                oneHolder.type.setText(musicStyleData.getName());
                RecyclerView recyclerView = oneHolder.recyclerView;
                recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                recyclerView.setAdapter(new MusicTypeHAdapter(musicStyleData.getList()) {
                    @Override
                    public void onItemClick(String tag, int position) {
                        onItemClicks(tag, position);
                    }
                });
                break;
            case MusicBaseData.TWO:
                TypeTwoHolder twoHolder = (TypeTwoHolder) holder;
                MusicPopData popData = (MusicPopData) list.get(position);
                twoHolder.type_one.setText(popData.getList().get(0).getName());
                twoHolder.type_two.setText("筛选");
                typePopWindows = new TypePopWindows(context, popData.getList()) {
                    @Override
                    public void onItemClicks(String id, String title) {
                        onTypeItemClick(title, id);
                    }
                };
                twoHolder.layout1.setOnClickListener(this);
                twoHolder.layout2.setOnClickListener(this);
                break;
            case MusicBaseData.THREE:
                TypeThreeHolder threeHolder = (TypeThreeHolder) holder;
                final MusicListData listData = (MusicListData) this.list.get(position);
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
        typePopWindows.showAsDropDown(v, 5, 5);
    }

    private static class TypeOneHolder extends RecyclerView.ViewHolder {

        private final TextView type;
        private final RecyclerView recyclerView;

        TypeOneHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            type = ((TextView) itemView.findViewById(R.id.tv_type));
            recyclerView = ((RecyclerView) itemView.findViewById(R.id.rv_choose_one));
        }
    }

    private static class TypeTwoHolder extends RecyclerView.ViewHolder {

        private final TextView type_one;
        private final TextView type_two;
        private final RelativeLayout layout1;
        private final RelativeLayout layout2;

        TypeTwoHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            type_one = ((TextView) itemView.findViewById(R.id.tv_type_one));
            type_two = ((TextView) itemView.findViewById(R.id.tv_type_two));
            layout1 = ((RelativeLayout) itemView.findViewById(R.id.rl_one));
            layout2 = ((RelativeLayout) itemView.findViewById(R.id.rl_two));
        }
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

    public abstract void onItemClicks(String tag, int position);

    public abstract void onTypeItemClick(String tag, String position);

}
