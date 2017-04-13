package com.djw.douban.ui.home.movies.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.djw.douban.R;
import com.djw.douban.data.movies.PeopleTwo;
import com.djw.douban.ui.home.movies.activity.PeopleActivity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by JasonDong on 2017/4/10.
 */

public class PeopleCastsAdapter extends RecyclerView.Adapter<PeopleCastsAdapter.PeopleCastsHolder> {

    private List<PeopleTwo> list;

    private Context context;

    public PeopleCastsAdapter(List<PeopleTwo> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public PeopleCastsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PeopleCastsHolder(LayoutInflater.from(context).inflate(R.layout.item_casts, parent, false));
    }

    @Override
    public void onBindViewHolder(PeopleCastsHolder holder, int position) {
        final PeopleTwo peopleTwo = list.get(position);
        holder.name.setText(peopleTwo.getName());
        if (peopleTwo.getUrl().equals(""))
            holder.image.setImageResource(R.mipmap.img_default_meizi);
        else Glide.with(context).load(peopleTwo.getUrl()).asBitmap().into(holder.image);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", Integer.parseInt(peopleTwo.getId()));
                ((PeopleActivity) context).startActivity(PeopleActivity.class, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class PeopleCastsHolder extends RecyclerView.ViewHolder {

        private final ImageView image;
        private final TextView name;
        private final CardView cardView;

        public PeopleCastsHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            cardView = ((CardView) itemView.findViewById(R.id.cv_item));
            image = ((ImageView) itemView.findViewById(R.id.iv_movie));
            name = ((TextView) itemView.findViewById(R.id.tv_name));
        }
    }
}
