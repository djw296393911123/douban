package com.djw.douban.ui.cloud.adapter;

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
import com.djw.douban.MainActivity;
import com.djw.douban.R;
import com.djw.douban.data.cloud.CloudItemData;
import com.djw.douban.ui.cloud.activity.CloudInfoActivity;
import com.djw.douban.ui.cloud.activity.UserActivity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by JasonDong on 2017/4/12.
 */

public class CloudAdapter extends RecyclerView.Adapter<CloudAdapter.CloudHolder> {

    private List<CloudItemData.EventsBean> list;

    private Context context;

    private ViewGroup parent;

    public CloudAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void notifyDataChange(List<CloudItemData.EventsBean> list, boolean isLoadMore) {
        if (!isLoadMore) this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public CloudHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.parent = parent;
        return new CloudHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cloud, parent, false));
    }

    @Override
    public void onBindViewHolder(final CloudHolder holder, final int position) {
        final CloudItemData.EventsBean eventsBean = list.get(position);
        holder.content.setText(eventsBean.getTitle());
        holder.name.setText(eventsBean.getOwner().getName());
        holder.part.setText(eventsBean.getParticipant_count() + "人关注");
        holder.time.setText(eventsBean.getBegin_time());
        holder.type.setText(eventsBean.getCategory_name());
        Glide.with(context).load(eventsBean.getOwner().getAvatar()).bitmapTransform(new CropCircleTransformation(context)).into(holder.head);
        Glide.with(context).load(eventsBean.getImage_hlarge()).error(R.mipmap.img_default_meizi).into(holder.image);
        holder.cha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                notifyDataSetChanged();
            }
        });
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("content", eventsBean.getContent());
                bundle.putString("title", eventsBean.getTitle());
                ((MainActivity) context).startActivity(CloudInfoActivity.class, bundle);
            }
        });
        holder.head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("id", eventsBean.getOwner().getId());
                ((MainActivity) context).startActivity(UserActivity.class, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class CloudHolder extends RecyclerView.ViewHolder {

        private final ImageView head;
        private final TextView name;
        private final TextView part;
        private final TextView type;
        private final TextView content;
        private final TextView time;
        private final ImageView image;
        private final ImageView cha;
        private final CardView cardView;

        public CloudHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            head = ((ImageView) itemView.findViewById(R.id.iv_cloud));
            image = ((ImageView) itemView.findViewById(R.id.iv_content));
            name = ((TextView) itemView.findViewById(R.id.tv_name));
            part = ((TextView) itemView.findViewById(R.id.tv_participant));
            type = ((TextView) itemView.findViewById(R.id.tv_type));
            content = ((TextView) itemView.findViewById(R.id.tv_content));
            time = ((TextView) itemView.findViewById(R.id.tv_time));
            cha = ((ImageView) itemView.findViewById(R.id.iv_cha));
            cardView = ((CardView) itemView.findViewById(R.id.cv_item));
        }
    }

}
