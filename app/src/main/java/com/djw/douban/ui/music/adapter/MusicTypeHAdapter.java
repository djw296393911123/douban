package com.djw.douban.ui.music.adapter;

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
 * on 2017/4/18.
 */

abstract class MusicTypeHAdapter extends RecyclerView.Adapter<MusicTypeHAdapter.TypeHolder> {

    private List<MusicStyleInfoData> list;

    MusicTypeHAdapter(List<MusicStyleInfoData> list) {
        this.list = list;
    }

    void addType(List<MusicStyleInfoData> list) {
        this.list.addAll(this.list.size() - 1, list);
        notifyDataSetChanged();
    }

    @Override
    public TypeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TypeHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_choose_type_item, parent, false));
    }

    @Override
    public void onBindViewHolder(TypeHolder holder, int position) {
        holder.title.setSelected(list.get(position).isSelect());
        holder.title.setText(list.get(position).getName());
        holder.layout.setTag(position);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setSelect(false);
                }
                list.get(((int) v.getTag())).setSelect(true);
                notifyDataSetChanged();
                onItemClick(list.get(((int) v.getTag())).getName());
            }
        });
        holder.layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (!list.get(((int) v.getTag())).getName().equals("+自定义标签")) {
                    list.remove(list.get(((int) v.getTag())));
                    notifyDataSetChanged();
                } else {
                    onItemClick("+自定义标签");
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class TypeHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final LinearLayout layout;

        TypeHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            title = ((TextView) itemView.findViewById(R.id.tv_type_title));
            layout = ((LinearLayout) itemView.findViewById(R.id.ll_type));
        }
    }

    public abstract void onItemClick(String tag);

}
