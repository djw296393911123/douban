package com.djw.douban.ui.movies.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.azoft.carousellayoutmanager.CenterScrollListener;
import com.bumptech.glide.Glide;
import com.djw.douban.MainActivity;
import com.djw.douban.R;
import com.djw.douban.data.newmovies.MoviesEightItemData;
import com.djw.douban.data.newmovies.NewMovieNineItemData;
import com.djw.douban.data.newmovies.NewMovieOne;
import com.djw.douban.data.newmovies.NewMovieTen;
import com.djw.douban.data.newmovies.NewMoviesBaseData;
import com.djw.douban.data.newmovies.NewMoviesEight;
import com.djw.douban.data.newmovies.NewMoviesFive;
import com.djw.douban.data.newmovies.NewMoviesFour;
import com.djw.douban.data.newmovies.NewMoviesNine;
import com.djw.douban.data.newmovies.NewMoviesSeven;
import com.djw.douban.data.newmovies.NewMoviesSix;
import com.djw.douban.data.newmovies.NewMoviesThree;
import com.djw.douban.data.newmovies.NewMoviesTwo;
import com.djw.douban.data.newmovies.NewMoviewTwoItemData;
import com.djw.douban.ui.movies.activity.CommingSoonActivity;
import com.djw.douban.ui.movies.activity.HotActivity;
import com.djw.douban.ui.movies.activity.MovieInfoActivity;
import com.djw.douban.ui.movies.activity.NorthAmericaActivity;
import com.djw.douban.ui.movies.activity.Top250Activity;
import com.djw.douban.ui.movies.activity.TypeActivity;
import com.djw.douban.util.DividerGridItemDecoration;
import com.sunfusheng.marqueeview.MarqueeView;
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

public class NewMoviesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements OnBannerClickListener, View.OnClickListener, MarqueeView.OnItemClickListener {

    private List<NewMoviesBaseData> list;

    private Context context;

    public NewMoviesAdapter() {
        this.list = new ArrayList<>();
    }

