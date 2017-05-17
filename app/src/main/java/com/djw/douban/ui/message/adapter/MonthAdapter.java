package com.djw.douban.ui.message.adapter;

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
 * Created by JasonDong
 * <p>
 * on 2017/4/12.
 */

public abstract class MonthAdapter extends RecyclerView.Adapter<MonthAdapter.CityHolder> {

    private List<String> list;

    public MonthAdapter(List<String> list) {
        this.list = list;
    }

    @Override
    public CityHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CityHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city_dowm, parent, false));
    }

    @Override
    public void onBindViewHolder(CityHolder holder, int position) {
        final String month = list.get(position);
        holder.textview.setText(month);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(month);
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

        CityHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            textview = ((TextView) itemView.findViewById(R.id.tv_city));
            layout = ((LinearLayout) itemView.findViewById(R.id.ll_city));
        }
    }

    public abstract void onItemClick(String month);

}
