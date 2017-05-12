package com.djw.douban.ui.message.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.djw.douban.R;
import com.djw.douban.data.things.ThingsBaseData;
import com.djw.douban.data.things.ThingsData;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JasonDong on 2017/5/12.
 */

public class ThingsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ThingsBaseData> list;

    public ThingsAdapter() {
        this.list = new ArrayList<>();
    }

    public void notifyDataChange(List<ThingsBaseData> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ThingsBaseData.NORMAL:
                return new NormalHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_things_normal, parent, false));
            case ThingsBaseData.THINGS:
                return new ThingsHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_things_thing, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (list.get(position).getType()) {
            case ThingsBaseData.NORMAL:
                NormalHolder normalHolder = (NormalHolder) holder;
                normalHolder.layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                break;
            case ThingsBaseData.THINGS:
                ThingsHolder thingsHolder = (ThingsHolder) holder;
                ThingsData thingsData = (ThingsData) list.get(position);
                thingsHolder.things.setText(thingsData.getThings());
                thingsHolder.time.setText(thingsData.getTime());
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

    private static class NormalHolder extends RecyclerView.ViewHolder {

        private final View layout;

        NormalHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            layout = itemView.findViewById(R.id.ll_thing);
        }
    }

    private static class ThingsHolder extends RecyclerView.ViewHolder {

        private final TextView time;
        private final TextView things;

        ThingsHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            time = ((TextView) itemView.findViewById(R.id.tv_normal_time));
            things = ((TextView) itemView.findViewById(R.id.tv_normal_things));
        }
    }

}
