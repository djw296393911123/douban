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
import com.djw.douban.data.newmovies.NewMoviewTwoItemData;
import com.djw.douban.ui.movies.activity.HotActivity;
import com.djw.douban.ui.movies.activity.TypeActivity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JasonDong
 * <p>
 * on 2017/5/4.
 */
class NewMoviewTwoAdapter extends RecyclerView.Adapter<NewMoviewTwoAdapter.NewMovieTwoHolder> implements View.OnClickListener {

    private List<NewMoviewTwoItemData> list;

    private Context context;

    NewMoviewTwoAdapter(Context context) {
        this.list = new ArrayList<>();
        this.context = context;
    }

    public void notifyDataChange(List<NewMoviewTwoItemData> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public NewMovieTwoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewMovieTwoHolder(LayoutInflater.from(context).inflate(R.layout.item_new_two_item, parent, false));
    }

    @Override
    public void onBindViewHolder(NewMovieTwoHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImg()).asBitmap().into(holder.img);
        holder.title.setText(list.get(position).getTitle());
        holder.view.setTag(position);
        holder.view.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        switch (((int) v.getTag())) {
            case 0:
                ((MainActivity) context).startActivity(HotActivity.class);
                break;
            case 1:
                Bundle two = new Bundle();
                two.putString("q", "喜剧");
                ((MainActivity) context).startActivity(TypeActivity.class, two);
                break;
            case 2:
                Bundle three = new Bundle();
                three.putString("q", "犯罪");
                ((MainActivity) context).startActivity(TypeActivity.class, three);
                break;
            case 3:
                Bundle four = new Bundle();
                four.putString("q", "爱情");
                ((MainActivity) context).startActivity(TypeActivity.class, four);
                break;
            case 4:
                Bundle five = new Bundle();
                five.putString("q", "悬疑");
                ((MainActivity) context).startActivity(TypeActivity.class, five);
                break;
            case 5:
                Bundle six = new Bundle();
                six.putString("q", "动作");
                ((MainActivity) context).startActivity(TypeActivity.class, six);
                break;
            case 6:
                Bundle seven = new Bundle();
                seven.putString("q", "灾难");
                ((MainActivity) context).startActivity(TypeActivity.class, seven);
                break;
            case 7:
                Bundle eight = new Bundle();
                eight.putString("q", "剧情");
                ((MainActivity) context).startActivity(TypeActivity.class, eight);
                break;
        }
    }

    static class NewMovieTwoHolder extends RecyclerView.ViewHolder {

        View view;
        ImageView img;
        TextView title;

        NewMovieTwoHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            img = ((ImageView) itemView.findViewById(R.id.iv_tow_one));
            title = ((TextView) itemView.findViewById(R.id.tv_two_one));
            view = itemView.findViewById(R.id.ll_two);
        }
    }

}
