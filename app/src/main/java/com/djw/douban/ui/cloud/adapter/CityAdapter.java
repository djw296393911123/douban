package com.djw.douban.ui.cloud.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.djw.douban.R;
import com.djw.douban.data.cloud.CityData;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by JasonDong on 2017/4/12.
 */

public abstract class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityHolder> {

    private List<CityData> list;

    public CityAdapter(List<CityData> list) {
        this.list = list;
    }

    @Override
    public CityHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CityHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city_dowm, parent, false));
    }

    @Override
    public void onBindViewHolder(CityHolder holder, final int position) {
        holder.textview.setText(list.get(position).getTitle());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(list.get(position).getId(), list.get(position).getTitle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class CityHolder extends RecyclerView.ViewHolder {

        private final TextView textview;
        private final LinearLayout layout;

        public CityHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            textview = ((TextView) itemView.findViewById(R.id.tv_city));
            layout = ((LinearLayout) itemView.findViewById(R.id.ll_city));
        }
    }

    public abstract void onItemClick(String id, String title);

}
