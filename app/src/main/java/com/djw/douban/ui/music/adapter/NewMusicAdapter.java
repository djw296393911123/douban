package com.djw.douban.ui.music.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.azoft.carousellayoutmanager.CenterScrollListener;
import com.bumptech.glide.Glide;
import com.djw.douban.MainActivity;
import com.djw.douban.R;
import com.djw.douban.data.newmusic.MusicBaseData;
import com.djw.douban.data.newmusic.MusicChooseData;
import com.djw.douban.data.newmusic.MusicContentBaseData;
import com.djw.douban.data.newmusic.MusicContentData;
import com.djw.douban.data.newmusic.MusicLikeData;
import com.djw.douban.data.newmusic.MusicMVData;
import com.djw.douban.data.newmusic.MusicNewFiveData;
import com.djw.douban.data.newmusic.MusicNoMoreData;
import com.djw.douban.data.newmusic.MusicTypeData;
import com.djw.douban.ui.music.activity.MoreMusicActivity;
import com.djw.douban.ui.music.activity.MusicInfoActivity;
import com.sunfusheng.marqueeview.MarqueeView;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JasonDong
 * <p>
 * on 2017/4/18.
 */

public class NewMusicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private List<MusicBaseData> list;
    private Context context;

    public NewMusicAdapter() {
        this.list = new ArrayList<>();
    }

    public void notifyDataChange(List<MusicBaseData> list) {
        this.list.addAll(list);
        if (getItemCount() == 0) notifyItemRangeChanged(getItemCount(), list.size());
        else notifyItemRangeChanged(getItemCount() + 1, list.size());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        switch (viewType) {
            case MusicBaseData.ONE:
                return new NewMusicTypeHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_music_type, parent, false));
            case MusicBaseData.TWO:
                return new NewMusicContentHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_music_content, parent, false));
            case MusicBaseData.THREE:
                return new NewMusicChooseHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_music_content, parent, false));
            case MusicBaseData.FOUR:
                return new FourHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_four, parent, false));
            case MusicBaseData.FIVE:
                return new FiveHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_six, parent, false));
            case MusicBaseData.SIX:
                return new SixHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_music_nomore, parent, false));
            case MusicBaseData.SEVEN:
                return new SevenHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_music_seven_item, parent, false));

        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (list.get(position).getType()) {
            case MusicBaseData.ONE:
                NewMusicTypeHolder typeHolder = (NewMusicTypeHolder) holder;
                final MusicTypeData typeData = (MusicTypeData) list.get(position);
                typeHolder.type.setText(typeData.getName());
                typeHolder.more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString("tag", typeData.getName());
                        ((MainActivity) context).startActivity(MoreMusicActivity.class, bundle);
                    }
                });
                break;
            case MusicBaseData.TWO:
                NewMusicContentHolder contentHolder = (NewMusicContentHolder) holder;
                MusicContentData contentData = (MusicContentData) list.get(position);
                contentHolder.loadData(contentData.getList());
                break;
            case MusicBaseData.THREE:
                NewMusicChooseHolder chooseHolder = (NewMusicChooseHolder) holder;
                MusicChooseData chooseData = (MusicChooseData) list.get(position);
                chooseHolder.loadData(chooseData.getName());
                break;
            case MusicBaseData.FOUR:
                FourHolder fourHolder = (FourHolder) holder;
                MusicLikeData four = (MusicLikeData) list.get(position);
                Glide.with(context).load(four.getUrl()).asBitmap().into(fourHolder.ivFour);
                fourHolder.grade.setText(four.getGrade());
                fourHolder.ratingBar.setRating(((float) (Double.parseDouble(four.getGrade()) / 2)));
                fourHolder.name.setText(four.getName());
                fourHolder.cardView.setTag(four.getId());
                fourHolder.cardView.setOnClickListener(this);
                fourHolder.singer.setText(four.getSinger());
                break;
            case MusicBaseData.FIVE:
                FiveHolder fiveHolder = (FiveHolder) holder;
                MusicNewFiveData fiveData = (MusicNewFiveData) list.get(position);
                fiveHolder.textView.setText(fiveData.getName());
                fiveHolder.layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString("tag", "喜欢");
                        ((MainActivity) context).startActivity(MoreMusicActivity.class, bundle);
                    }
                });
                break;
            case MusicBaseData.SIX:
                ((SixHolder) holder).textView.setText(((MusicNoMoreData) list.get(position)).getTitle());
                break;
            case MusicBaseData.SEVEN:
                SevenHolder sevenHolder = (SevenHolder) holder;
                final MusicMVData seven = (MusicMVData) list.get(position);
                sevenHolder.marqueeView.startWithList(seven.getTitles());
                sevenHolder.marqueeView.setTag(seven.getTypes());
                sevenHolder.marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position, TextView textView) {
                        Bundle bundle = new Bundle();
                        bundle.putString("tag", seven.getTypes().get(position));
                        ((MainActivity) context).startActivity(MoreMusicActivity.class, bundle);
                    }
                });
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public int isSpan(int position) {
        return list.get(position).getType() == MusicBaseData.FOUR ? 1 : 3;
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        bundle.putString("id", ((String) v.getTag()));
        ((MainActivity) context).startActivity(MusicInfoActivity.class, bundle);
    }

    private static class NewMusicTypeHolder extends RecyclerView.ViewHolder {

        private final TextView type;
        private final TextView more;

        NewMusicTypeHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            type = ((TextView) itemView.findViewById(R.id.tv_music_type));
            more = ((TextView) itemView.findViewById(R.id.tv_music_more));
        }
    }

    private static class NewMusicContentHolder extends RecyclerView.ViewHolder {

        private final MusicContentAdapter adapter;

        NewMusicContentHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            RecyclerView recyclerView = ((RecyclerView) itemView.findViewById(R.id.rv_music_content));
            CarouselLayoutManager layoutManager = new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.addOnScrollListener(new CenterScrollListener());
            layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());
            adapter = new MusicContentAdapter(itemView.getContext());
            recyclerView.setAdapter(adapter);
            recyclerView.scrollToPosition(1);
        }

        void loadData(List<MusicContentBaseData> list) {
            adapter.notifyDataChange(list);
        }

    }

    private static class NewMusicChooseHolder extends RecyclerView.ViewHolder {


        private final MusicChooseAdapter adapter;

        NewMusicChooseHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            RecyclerView recyclerView = ((RecyclerView) itemView.findViewById(R.id.rv_music_content));
            recyclerView.setLayoutManager(new GridLayoutManager(itemView.getContext(), 4));
            adapter = new MusicChooseAdapter(itemView.getContext());
            recyclerView.setAdapter(adapter);
        }

        void loadData(List<String> list) {
            adapter.notifyDataChange(list);
        }

    }

    private static class FourHolder extends RecyclerView.ViewHolder {
        private final TextView singer;
        CardView cardView;
        TextView name;
        ImageView ivFour;
        RatingBar ratingBar;
        TextView grade;

        FourHolder(View view) {
            super(view);
            AutoUtils.autoSize(view);
            ivFour = ((ImageView) view.findViewById(R.id.iv_four));
            name = ((TextView) view.findViewById(R.id.tv_foru_name));
            ratingBar = ((RatingBar) view.findViewById(R.id.rb_four));
            grade = ((TextView) view.findViewById(R.id.tv_four_grade));
            cardView = ((CardView) view.findViewById(R.id.cv_item));
            singer = ((TextView) view.findViewById(R.id.tv_singer));
        }
    }

    private static class FiveHolder extends RecyclerView.ViewHolder {
        LinearLayout layout;
        TextView textView;

        FiveHolder(View view) {
            super(view);
            AutoUtils.autoSize(view);
            textView = (TextView) view.findViewById(R.id.tv_six_more);
            layout = ((LinearLayout) view.findViewById(R.id.ll_six_layout));
        }
    }

    private static class SixHolder extends RecyclerView.ViewHolder {
        TextView textView;

        SixHolder(View view) {
            super(view);
            AutoUtils.autoSize(view);
            textView = (TextView) view.findViewById(R.id.tv_music_type);
        }
    }

    private static class SevenHolder extends RecyclerView.ViewHolder {

        MarqueeView marqueeView;

        SevenHolder(View view) {
            super(view);
            AutoUtils.autoSize(view);
            marqueeView = ((MarqueeView) view.findViewById(R.id.mv_tuijian));
        }
    }

}
