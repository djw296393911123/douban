package com.djw.douban.ui.mine.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.djw.douban.MainActivity;
import com.djw.douban.R;
import com.djw.douban.data.mine.MineListData;
import com.djw.douban.ui.movies.activity.MovieInfoActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JasonDong on 2017/4/14.
 */

public class MineAdapter extends RecyclerView.Adapter<MineAdapter.MineHolder> {

    private List<MineListData> list;

    private Context context;

    public MineAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void notifyDataChange(List<MineListData> list, boolean isLoadMore) {
        if (!isLoadMore) this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public MineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MineHolder(LayoutInflater.from(context).inflate(R.layout.item_mine, parent, false));
    }

    public int span(int position) {
        return list.get(position).getType();
    }

    @Override
    public void onBindViewHolder(MineHolder holder, final int position) {
        Glide.with(context).load(list.get(position).getUrl()).asBitmap().into(holder.image);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("direct", list.get(position).getDirect_id());
                bundle.putInt("id", Integer.parseInt(list.get(position).getId()));
                ((MainActivity) context).startActivity(MovieInfoActivity.class, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MineHolder extends RecyclerView.ViewHolder {

        private final ImageView image;
        private final CardView cardView;

        MineHolder(View itemView) {
            super(itemView);
            image = ((ImageView) itemView.findViewById(R.id.iv_four));
            cardView = ((CardView) itemView.findViewById(R.id.cv_item));
        }
    }

}