    public void notifyDataChange(List<NewMoviesBaseData> list, boolean isLoadMore) {
        if (isLoadMore) {
            this.list.addAll(list);
            notifyItemRangeChanged(getItemCount() + 1, list.size());
        } else {
            this.list.clear();
            this.list.addAll(list);
            notifyDataSetChanged();
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        switch (viewType) {
            case NewMoviesBaseData.ONE:
                return new OneHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_one, parent, false));
            case NewMoviesBaseData.TWO:
                return new TwoHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_two_new, parent, false));
            case NewMoviesBaseData.THREE:
                return new ThreeHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_three, parent, false));
            case NewMoviesBaseData.FOUR:
                return new FourHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_fourr, parent, false));
            case NewMoviesBaseData.FIVE:
                return new FiveHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_five, parent, false));
            case NewMoviesBaseData.SIX:
                return new SixHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_six, parent, false));
            case NewMoviesBaseData.SEVEN:
                return new SevenHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_seven, parent, false));
            case NewMoviesBaseData.EIGHT:
                return new EightHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_eight, parent, false));
            case NewMoviesBaseData.NINE:
                return new NineHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_nine, parent, false));
            case NewMoviesBaseData.TEN:
                return new TenHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_nine, parent, false));

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
                twoHolder.loadData(two.getList());
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
                fourHolder.cardView.setTag(Integer.parseInt(four.getId()));
                fourHolder.cardView.setOnClickListener(this);
                fourHolder.direct.setText(four.getDirect());
                if (four.isGood()) fourHolder.tuijian.setVisibility(View.VISIBLE);
                else fourHolder.tuijian.setVisibility(View.GONE);
                break;
            case NewMoviesBaseData.FIVE:
                FiveHolder fiveHolder = (FiveHolder) holder;
                NewMoviesFive five = (NewMoviesFive) list.get(position);
                Glide.with(context).load(five.getImg()).asBitmap().into(fiveHolder.imageView);
                fiveHolder.textView.setText(five.getName());
                break;
            case NewMoviesBaseData.SIX:
                SixHolder sixeHolder = (SixHolder) holder;
                NewMoviesSix six = (NewMoviesSix) list.get(position);
                sixeHolder.textView.setText(six.getName());
                sixeHolder.layout.setOnClickListener(this);
                break;
            case NewMoviesBaseData.SEVEN:
                SevenHolder sevenHolder = (SevenHolder) holder;
                NewMoviesSeven seven = (NewMoviesSeven) list.get(position);
                sevenHolder.marqueeView.startWithList(seven.getTitles());
                sevenHolder.marqueeView.setOnItemClickListener(this);
                break;
            case NewMoviesBaseData.EIGHT:
                EightHolder eightHolder = (EightHolder) holder;
                NewMoviesEight eight = (NewMoviesEight) list.get(position);
                eightHolder.loadData(eight.getImgs());
                break;
            case NewMoviesBaseData.NINE:
                NineHolder nineHolder = (NineHolder) holder;
                NewMoviesNine nine = (NewMoviesNine) list.get(position);
                nineHolder.loadData(nine.getList());
                break;
            case NewMoviesBaseData.TEN:
                TenHolder tenHolder = (TenHolder) holder;
                NewMovieTen ten = (NewMovieTen) list.get(position);
                tenHolder.loadData(ten.getList());
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
                bundle.putInt("id", ((int) v.getTag()));
                ((MainActivity) context).startActivity(MovieInfoActivity.class, bundle);
                break;
            case R.id.ll_six_layout:
                ((MainActivity) context).startActivity(HotActivity.class);
                break;
        }
    }

    @Override
    public void onItemClick(int position, TextView textView) {
        switch (position) {
            case 0:
                Bundle zero = new Bundle();
                zero.putString("q", "经典");
                ((MainActivity) context).startActivity(TypeActivity.class, zero);
                break;
            case 1:
                ((MainActivity) context).startActivity(HotActivity.class);
                break;
            case 2:
                ((MainActivity) context).startActivity(Top250Activity.class);
                break;
            case 3:
                Bundle three = new Bundle();
                three.putString("q", "周星驰");
                ((MainActivity) context).startActivity(TypeActivity.class, three);
                break;
            case 4:
                ((MainActivity) context).startActivity(NorthAmericaActivity.class);
                break;
            case 5:
                ((MainActivity) context).startActivity(Top250Activity.class);
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

        NewMoviewTwoAdapter adapter;

        TwoHolder(View view) {
            super(view);
            AutoUtils.autoSize(view);
            RecyclerView recyclerView = ((RecyclerView) view.findViewById(R.id.rv_new_two));
            recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 4));
            adapter = new NewMoviewTwoAdapter(view.getContext());
            recyclerView.setAdapter(adapter);
        }

        void loadData(List<NewMoviewTwoItemData> list) {
            adapter.notifyDataChange(list);
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
        ImageView tuijian;
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
            tuijian = ((ImageView) view.findViewById(R.id.iv_four_tuijian));
        }
    }

    private static class FiveHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        FiveHolder(View view) {
            super(view);
            AutoUtils.autoSize(view);
            textView = (TextView) view.findViewById(R.id.tv_five);
            imageView = ((ImageView) view.findViewById(R.id.iv_five));
        }
    }

    private static class SixHolder extends RecyclerView.ViewHolder {
        LinearLayout layout;
        TextView textView;

        SixHolder(View view) {
            super(view);
            AutoUtils.autoSize(view);
            textView = (TextView) view.findViewById(R.id.tv_six_more);
            layout = ((LinearLayout) view.findViewById(R.id.ll_six_layout));
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

    private static class EightHolder extends RecyclerView.ViewHolder {


        MoviesEightAdapter adapter;

        EightHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            RecyclerView recyclerView = ((RecyclerView) itemView.findViewById(R.id.rv_eight));
            CarouselLayoutManager layoutManager = new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.addOnScrollListener(new CenterScrollListener());
            layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());
            adapter = new MoviesEightAdapter(itemView.getContext());
            recyclerView.setAdapter(adapter);
            recyclerView.scrollToPosition(1);
        }

        void loadData(List<MoviesEightItemData> list) {
            adapter.notifyDataChange(list);
        }

    }

    private static class NineHolder extends RecyclerView.ViewHolder {


        NewMovieNineAdapter adapter;

        NineHolder(View itemView) {
            super(itemView);
            Log.i("nine", "nine");
            AutoUtils.autoSize(itemView);
            RecyclerView recyclerView = ((RecyclerView) itemView.findViewById(R.id.rv_eight));
            GridLayoutManager gridLayoutManager = new GridLayoutManager(itemView.getContext(), 4);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.addItemDecoration(new DividerGridItemDecoration(itemView.getContext()));
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (position == 0 || position == 3)
                        return 2;
                    return 1;
                }
            });
            adapter = new NewMovieNineAdapter(itemView.getContext());
            recyclerView.setAdapter(adapter);
        }

        void loadData(List<NewMovieNineItemData> list) {
            adapter.notifyDataChange(list);
        }

    }

    private static class TenHolder extends RecyclerView.ViewHolder {

        private final NewMovieTenAdapter adapter;
        RecyclerView recyclerView;

        TenHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            recyclerView = ((RecyclerView) itemView.findViewById(R.id.rv_eight));
            GridLayoutManager manager = new GridLayoutManager(itemView.getContext(), 4);
            recyclerView.setLayoutManager(manager);
            recyclerView.addItemDecoration(new DividerGridItemDecoration(itemView.getContext()));
            adapter = new NewMovieTenAdapter(itemView.getContext());
            recyclerView.setAdapter(adapter);
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return position < 4 ? 2 : 1;
                }
            });
        }

        void loadData(List<NewMovieNineItemData> list) {
            adapter.notifyDataChange(list);
        }

    }


    private static class GlideImageLoader extends ImageLoader {

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
