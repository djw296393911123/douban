package com.djw.douban.ui.home.movies.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.djw.douban.MainActivity;
import com.djw.douban.R;
import com.djw.douban.data.newmovies.NewMovieOne;
import com.djw.douban.data.newmovies.NewMoviesBaseData;
import com.djw.douban.data.newmovies.NewMoviesFive;
import com.djw.douban.data.newmovies.NewMoviesFour;
import com.djw.douban.data.newmovies.NewMoviesSix;
import com.djw.douban.data.newmovies.NewMoviesThree;
import com.djw.douban.data.newmovies.NewMoviesTwo;
import com.djw.douban.ui.home.movies.activity.CommingSoonActivity;
import com.djw.douban.ui.home.movies.activity.HotActivity;
import com.djw.douban.ui.home.movies.activity.MovieInfoActivity;
import com.djw.douban.ui.home.movies.activity.NorthAmericaActivity;
import com.djw.douban.ui.home.movies.activity.Top250Activity;
import com.djw.douban.ui.home.movies.activity.TypeActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.loader.ImageLoader;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JasonDong
 * <p>
 * on 2017/4/14.
 */

public class NewMoviesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements OnBannerClickListener, View.OnClickListener {

    private List<NewMoviesBaseData> list;

    private Context context;

    public NewMoviesAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void notifyDataChange(List<NewMoviesBaseData> list, boolean isLoadMore) {
        if (!isLoadMore) this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case NewMoviesBaseData.ONE:
                return new OneHolder(LayoutInflater.from(context).inflate(R.layout.item_new_one, parent, false));
            case NewMoviesBaseData.TWO:
                return new TwoHolder(LayoutInflater.from(context).inflate(R.layout.item_new_two, parent, false));
            case NewMoviesBaseData.THREE:
                return new ThreeHolder(LayoutInflater.from(context).inflate(R.layout.item_new_three, parent, false));
            case NewMoviesBaseData.FOUR:
                return new FourHolder(LayoutInflater.from(context).inflate(R.layout.item_new_fourr, parent, false));
            case NewMoviesBaseData.FIVE:
                return new FiveHolder(LayoutInflater.from(context).inflate(R.layout.item_new_five, parent, false));
            case NewMoviesBaseData.SIX:
                return new SixeHolder(LayoutInflater.from(context).inflate(R.layout.item_new_six, parent, false));

        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (list.get(position).getType()) {
            case NewMoviesBaseData.ONE:
                OneHolder oneHolder = (OneHolder) holder;
                NewMovieOne one = (NewMovieOne) list.get(position);
                Banner banner = oneHolder.banner;
                banner.setImageLoader(new GlideImageLoader());
                banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
                banner.setOffscreenPageLimit(1);
                banner.setOnBannerClickListener(this);
                banner.setBannerTitles(one.getTitles());
                banner.setImages(one.getUrls());
                banner.start();
                break;
            case NewMoviesBaseData.TWO:
                TwoHolder twoHolder = (TwoHolder) holder;
                NewMoviesTwo two = (NewMoviesTwo) list.get(position);
                twoHolder.ivTowOne.setImageResource(two.getUrl().get(0));
                twoHolder.ivTowOne.setOnClickListener(this);
                twoHolder.ivTowTwo.setImageResource(two.getUrl().get(1));
                twoHolder.ivTowTwo.setOnClickListener(this);
                twoHolder.ivTowThree.setImageResource(two.getUrl().get(2));
                twoHolder.ivTowThree.setOnClickListener(this);
                twoHolder.ivTowFour.setImageResource(two.getUrl().get(3));
                twoHolder.ivTowFour.setOnClickListener(this);
                twoHolder.tvTwoOne.setText(two.getName().get(0));
                twoHolder.tvTwoTwo.setText(two.getName().get(1));
                twoHolder.tvTwoThree.setText(two.getName().get(2));
                twoHolder.tvTwoFour.setText(two.getName().get(3));
                break;
            case NewMoviesBaseData.THREE:
                ThreeHolder threeHolder = (ThreeHolder) holder;
                NewMoviesThree three = (NewMoviesThree) list.get(position);
                Glide.with(context).load(three.getUrl().get(0)).into(threeHolder.ivThreeOne);
                Glide.with(context).load(three.getUrl().get(1)).into(threeHolder.ivThreeTwo);
                Glide.with(context).load(three.getUrl().get(2)).into(threeHolder.ivThreeThree);
                threeHolder.ivThreeOne.setOnClickListener(this);
                threeHolder.ivThreeTwo.setOnClickListener(this);
                threeHolder.ivThreeThree.setOnClickListener(this);
                break;
            case NewMoviesBaseData.FOUR:
                FourHolder fourHolder = (FourHolder) holder;
                NewMoviesFour four = (NewMoviesFour) list.get(position);
                Glide.with(context).load(four.getUrl()).asBitmap().into(fourHolder.ivFour);
                fourHolder.name.setText(four.getName());
                fourHolder.cardView.setTag(Integer.parseInt(four.getId()) + "," + four.getDirect_id());
                fourHolder.cardView.setOnClickListener(this);
                fourHolder.direct.setText(four.getDirect());
                break;
            case NewMoviesBaseData.FIVE:
                FiveHolder fiveHolder = (FiveHolder) holder;
                NewMoviesFive five = (NewMoviesFive) list.get(position);
                fiveHolder.textView.setText(five.getName());
                break;
            case NewMoviesBaseData.SIX:
                SixeHolder sixeHolder = (SixeHolder) holder;
                NewMoviesSix six = (NewMoviesSix) list.get(position);
                sixeHolder.textView.setText(six.getName());
                sixeHolder.layout.setOnClickListener(this);
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

    public int isSpan(int position) {
        return list.get(position).getType() == NewMoviesBaseData.FOUR ? 1 : 2;
    }

    @Override
    public void OnBannerClick(int position) {
        Bundle bundle = new Bundle();
        NewMovieOne newMovieOne = (NewMovieOne) list.get(0);
        bundle.putString("direct", newMovieOne.getDirect_id().get(position - 1));
        bundle.putInt("id", Integer.parseInt(newMovieOne.getIds().get(position - 1)));
        ((MainActivity) context).startActivity(MovieInfoActivity.class, bundle);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_three_one:
                ((MainActivity) context).startActivity(Top250Activity.class);
                break;
            case R.id.iv_three_two:
                ((MainActivity) context).startActivity(CommingSoonActivity.class);
                break;
            case R.id.iv_three_three:
                ((MainActivity) context).startActivity(NorthAmericaActivity.class);
                break;
            case R.id.cv_item:
                Bundle bundle = new Bundle();
                String tag = (String) v.getTag();
                String[] split = tag.split(",");
                bundle.putInt("id", Integer.parseInt(split[0]));
                bundle.putString("direct", split[1]);
                ((MainActivity) context).startActivity(MovieInfoActivity.class, bundle);
                break;
            case R.id.iv_tow_one:
                ((MainActivity) context).startActivity(HotActivity.class);
                break;
            case R.id.iv_tow_two:
                Bundle two = new Bundle();
                two.putString("q", "喜剧");
                ((MainActivity) context).startActivity(TypeActivity.class, two);
                break;
            case R.id.iv_tow_three:
                Bundle three = new Bundle();
                three.putString("q", "犯罪");
                ((MainActivity) context).startActivity(TypeActivity.class, three);
                break;
            case R.id.iv_tow_four:
                Bundle four = new Bundle();
                four.putString("q", "爱情");
                ((MainActivity) context).startActivity(TypeActivity.class, four);
                break;
            case R.id.ll_six_layout:
                ((MainActivity) context).startActivity(HotActivity.class);
                break;
        }
    }

    private static class OneHolder extends RecyclerView.ViewHolder {
        Banner banner;

        OneHolder(View view) {
            super(view);
            AutoUtils.autoSize(view);
            banner = (Banner) view.findViewById(R.id.banner_new);
        }
    }

    private static class TwoHolder extends RecyclerView.ViewHolder {
        ImageView ivTowOne;
        ImageView ivTowTwo;
        ImageView ivTowThree;
        ImageView ivTowFour;
        TextView tvTwoOne, tvTwoTwo, tvTwoThree, tvTwoFour;

        TwoHolder(View view) {
            super(view);
            AutoUtils.autoSize(view);
            ivTowOne = ((ImageView) view.findViewById(R.id.iv_tow_one));
            ivTowTwo = ((ImageView) view.findViewById(R.id.iv_tow_two));
            ivTowThree = ((ImageView) view.findViewById(R.id.iv_tow_three));
            ivTowFour = ((ImageView) view.findViewById(R.id.iv_tow_four));
            tvTwoOne = ((TextView) view.findViewById(R.id.tv_two_one));
            tvTwoTwo = ((TextView) view.findViewById(R.id.tv_two_two));
            tvTwoThree = ((TextView) view.findViewById(R.id.tv_two_three));
            tvTwoFour = ((TextView) view.findViewById(R.id.tv_two_four));
        }
    }

    private static class ThreeHolder extends RecyclerView.ViewHolder {
        ImageView ivThreeOne;
        ImageView ivThreeTwo;
        ImageView ivThreeThree;

        ThreeHolder(View view) {
            super(view);
            AutoUtils.autoSize(view);
            ivThreeOne = ((ImageView) view.findViewById(R.id.iv_three_one));
            ivThreeTwo = ((ImageView) view.findViewById(R.id.iv_three_two));
            ivThreeThree = ((ImageView) view.findViewById(R.id.iv_three_three));
        }
    }

    private static class FourHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView name;
        ImageView ivFour;
        TextView direct;

        FourHolder(View view) {
            super(view);
            AutoUtils.autoSize(view);
            ivFour = ((ImageView) view.findViewById(R.id.iv_four));
            name = ((TextView) view.findViewById(R.id.tv_foru_name));
            cardView = ((CardView) view.findViewById(R.id.cv_item));
            direct = ((TextView) view.findViewById(R.id.tv_singer));
        }
    }

    private static class FiveHolder extends RecyclerView.ViewHolder {
        TextView textView;

        FiveHolder(View view) {
            super(view);
            AutoUtils.autoSize(view);
            textView = (TextView) view.findViewById(R.id.tv_five);
        }
    }

    private static class SixeHolder extends RecyclerView.ViewHolder {
        LinearLayout layout;
        TextView textView;

        SixeHolder(View view) {
            super(view);
            AutoUtils.autoSize(view);
            textView = (TextView) view.findViewById(R.id.tv_six_more);
            layout = ((LinearLayout) view.findViewById(R.id.ll_six_layout));
        }
    }

    private class GlideImageLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            Glide.with(context).load(path).asBitmap().into(imageView);
        }

        @Override
        public ImageView createImageView(Context context) {
            return new ImageView(context);
        }
    }
}
