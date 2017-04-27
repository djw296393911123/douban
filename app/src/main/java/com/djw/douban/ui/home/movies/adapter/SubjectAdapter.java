package com.djw.douban.ui.home.movies.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.djw.douban.R;
import com.djw.douban.data.movies.PeopleTwo;
import com.djw.douban.ui.home.movies.activity.MovieInfoActivity;
import com.djw.douban.ui.home.movies.activity.PeopleActivity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by JasonDong
 * <p>
 * on 2017/4/20.
 */

class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.AlsoListHolder> implements View.OnClickListener {

    private List<PeopleTwo> list;

    private Context context;

    SubjectAdapter(List<PeopleTwo> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public AlsoListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AlsoListHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_people_item, parent, false));
    }

    @Override
    public void onBindViewHolder(AlsoListHolder holder, int position) {
        PeopleTwo peopleTwo = list.get(position);
        holder.grade.setText(peopleTwo.getGrade());
        holder.name.setText(peopleTwo.getName());
        Glide.with(context).load(peopleTwo.getUrl()).asBitmap().into(holder.head);
        holder.layout.setTag(peopleTwo.getId() + "," + peopleTwo.getDirect_id());
        holder.layout.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        String tag = (String) v.getTag();
        String[] split = tag.split(",");
        bundle.putInt("id", Integer.parseInt(split[0]));
        bundle.putString("direct", split[1]);
        ((PeopleActivity) context).startActivity(MovieInfoActivity.class, bundle);
    }

    class AlsoListHolder extends RecyclerView.ViewHolder {

        private final ImageView head;
        private final TextView name;
        private final TextView grade;
        private final LinearLayout layout;

        AlsoListHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            head = ((ImageView) itemView.findViewById(R.id.iv_like));
            name = ((TextView) itemView.findViewById(R.id.tv_like_name));
            grade = ((TextView) itemView.findViewById(R.id.tv_grade));
            layout = ((LinearLayout) itemView.findViewById(R.id.ll_content));
        }
    }
}
