package com.djw.douban.ui.book.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.djw.douban.R;
import com.djw.douban.data.newbook.BookListItemData;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JasonDong on 2017/5/5.
 */

public class BookNewAdapter extends RecyclerView.Adapter<BookNewAdapter.BookNewHolder> {

    private List<BookListItemData> list;

    public BookNewAdapter() {
        this.list = new ArrayList<>();
    }

    public void notifyDataChange(List<BookListItemData> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public BookNewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BookNewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_new_item, parent, false));
    }

    @Override
    public void onBindViewHolder(BookNewHolder holder, int position) {

        holder.loadData(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class BookNewHolder extends RecyclerView.ViewHolder {

        private final ImageView image;
        private final TextView name;
        private final Context context;

        BookNewHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            context = itemView.getContext();
            image = ((ImageView) itemView.findViewById(R.id.iv_movie));
            name = ((TextView) itemView.findViewById(R.id.tv_name));
        }

        void loadData(BookListItemData data) {
            name.setText(data.getName());
            Glide.with(context).load(data.getImg()).asBitmap().into(image);
        }

    }

}
