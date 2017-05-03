package com.djw.douban.ui.search.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.djw.douban.R;
import com.djw.douban.data.search.SearchBaseData;
import com.djw.douban.data.search.SearchNormalData;
import com.djw.douban.data.search.SearchTypeData;
import com.djw.douban.ui.search.activity.SearchActivity;
import com.djw.douban.ui.book.activity.BookInfoActivity;
import com.djw.douban.ui.movies.activity.MovieInfoActivity;
import com.djw.douban.ui.music.activity.MusicInfoActivity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JasonDong
 * <p>
 * on 2017/4/17.
 */

public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private List<SearchBaseData> list;

    private Context context;

    public SearchAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void notifyDataChange(List<SearchBaseData> list, boolean isSearch) {
        if (!isSearch) this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void clearList() {
        list.clear();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case SearchBaseData.ONE:
                return new SearchHotHolder(LayoutInflater.from(context).inflate(R.layout.item_search_one, parent, false));
            case SearchBaseData.TWO:
                return new SearchTypeHolder(LayoutInflater.from(context).inflate(R.layout.item_new_five, parent, false));

        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (list.get(position).getType()) {
            case SearchBaseData.ONE:
                SearchHotHolder hotHolder = (SearchHotHolder) holder;
                SearchNormalData normalData = (SearchNormalData) list.get(position);
                hotHolder.name.setText(normalData.getName());
                hotHolder.num.setText(String.valueOf(position));
                hotHolder.layout.setTag(normalData.getId() + "," + normalData.getGoWhere() + "," + normalData.getDirect_id());
                hotHolder.layout.setOnClickListener(this);
                break;
            case SearchBaseData.TWO:
                SearchTypeHolder typeHolder = (SearchTypeHolder) holder;
                SearchTypeData typeData = (SearchTypeData) list.get(position);
                typeHolder.type.setText(typeData.getName());
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

    @Override
    public void onClick(View v) {
        String tag = ((String) v.getTag());
        String[] split = tag.split(",");
        Bundle bundle = new Bundle();
        switch (Integer.parseInt(split[1])) {
            case 1:
                bundle.putInt("id", Integer.parseInt(split[0]));
                bundle.putString("direct", split[2]);
                ((SearchActivity) context).startActivity(MovieInfoActivity.class, bundle);
                break;
            case 2:
                bundle.putString("id", split[0]);
                ((SearchActivity) context).startActivity(BookInfoActivity.class, bundle);
                break;
            case 3:
                bundle.putString("id", split[0]);
                ((SearchActivity) context).startActivity(MusicInfoActivity.class, bundle);
                break;
        }

    }

    private static class SearchHotHolder extends RecyclerView.ViewHolder {

        private final TextView num;
        private final TextView name;
        private final LinearLayout layout;

        SearchHotHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            num = ((TextView) itemView.findViewById(R.id.tv_search_one));
            name = ((TextView) itemView.findViewById(R.id.tv_search_two));
            layout = ((LinearLayout) itemView.findViewById(R.id.ll_search_layout));
        }
    }

    private static class SearchTypeHolder extends RecyclerView.ViewHolder {
        private final TextView type;

        SearchTypeHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            type = (TextView) itemView.findViewById(R.id.tv_five);
        }
    }

}
