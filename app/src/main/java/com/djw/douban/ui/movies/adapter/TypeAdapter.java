package com.djw.douban.ui.movies.adapter;

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
import com.djw.douban.data.movies.TypeData;
import com.djw.douban.ui.movies.activity.MovieInfoActivity;
import com.djw.douban.ui.movies.activity.TypeActivity;
import com.ramotion.foldingcell.FoldingCell;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JasonDong
 * <p>
 * on 2017/4/10.
 */

public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.TypeHolder> {

    private List<TypeData.SubjectsBean> list;

    private Context context;

    private HashSet<Integer> unfoldedIndexes = new HashSet<>();

    public TypeAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void notifyDataChange(List<TypeData.SubjectsBean> list, boolean isLoadMore) {
        if (isLoadMore) {
            this.list.addAll(list);
            notifyItemRangeChanged(getItemCount() + 1, list.size());
        } else {
            this.list.clear();
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }

    public List<TypeData.SubjectsBean> getList() {
        return this.list;
    }

    @Override
    public TypeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TypeHolder(LayoutInflater.from(context).inflate(R.layout.item_type_movies, parent, false));
    }

    @Override
    public void onBindViewHolder(final TypeHolder holder, int position) {
        final TypeData.SubjectsBean subjectBean = list.get(position);
        if (unfoldedIndexes.contains(position)) {
            holder.foldingCell.unfold(true);
        } else {
            holder.foldingCell.fold(true);
        }
        Glide.with(context).load(subjectBean.getImages().getLarge()).asBitmap().into(holder.ivHotHead);
        Glide.with(context).load(subjectBean.getImages().getLarge()).asBitmap().into(holder.large);
        holder.tvHotTitle.setText(subjectBean.getTitle());
        holder.tvHotGrade.setText(String.valueOf(subjectBean.getRating().getAverage()));
        holder.rbHot.setRating(((float) (subjectBean.getRating().getAverage() / 2)));
        holder.tvHotNum.setText(String.valueOf(subjectBean.getCollect_count()));
        String daoyan = "导演 ：";
        List<TypeData.SubjectsBean.DirectorsBean> directors = subjectBean.getDirectors();
        if (directors.size() > 0) {
            for (int i = 0; i < directors.size(); i++) {
                daoyan = daoyan + directors.get(i).getName() + "/";
            }
        } else daoyan = daoyan + "JasonDong";

        holder.tvHotDirect.setText(daoyan.substring(0, daoyan.length() - 1));
        String actor = "演员 ：";
        List<TypeData.SubjectsBean.CastsBean> casts = subjectBean.getCasts();
        for (int i = 0; i < casts.size(); i++) {
            actor = actor + casts.get(i).getName() + "/";
        }
        holder.tvHotCast.setText(actor.substring(0, actor.length() - 1));
        holder.llLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", Integer.parseInt(subjectBean.getId()));
                ((TypeActivity) context).startActivity(MovieInfoActivity.class, bundle);
            }
        });
        holder.ivHotHead.setTag(position);
        holder.ivHotHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.foldingCell.toggle(false);
                registerToggle(((int) v.getTag()));
            }
        });
        holder.large.setTag(position);
        holder.large.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.foldingCell.toggle(false);
                registerToggle(((int) v.getTag()));
            }
        });
    }

    private void registerToggle(int position) {
        if (unfoldedIndexes.contains(position))
            registerFold(position);
        else
            registerUnfold(position);
    }

    private void registerFold(int position) {
        unfoldedIndexes.remove(position);
    }

    private void registerUnfold(int position) {
        unfoldedIndexes.add(position);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    static class TypeHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_hot_head)
        ImageView ivHotHead;
        @BindView(R.id.tv_hot_title)
        TextView tvHotTitle;
        @BindView(R.id.rb_hot)
        RatingBar rbHot;
        @BindView(R.id.tv_hot_grade)
        TextView tvHotGrade;
        @BindView(R.id.tv_hot_direct)
        TextView tvHotDirect;
        @BindView(R.id.tv_hot_cast)
        TextView tvHotCast;
        @BindView(R.id.tv_hot_num)
        TextView tvHotNum;
        @BindView(R.id.ll_hot)
        LinearLayout llLayout;
        @BindView(R.id.iv_large)
        ImageView large;
        @BindView(R.id.folding_cell)
        FoldingCell foldingCell;

        TypeHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            AutoUtils.autoSize(itemView);

        }
    }
}
