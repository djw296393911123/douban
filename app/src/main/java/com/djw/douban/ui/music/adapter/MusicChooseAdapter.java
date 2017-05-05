package com.djw.douban.ui.music.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.djw.douban.MainActivity;
import com.djw.douban.R;
import com.djw.douban.ui.music.activity.ChooseTypeActivity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JasonDong
 * <p>
 * on 2017/4/18.
 */
class MusicChooseAdapter extends RecyclerView.Adapter<MusicChooseAdapter.MusicChooseHolder> implements View.OnClickListener {

    private List<String> list;

    private Context context;

    MusicChooseAdapter(Context context) {
        this.list = new ArrayList<>();
        this.context = context;
    }

    public void notifyDataChange(List<String> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public MusicChooseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MusicChooseHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_music_choose, parent, false));
    }

    @Override
    public void onBindViewHolder(MusicChooseHolder holder, int position) {
        holder.choose.setText(list.get(position));
        holder.layout.setTag(list.get(position) + "," + position);
        holder.layout.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        String tag = (String) v.getTag();
        String[] split = tag.split(",");
        bundle.putString("tag", split[0]);
        bundle.putInt("position", Integer.parseInt(split[1]));
        ((MainActivity) context).startActivity(ChooseTypeActivity.class, bundle);

    }

    static class MusicChooseHolder extends RecyclerView.ViewHolder {
        private final TextView choose;
        private final LinearLayout layout;

        MusicChooseHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            choose = ((TextView) itemView.findViewById(R.id.tv_choose_type));
            layout = ((LinearLayout) itemView.findViewById(R.id.ll_choose));
        }
    }
}
