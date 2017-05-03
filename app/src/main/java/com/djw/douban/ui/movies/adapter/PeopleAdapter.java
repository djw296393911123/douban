package com.djw.douban.ui.movies.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.djw.douban.R;
import com.djw.douban.data.movies.PeopleBaseData;
import com.djw.douban.data.movies.PeopleFour;
import com.djw.douban.data.movies.PeopleOne;
import com.djw.douban.data.movies.PeopleThree;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by JasonDong
 * <p>
 * on 2017/4/7.
 */

public class PeopleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<PeopleBaseData> list;

    private Context context;

    public PeopleAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void notifyDataChange(List<PeopleBaseData> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case PeopleBaseData.ONE:
                return new PeopleOneHolder(LayoutInflater.from(context).inflate(R.layout.item_people_one, parent, false));
            case PeopleBaseData.THREE:
                return new PeopleThreeHolder(LayoutInflater.from(context).inflate(R.layout.item_people_list, parent, false));
            case PeopleBaseData.FOUR:
                return new PeopleFourHolder(LayoutInflater.from(context).inflate(R.layout.item_people_type, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (list.get(position).getType()) {
            case PeopleBaseData.ONE:
                PeopleOneHolder oneHolder = (PeopleOneHolder) holder;
                PeopleOne peopleOne = (PeopleOne) list.get(position);
                oneHolder.jianjie.setText(peopleOne.getJianjie());
                oneHolder.name.setText(peopleOne.getName());
                Glide.with(context).load(peopleOne.getUrl()).bitmapTransform(new RoundedCornersTransformation(context, 5, 5)).into(oneHolder.head);
                break;
            case PeopleBaseData.THREE:
                PeopleThreeHolder threeHolder = (PeopleThreeHolder) holder;
                final PeopleThree peopleThree = (PeopleThree) list.get(position);
                threeHolder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                SubjectAdapter adapter = new SubjectAdapter(peopleThree.getList(), context);
                threeHolder.recyclerView.setAdapter(adapter);
                break;
            case PeopleBaseData.FOUR:
                PeopleFourHolder fourHolder = (PeopleFourHolder) holder;
                PeopleFour peopleFour = (PeopleFour) list.get(position);
                fourHolder.textview.setText(peopleFour.getTitle());
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private static class PeopleOneHolder extends RecyclerView.ViewHolder {

        private final ImageView head;
        private final TextView name;
        private final TextView jianjie;

        PeopleOneHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            head = ((ImageView) itemView.findViewById(R.id.iv_people_head));
            name = ((TextView) itemView.findViewById(R.id.tv_people_name));
            jianjie = ((TextView) itemView.findViewById(R.id.tv_jianjie));
        }
    }

    private static class PeopleThreeHolder extends RecyclerView.ViewHolder {

        private final RecyclerView recyclerView;

        PeopleThreeHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            recyclerView = ((RecyclerView) itemView.findViewById(R.id.rv_music_content));
        }
    }

    private static class PeopleFourHolder extends RecyclerView.ViewHolder {

        private final TextView textview;

        PeopleFourHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            textview = ((TextView) itemView.findViewById(R.id.tv_type));
        }
    }

}
