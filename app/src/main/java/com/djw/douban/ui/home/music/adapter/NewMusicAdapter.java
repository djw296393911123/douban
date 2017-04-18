package com.djw.douban.ui.home.music.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.djw.douban.MainActivity;
import com.djw.douban.R;
import com.djw.douban.data.newmusic.MusicBaseData;
import com.djw.douban.data.newmusic.MusicChooseData;
import com.djw.douban.data.newmusic.MusicContentData;
import com.djw.douban.data.newmusic.MusicLikeData;
import com.djw.douban.data.newmusic.MusicNewFiveData;
import com.djw.douban.data.newmusic.MusicTypeData;
import com.djw.douban.ui.home.music.activity.MoreMusicActivity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JasonDong on 2017/4/18.
 */

public class NewMusicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MusicBaseData> list;

    private Context context;

    public NewMusicAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void notifyDataChange(List<MusicBaseData> list) {
//        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case MusicBaseData.ONE:
                return new NewMusicTypeHolder(LayoutInflater.from(context).inflate(R.layout.item_new_music_type, parent, false));
            case MusicBaseData.TWO:
                return new NewMusicContentHolder(LayoutInflater.from(context).inflate(R.layout.item_new_music_content, parent, false));
            case MusicBaseData.THREE:
                return new NewMusicChooseHolder(LayoutInflater.from(context).inflate(R.layout.item_new_music_content, parent, false));
            case MusicBaseData.FOUR:
                return new FourHolder(LayoutInflater.from(context).inflate(R.layout.item_new_music_content, parent, false));
            case MusicBaseData.FIVE:
                return new FiveHolder(LayoutInflater.from(context).inflate(R.layout.item_new_six, parent, false));
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (list.get(position).getType()) {
            case MusicBaseData.ONE:
                NewMusicTypeHolder typeHolder = (NewMusicTypeHolder) holder;
                final MusicTypeData typeData = (MusicTypeData) list.get(position);
                typeHolder.type.setText(typeData.getName());
                typeHolder.more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString("tag", typeData.getName());
                        ((MainActivity) context).startActivity(MoreMusicActivity.class, bundle);
                    }
                });
                break;
            case MusicBaseData.TWO:
                NewMusicContentHolder contentHolder = (NewMusicContentHolder) holder;
                MusicContentData contentData = (MusicContentData) list.get(position);
                RecyclerView recyclerView = contentHolder.recyclerView;
                recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                recyclerView.setAdapter(new MusicContentAdapter(contentData.getList(), context));
                break;
            case MusicBaseData.THREE:
                NewMusicChooseHolder chooseHolder = (NewMusicChooseHolder) holder;
                MusicChooseData chooseData = (MusicChooseData) list.get(position);
                RecyclerView rvChoose = chooseHolder.recyclerView;
                rvChoose.setLayoutManager(new GridLayoutManager(context, 4));
                rvChoose.setAdapter(new MusicChooseAdapter(chooseData.getName(), context));
                break;
            case MusicBaseData.FOUR:
                FourHolder fourHolder = (FourHolder) holder;
                MusicLikeData likeData = (MusicLikeData) list.get(position);
                RecyclerView recyclerView1 = fourHolder.recyclerView;
                recyclerView1.setLayoutManager(new GridLayoutManager(context, 2));
                LikeAdapter adapter = new LikeAdapter(context);
                recyclerView1.setAdapter(adapter);
                adapter.notifyDataChange(likeData.getList(), false);
                break;
            case MusicBaseData.FIVE:
                FiveHolder fiveHolder = (FiveHolder) holder;
                MusicNewFiveData fiveData = (MusicNewFiveData) list.get(position);
                fiveHolder.textView.setText(fiveData.getName());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private static class NewMusicTypeHolder extends RecyclerView.ViewHolder {

        private final TextView type;
        private final TextView more;

        NewMusicTypeHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            type = ((TextView) itemView.findViewById(R.id.tv_music_type));
            more = ((TextView) itemView.findViewById(R.id.tv_music_more));
        }
    }

    private static class NewMusicContentHolder extends RecyclerView.ViewHolder {


        private final RecyclerView recyclerView;

        NewMusicContentHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            recyclerView = ((RecyclerView) itemView.findViewById(R.id.rv_music_content));
        }
    }

    private static class NewMusicChooseHolder extends RecyclerView.ViewHolder {


        private final RecyclerView recyclerView;

        NewMusicChooseHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            recyclerView = ((RecyclerView) itemView.findViewById(R.id.rv_music_content));
        }
    }

    private static class FourHolder extends RecyclerView.ViewHolder {

        private final RecyclerView recyclerView;

        FourHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            recyclerView = ((RecyclerView) itemView.findViewById(R.id.rv_music_content));
        }
    }

    private static class FiveHolder extends RecyclerView.ViewHolder {
        LinearLayout layout;
        TextView textView;

        FiveHolder(View view) {
            super(view);
            AutoUtils.autoSize(view);
            textView = (TextView) view.findViewById(R.id.tv_six_more);
            layout = ((LinearLayout) view.findViewById(R.id.ll_six_layout));
        }
    }

}
