package com.djw.douban.ui.music.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.djw.douban.R;
import com.djw.douban.data.newmusic.MusicBaseData;
import com.djw.douban.data.newmusic.MusicStyleInfoData;
import com.djw.douban.data.newmusic.TopOneData;
import com.djw.douban.data.newmusic.TopThreeData;
import com.djw.douban.data.newmusic.TopTwoData;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JasonDong
 * <p>
 * on 2017/4/19.
 */

public abstract class TopAdapter extends RecyclerView.Adapter<TopAdapter.TopHolder> {

    private List<MusicBaseData> list;

    private Context context;
    private MusicTypeHAdapter adapter;

    protected TopAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void notifyDataChange(List<MusicBaseData> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public TopHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TopHolder(LayoutInflater.from(context).inflate(R.layout.item_choose_one, parent, false));
    }

    @Override
    public void onBindViewHolder(TopHolder holder, final int position) {
        switch (list.get(position).getType()) {
            case MusicBaseData.ONE:
                TopOneData topOneData = (TopOneData) list.get(position);
                holder.title.setText(topOneData.getTitle());
                holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                holder.recyclerView.setAdapter(new MusicTypeHAdapter(topOneData.getList()) {
                    @Override
                    public void onItemClick(String tag) {
                        onTopItemClick(tag, MusicBaseData.ONE);
                    }
                });
                break;
            case MusicBaseData.TWO:
                TopTwoData topTwoData = (TopTwoData) list.get(position);
                holder.title.setText(topTwoData.getTitle());
                holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                holder.recyclerView.setAdapter(new MusicTypeHAdapter(topTwoData.getList()) {
                    @Override
                    public void onItemClick(String tag) {
                        onTopItemClick(tag, MusicBaseData.TWO);
                    }
                });
                break;
            case MusicBaseData.THREE:
                TopThreeData topThreeData = (TopThreeData) list.get(position);
                holder.title.setText(topThreeData.getTitle());
                holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                adapter = new MusicTypeHAdapter(topThreeData.getList()) {
                    @Override
                    public void onItemClick(String tag) {
                        onTopItemClick(tag, MusicBaseData.THREE);
                    }
                };
                holder.recyclerView.setAdapter(adapter);
                break;
        }
    }

    public void addType(List<MusicStyleInfoData> list) {
        adapter.addType(list);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class TopHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final RecyclerView recyclerView;

        TopHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            title = ((TextView) itemView.findViewById(R.id.tv_type));
            recyclerView = ((RecyclerView) itemView.findViewById(R.id.rv_choose_one));
        }
    }

    public abstract void onTopItemClick(String tag, int where);

}
