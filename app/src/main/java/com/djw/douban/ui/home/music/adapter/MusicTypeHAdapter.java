package com.djw.douban.ui.home.music.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.djw.douban.R;
import com.djw.douban.data.newmusic.MusicStyleInfoData;

import java.util.List;

/**
 * Created by JasonDong on 2017/4/18.
 */

public abstract class MusicTypeHAdapter extends RecyclerView.Adapter<MusicTypeHAdapter.TypeHolder> {

    private List<MusicStyleInfoData> list;

    public MusicTypeHAdapter(List<MusicStyleInfoData> list) {
        this.list = list;
    }

    @Override
    public TypeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TypeHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_choose_type_item, parent, false));
    }

    @Override
    public void onBindViewHolder(TypeHolder holder, final int position) {
        holder.title.setSelected(list.get(position).isSelect());
        holder.title.setText(list.get(position).getName());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setSelect(false);
                }
                list.get(position).setSelect(true);
                notifyDataSetChanged();
                onItemClick(list.get(position).getName(), position);
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

        public TypeHolder(View itemView) {
            super(itemView);
            title = ((TextView) itemView.findViewById(R.id.tv_type_title));
            layout = ((LinearLayout) itemView.findViewById(R.id.ll_type));
        }
    }

    public abstract void onItemClick(String tag, int position);

}
