package com.djw.douban.ui.home.music.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.djw.douban.R;
import com.djw.douban.data.music.Musics;
import com.djw.douban.ui.home.movies.adapter.MoviesRecyclerAdapter;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JasonDong on 2017/4/12.
 */

public class MusciAdapter extends RecyclerView.Adapter<MusciAdapter.MusicHolder> {

    private List<Musics> list;

    private Context context;

    public MusciAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    @Override
    public MusicHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MusicHolder(LayoutInflater.from(context).inflate(R.layout.item_home_one, parent, false));

    }

    @Override
    public void onBindViewHolder(MusicHolder holder, int position) {
        Musics musics = list.get(position);
        holder.type.setText(musics.getTitle());
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        MoviesRecyclerAdapter adapter = new MoviesRecyclerAdapter(context);
        holder.recyclerView.setAdapter(adapter);

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MusicHolder extends RecyclerView.ViewHolder {

        private final TextView type;
        private final RecyclerView recyclerView;
        private final TextView more;

        public MusicHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            type = ((TextView) itemView.findViewById(R.id.tv_home_type_title));
            recyclerView = ((RecyclerView) itemView.findViewById(R.id.rv_home));
            more = ((TextView) itemView.findViewById(R.id.tv_home_more));
        }
    }

}
