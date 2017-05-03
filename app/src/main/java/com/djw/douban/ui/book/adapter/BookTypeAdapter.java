package com.djw.douban.ui.book.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.djw.douban.R;
import com.djw.douban.data.book.BookTypeData;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JasonDong on
 * <p>
 * 2017/4/11.
 */

public abstract class BookTypeAdapter extends RecyclerView.Adapter<BookTypeAdapter.BookTypeHolder> {

    private List<BookTypeData> list;

    protected BookTypeAdapter() {
        this.list = new ArrayList<>();
    }

    public void notifyDataChange(List<BookTypeData> list) {
        this.list.clear();
        this.list.addAll(list);
    }

    @Override
    public BookTypeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BookTypeHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_type, parent, false));
    }

    @Override
    public void onBindViewHolder(final BookTypeHolder holder, int position) {
        holder.textView.setText(list.get(position).getTitle());
        holder.layout.setSelected(list.get(position).isSelect());
        holder.layout.setTag(position);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setSelect(false);
                }
                list.get(((int) v.getTag())).setSelect(true);
                notifyDataSetChanged();
                OnItemClick(((int) v.getTag()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class BookTypeHolder extends RecyclerView.ViewHolder {

        private final TextView textView;
        private final LinearLayout layout;

        BookTypeHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            textView = ((TextView) itemView.findViewById(R.id.tv_type));
            layout = ((LinearLayout) itemView.findViewById(R.id.ll_type));
        }
    }

    public abstract void OnItemClick(int position);

}
