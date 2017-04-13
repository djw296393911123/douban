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
import com.djw.douban.data.movies.MoviesInfoBaseData;
import com.djw.douban.data.movies.MoviesInfoData;
import com.djw.douban.data.movies.MoviesInfoType;
import com.djw.douban.data.movies.MoviesPeople;
import com.djw.douban.data.movies.MoviesTextData;
import com.djw.douban.ui.home.movies.activity.MovieInfoActivity;
import com.djw.douban.ui.home.movies.activity.PeopleActivity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JasonDong on 2017/4/7.
 */

public class MoviesInfoAapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MoviesInfoBaseData> list;

    private Context context;

    public MoviesInfoAapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void notifyDataChange(MoviesInfoData moviesInfoData) {
        list.add(new MoviesInfoType("简介"));
        list.add(new MoviesTextData(moviesInfoData.getSummary()));
        list.add(new MoviesInfoType("导演"));
        List<MoviesInfoData.DirectorsBean> directors = moviesInfoData.getDirectors();
        for (int i = 0; i < directors.size(); i++) {
            MoviesInfoData.DirectorsBean directorsBean = directors.get(i);
            list.add(new MoviesPeople(directorsBean.getAvatars().getLarge(), directorsBean.getName(), Integer.parseInt(directorsBean.getId())));
        }
        list.add(new MoviesInfoType("主演"));
        List<MoviesInfoData.CastsBean> casts = moviesInfoData.getCasts();
        for (int i = 0; i < casts.size(); i++) {
            MoviesInfoData.CastsBean castsBean = casts.get(i);
            list.add(new MoviesPeople(castsBean.getAvatars().getLarge(), castsBean.getName(), Integer.parseInt(castsBean.getId())));
        }

        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case MoviesInfoBaseData.PEOPLE_TYPE:
                return new MoviesPeopleHolder(LayoutInflater.from(context).inflate(R.layout.item_movie_casts, parent, false));
            case MoviesInfoBaseData.TEXT_TYPE:
                return new MoviesTextHolder(LayoutInflater.from(context).inflate(R.layout.item_movies_content, parent, false));
            case MoviesInfoBaseData.TYPE_TYPE:
                return new MoviesTypeHolder(LayoutInflater.from(context).inflate(R.layout.item_type_title, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (list.get(position).getType()) {
            case MoviesInfoBaseData.PEOPLE_TYPE:
                MoviesPeopleHolder peopleHolder = (MoviesPeopleHolder) holder;
                final MoviesPeople moviesPeople = (MoviesPeople) list.get(position);
                peopleHolder.name.setText(moviesPeople.getName());
                Glide.with(context).load(moviesPeople.getUrl()).asBitmap().into(peopleHolder.head);
                peopleHolder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("id", moviesPeople.getId());
                        ((MovieInfoActivity) context).startActivity(PeopleActivity.class, bundle);
                    }
                });
                break;
            case MoviesInfoBaseData.TEXT_TYPE:
                MoviesTextHolder textHolder = (MoviesTextHolder) holder;
                MoviesTextData moviesTextData = (MoviesTextData) list.get(position);
                textHolder.textview.setText(moviesTextData.getContent());
                break;
            case MoviesInfoBaseData.TYPE_TYPE:
                MoviesTypeHolder typeHolder = (MoviesTypeHolder) holder;
                MoviesInfoType moviesInfoType = (MoviesInfoType) list.get(position);
                typeHolder.textview.setText(moviesInfoType.getTitle());
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

    class MoviesTextHolder extends RecyclerView.ViewHolder {

        private final TextView textview;

        public MoviesTextHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            textview = ((TextView) itemView.findViewById(R.id.tv_info_content));
        }
    }

    class MoviesTypeHolder extends RecyclerView.ViewHolder {

        private final TextView textview;

        public MoviesTypeHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            textview = ((TextView) itemView.findViewById(R.id.tv_type));
        }
    }

    class MoviesPeopleHolder extends RecyclerView.ViewHolder {


        private final ImageView head;
        private final TextView people;
        private final TextView name;
        private final CardView cardView;

        public MoviesPeopleHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            cardView = ((CardView) itemView.findViewById(R.id.cv_item));
            head = ((ImageView) itemView.findViewById(R.id.iv_movie));
            people = ((TextView) itemView.findViewById(R.id.tv_people));
            name = ((TextView) itemView.findViewById(R.id.tv_name));
        }
    }

}
