package com.djw.douban.ui.message.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.djw.douban.R;
import com.djw.douban.data.message.MessageBaseData;
import com.djw.douban.data.message.MessageImgData;
import com.djw.douban.data.message.MessageReceiveData;
import com.djw.douban.data.message.MessageSendData;
import com.djw.douban.data.message.MessageTimeData;
import com.djw.douban.data.message.MessageUrlData;
import com.djw.douban.ui.cloud.fragment.ImageFragment;
import com.djw.douban.ui.message.MessageActivity;
import com.djw.douban.ui.message.WebviewActivity;
import com.djw.douban.ui.message.fragment.PageFragment;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JasonDong
 * <p>
 * on 2017/5/5.
 */

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MessageBaseData> list;

    public MessageAdapter() {
        this.list = new ArrayList<>();
    }

    public void notifyDataChange(MessageBaseData data) {
        this.list.add(data);
        notifyDataSetChanged();
    }

    public void notifyListChange(List<MessageBaseData> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void notifyDeleteAll() {
        this.list.clear();
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case MessageBaseData.RECEIVE:
                return new ReceiveHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_receive, parent, false));
            case MessageBaseData.SEND:
                return new SendHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_send, parent, false));
            case MessageBaseData.IMAGE:
                return new ImgHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_img, parent, false));
            case MessageBaseData.TIME:
                return new TimeHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_time, parent, false));
            case MessageBaseData.URL:
                return new URLHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_receive, parent, false));

        }

        return new SendHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_send, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (list.get(position).getType()) {
            case MessageBaseData.RECEIVE:
                ReceiveHolder receiveHolder = (ReceiveHolder) holder;
                MessageReceiveData receiveData = (MessageReceiveData) list.get(position);
                receiveHolder.loadData(receiveData);
                break;
            case MessageBaseData.SEND:
                SendHolder sendHolder = (SendHolder) holder;
                MessageSendData sendData = (MessageSendData) list.get(position);
                sendHolder.loadData(sendData);
                break;
            case MessageBaseData.IMAGE:
                ImgHolder imgHolder = (ImgHolder) holder;
                MessageImgData imgData = (MessageImgData) list.get(position);
                imgHolder.loadData(imgData);
                break;
            case MessageBaseData.TIME:
                TimeHolder timeHolder = (TimeHolder) holder;
                MessageTimeData timeData = (MessageTimeData) list.get(position);
                timeHolder.loadData(timeData);
                break;
            case MessageBaseData.URL:
                URLHolder urlHolder = (URLHolder) holder;
                MessageUrlData urlData = (MessageUrlData) list.get(position);
                urlHolder.loadData(urlData);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    private static class ReceiveHolder extends RecyclerView.ViewHolder {

        private final TextView text;
        private final ImageView head;
        private final Context context;

        ReceiveHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            context = itemView.getContext();
            text = ((TextView) itemView.findViewById(R.id.tv_receive));
            head = ((ImageView) itemView.findViewById(R.id.iv_head));
        }

        void loadData(MessageReceiveData data) {
            text.setText(data.getText());
            Glide.with(context).load(R.mipmap.hot).into(head);
        }
    }

    private static class SendHolder extends RecyclerView.ViewHolder {

        private final ImageView head;
        private final TextView text;
        private final Context context;

        SendHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            context = itemView.getContext();
            head = ((ImageView) itemView.findViewById(R.id.iv_head));
            text = ((TextView) itemView.findViewById(R.id.tv_send));
        }

        void loadData(MessageSendData data) {

            text.setText(data.getMessage());
            Glide.with(context).load(R.mipmap.hot).into(head);

        }

    }

    private static class ImgHolder extends RecyclerView.ViewHolder {

        private final ImageView head;
        private final ImageView img;
        private final Context context;

        ImgHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            context = itemView.getContext();
            img = ((ImageView) itemView.findViewById(R.id.iv_img));
            head = ((ImageView) itemView.findViewById(R.id.iv_head));
        }

        void loadData(MessageImgData data) {

            Glide.with(context).load(data.getUrl()).into(img);

            Glide.with(context).load(R.mipmap.hot).into(head);

            img.setTag(data.getUrl());

            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    FragmentTransaction transaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                    transaction.add(ImageFragment.newInstance(((String) v.getTag())), "img");
                    transaction.commitAllowingStateLoss();
                }
            });

        }

    }

    private static class TimeHolder extends RecyclerView.ViewHolder {


        private final TextView time;

        TimeHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            time = ((TextView) itemView.findViewById(R.id.tv_time));
        }

        void loadData(MessageTimeData data) {

            time.setText(data.getTime());
        }

    }

    private static class URLHolder extends RecyclerView.ViewHolder {


        private final TextView text;
        private final Context context;
        private final ImageView head;

        URLHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            context = itemView.getContext();
            text = ((TextView) itemView.findViewById(R.id.tv_receive));
            text.setTextColor(Color.BLUE);
            head = ((ImageView) itemView.findViewById(R.id.iv_head));
        }

        void loadData(final MessageUrlData data) {
            Glide.with(context).load(R.mipmap.hot).into(head);
            text.setText(data.getUrl());
            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("url", data.getUrl());
                    ((MessageActivity) context).startActivity(WebviewActivity.class, bundle);
                }
            });
        }

    }

}
