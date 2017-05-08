package com.djw.douban.ui.message.adapter;

import android.content.Context;
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

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case MessageBaseData.RECEIVE:
                return new ReceiveHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_receive, parent, false));
            case MessageBaseData.SEND:
                return new SendHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_send, parent, false));
            case MessageBaseData.IMAGE:
                return new ImgHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_img, parent, false));

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

        }

    }

}
