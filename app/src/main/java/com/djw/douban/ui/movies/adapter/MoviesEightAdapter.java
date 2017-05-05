package com.djw.douban.ui.movies.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.djw.douban.MainActivity;
import com.djw.douban.R;
import com.djw.douban.data.newmovies.MoviesEightItemData;
import com.djw.douban.ui.movies.activity.MovieInfoActivity;
import com.djw.douban.ui.movies.activity.TypeActivity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JasonDong
 * <p>
 * on 2017/5/4.
 */
class MoviesEightAdapter extends RecyclerView.Adapter<MoviesEightAdapter.MoviesEightHolder> {

    private List<MoviesEightItemData> list;

    private Context context;

    MoviesEightAdapter(Context context) {
        this.list = new ArrayList<>();
        this.context = context;
    }

    public void notifyDataChange(List<MoviesEightItemData> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public MoviesEightHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MoviesEightHolder(LayoutInflater.from(context).inflate(R.layout.item_eight_content, parent, false));
    }

    @Override
    public void onBindViewHolder(MoviesEightHolder holder, int position) {
        if (position == list.size() - 1) {
            holder.view.setVisibility(View.GONE);
            holder.imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            Glide.with(context).load(Integer.parseInt(list.get(position).getImage())).asBitmap().into(holder.imageView);
        } else
            Glide.with(context).load(list.get(position).getImage()).asBitmap().into(holder.imageView);
        holder.name.setText(list.get(position).getName());
        holder.layout.setTag(list.get(position).getId());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                String tag = (String) v.getTag();
                if (tag.equals("12138")) {
                    bundle.putString("q", "口碑");
                    ((MainActivity) context).startActivity(TypeActivity.class, bundle);
                } else {
                    bundle.putInt("id", Integer.parseInt(tag));
                    ((MainActivity) context).startActivity(MovieInfoActivity.class, bundle);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MoviesEightHolder extends RecyclerView.ViewHolder {

        View view;
        View layout;
        TextView name;
        ImageView imageView;

        MoviesEightHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            imageView = ((ImageView) itemView.findViewById(R.id.iv_eight));
            name = ((TextView) itemView.findViewById(R.id.tv_eight_name));
            layout = itemView.findViewById(R.id.ll_eight);
            view = itemView.findViewById(R.id.iv_right);
        }
    }
}
