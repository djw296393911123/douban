package com.djw.douban.ui.movies.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.djw.douban.R;
import com.djw.douban.data.newmusic.MusicStyleInfoData;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by JasonDong
 * <p>
 * on 2017/4/12.
 */

public abstract class SelectTypeAdapter extends RecyclerView.Adapter<SelectTypeAdapter.CityHolder> {

    private List<MusicStyleInfoData> list;

    protected SelectTypeAdapter(List<MusicStyleInfoData> list) {
        this.list = list;
    }

    @Override
    public CityHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CityHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city_dowm, parent, false));
    }

    @Override
    public void onBindViewHolder(CityHolder holder, int position) {
        holder.textview.setText(list.get(position).getName());
        holder.textview.setSelected(list.get(position).isSelect());
        holder.layout.setTag(position);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setSelect(false);
                }
                list.get(((int) v.getTag())).setSelect(true);
                notifyDataSetChanged();
                onItemClick(String.valueOf(((int) v.getTag())), list.get(((int) v.getTag())).getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class CityHolder extends RecyclerView.ViewHolder {

        private final TextView textview;
        private final LinearLayout layout;

        CityHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            textview = ((TextView) itemView.findViewById(R.id.tv_city));
            layout = ((LinearLayout) itemView.findViewById(R.id.ll_city));
        }
    }

    public abstract void onItemClick(String id, String title);

}
