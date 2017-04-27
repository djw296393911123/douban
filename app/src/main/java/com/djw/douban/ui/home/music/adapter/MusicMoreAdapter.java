package com.djw.douban.ui.home.music.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.djw.douban.R;
import com.djw.douban.data.music.Musics;
import com.djw.douban.ui.home.music.activity.MoreMusicActivity;
import com.djw.douban.ui.home.music.activity.MusicInfoActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JasonDong
 * <p>
 * on 2017/4/18.
 */

public class MusicMoreAdapter extends RecyclerView.Adapter<MusicMoreAdapter.MusicMoreHolder> implements View.OnClickListener {

    private List<Musics> list;

    private Context context;

    public MusicMoreAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void notifyDataChange(List<Musics> list, boolean isLoadMore) {
        if (!isLoadMore) this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public MusicMoreHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MusicMoreHolder(LayoutInflater.from(context).inflate(R.layout.item_music_more, parent, false));
    }

    @Override
    public void onBindViewHolder(MusicMoreHolder holder, int position) {
        Musics musics = list.get(position);
        holder.author.setText(musics.getAuthor().get(0).getName());
        holder.grade.setText(musics.getRating().getAverage());
        holder.time.setText(musics.getAttrs().getPubdate().get(0));
        holder.title.setText(musics.getTitle());
        String string = musics.getAttrs().getTracks().toString();
        holder.pinglun.setText(string.substring(1, string.length() - 1));
        holder.ratingBar.setRating(((float) (Double.parseDouble(musics.getRating().getAverage()) / 2)));
        Glide.with(context).load(musics.getImage()).asBitmap().into(holder.head);
        holder.layout.setTag(musics.getId());
        holder.layout.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        bundle.putString("id", ((String) v.getTag()));
        ((MoreMusicActivity) context).startActivity(MusicInfoActivity.class, bundle);
    }

    static class MusicMoreHolder extends RecyclerView.ViewHolder {

        private final ImageView head;
        private final RatingBar ratingBar;
        private final TextView grade;
        private final TextView author;
        private final TextView time;
        private final TextView title;
        private final TextView pinglun;
        private final LinearLayout layout;

        MusicMoreHolder(View itemView) {
            super(itemView);
            head = ((ImageView) itemView.findViewById(R.id.iv_more_head));
            ratingBar = ((RatingBar) itemView.findViewById(R.id.rb_more));
            grade = ((TextView) itemView.findViewById(R.id.tv_more_grade));
            author = ((TextView) itemView.findViewById(R.id.tv_more_author));
            time = ((TextView) itemView.findViewById(R.id.tv_more_time));
            title = ((TextView) itemView.findViewById(R.id.tv_title));
            pinglun = ((TextView) itemView.findViewById(R.id.tv_pinglun));
            layout = ((LinearLayout) itemView.findViewById(R.id.ll_more));
        }
    }
}
