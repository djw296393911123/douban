package com.djw.douban.ui.home.movies.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.djw.douban.MainActivity;
import com.djw.douban.R;
import com.djw.douban.data.movies.MoviesHomeData;
import com.djw.douban.data.movies.MoviesOne;
import com.djw.douban.data.movies.MoviesThree;
import com.djw.douban.data.movies.MoviesTwo;
import com.djw.douban.ui.home.movies.activity.MoreMovieActivity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JasonDong on 2017/4/10.
 */

public class HomeRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MoviesHomeData> list;

    private Context context;

    public HomeRecycleAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void notifyDataChange(List<MoviesHomeData> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case MoviesHomeData.ONE:
                return new OneHolder(LayoutInflater.from(context).inflate(R.layout.item_home_one, parent, false));
            case MoviesHomeData.TWO:
                return new TwoHolder(LayoutInflater.from(context).inflate(R.layout.item_home_two, parent, false));
            case MoviesHomeData.THREE:
                return new ThreeHolder(LayoutInflater.from(context).inflate(R.layout.item_home_one, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        switch (list.get(position).getType()) {
            case MoviesHomeData.ONE:
                OneHolder oneHolder = (OneHolder) holder;
                MoviesOne moviesOne = (MoviesOne) list.get(position);
                oneHolder.type.setText(moviesOne.getTitle());
                oneHolder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                MoviesRecyclerAdapter adapter = new MoviesRecyclerAdapter(context);
                oneHolder.recyclerView.setAdapter(adapter);
                adapter.notifyDataChange(moviesOne.getList(), false);
                ((OneHolder) holder).more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("type", position + 1);
                        ((MainActivity) context).startActivity(MoreMovieActivity.class, bundle);
                    }
                });
                break;
            case MoviesHomeData.TWO:
                TwoHolder twoHolder = (TwoHolder) holder;
                MoviesTwo moviesTwo = (MoviesTwo) list.get(position);
                ((TwoHolder) holder).title.setText(moviesTwo.getTitle());
                twoHolder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                twoHolder.recyclerView.setAdapter(new HomeTwoAdapter(moviesTwo.getList(), context));
                break;
            case MoviesHomeData.THREE:
                ThreeHolder threeHolder = (ThreeHolder) holder;
                MoviesThree moviesThree = (MoviesThree) list.get(position);
                threeHolder.type.setText(moviesThree.getTitle());
                threeHolder.recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
                MoviesThreeAdapter threeAdapter = new MoviesThreeAdapter(context);
                threeHolder.recyclerView.setAdapter(threeAdapter);
                threeAdapter.notifyDataChange(moviesThree.getList(), false);
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

    class OneHolder extends RecyclerView.ViewHolder {

        private final TextView type;
        private final RecyclerView recyclerView;
        private final TextView more;

        public OneHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            type = ((TextView) itemView.findViewById(R.id.tv_home_type_title));
            recyclerView = ((RecyclerView) itemView.findViewById(R.id.rv_home));
            more = ((TextView) itemView.findViewById(R.id.tv_home_more));
        }
    }

    class TwoHolder extends RecyclerView.ViewHolder {

        private final RecyclerView recyclerView;
        private final TextView title;

        public TwoHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            recyclerView = ((RecyclerView) itemView.findViewById(R.id.rv_two));
            title = ((TextView) itemView.findViewById(R.id.tv_home_type_title));
        }
    }

    class ThreeHolder extends RecyclerView.ViewHolder {

        private final TextView type;
        private final RecyclerView recyclerView;
        private final TextView more;

        public ThreeHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            type = ((TextView) itemView.findViewById(R.id.tv_home_type_title));
            recyclerView = ((RecyclerView) itemView.findViewById(R.id.rv_home));
            more = ((TextView) itemView.findViewById(R.id.tv_home_more));
        }
    }

}
