package com.djw.douban.ui.home.movies.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.djw.douban.R;
import com.djw.douban.data.movies.MoviesActorsData;
import com.djw.douban.data.movies.PeopleBaseData;
import com.djw.douban.data.movies.PeopleFour;
import com.djw.douban.data.movies.PeopleOne;
import com.djw.douban.data.movies.PeopleThree;
import com.djw.douban.data.movies.PeopleTwo;
import com.djw.douban.ui.home.movies.activity.MovieInfoActivity;
import com.djw.douban.ui.home.movies.activity.PeopleActivity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by JasonDong on 2017/4/7.
 */

public class PeopleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<PeopleBaseData> list;

    private Context context;

    public PeopleAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void notifyDataChange(MoviesActorsData data) {
        list.add(new PeopleOne(data.getAvatars().getLarge(), data.getName() + data.getName_en(), "性别：" + data.getGender() + "/出生地:" + data.getBorn_place()));
        list.add(new PeopleFour("参演作品"));
        List<MoviesActorsData.WorksBean> works = data.getWorks();
        for (int i = 0; i < works.size(); i++) {
            MoviesActorsData.WorksBean.SubjectBean subject = works.get(i).getSubject();
            List<MoviesActorsData.WorksBean.SubjectBean.CastsBean> casts = subject.getCasts();
            List<PeopleTwo> peopleTwos = new ArrayList<>();
            for (int j = 0; j < casts.size(); j++) {
                MoviesActorsData.WorksBean.SubjectBean.CastsBean castsBean = casts.get(j);
                peopleTwos.add(new PeopleTwo(castsBean.getName(), castsBean.getId(), castsBean.getAvatars() == null ? "" : castsBean.getAvatars().getLarge()));
            }
            list.add(new PeopleThree(subject.getImages().getLarge(), subject.getTitle(), subject.getId(), peopleTwos));
        }
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case PeopleBaseData.ONE:
                return new PeopleOneHolder(LayoutInflater.from(context).inflate(R.layout.item_people_one, parent, false));
            case PeopleBaseData.THREE:
                return new PeopleThreeHolder(LayoutInflater.from(context).inflate(R.layout.item_people_casts, parent, false));
            case PeopleBaseData.FOUR:
                return new PeopleFourHolder(LayoutInflater.from(context).inflate(R.layout.item_type_title, parent, false));
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
                threeHolder.people.setText(peopleThree.getName());
                Glide.with(context).load(peopleThree.getUrl()).asBitmap().into(threeHolder.image);
                threeHolder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                threeHolder.recyclerView.setAdapter(new PeopleCastsAdapter(peopleThree.getList(), context));
                threeHolder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("id", Integer.parseInt(peopleThree.getId()));
                        ((PeopleActivity) context).startActivity(MovieInfoActivity.class, bundle);
                    }
                });
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

    class PeopleOneHolder extends RecyclerView.ViewHolder {

        private final ImageView head;
        private final TextView name;
        private final TextView jianjie;

        public PeopleOneHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            head = ((ImageView) itemView.findViewById(R.id.iv_people_head));
            name = ((TextView) itemView.findViewById(R.id.tv_people_name));
            jianjie = ((TextView) itemView.findViewById(R.id.tv_jianjie));
        }
    }

    class PeopleTwoHolder extends RecyclerView.ViewHolder {


        public PeopleTwoHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
        }
    }

    class PeopleThreeHolder extends RecyclerView.ViewHolder {

        private final ImageView image;
        private final TextView people;
        private final CardView cardView;
        private final RecyclerView recyclerView;

        public PeopleThreeHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            image = ((ImageView) itemView.findViewById(R.id.iv_movie));
            people = ((TextView) itemView.findViewById(R.id.tv_people));
            cardView = ((CardView) itemView.findViewById(R.id.cv_item));
            recyclerView = ((RecyclerView) itemView.findViewById(R.id.rv_people_casts));
        }
    }

    class PeopleFourHolder extends RecyclerView.ViewHolder {

        private final TextView textview;

        public PeopleFourHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            textview = ((TextView) itemView.findViewById(R.id.tv_type));
        }
    }

}
