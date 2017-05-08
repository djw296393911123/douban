package com.djw.douban.ui.book.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.djw.douban.MainActivity;
import com.djw.douban.R;
import com.djw.douban.data.newbook.BookBannerData;
import com.djw.douban.data.newbook.BookBaseData;
import com.djw.douban.data.newbook.BookListData;
import com.djw.douban.ui.book.activity.BookInfoActivity;
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
 * on 2017/4/7.
 */

public class BookRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    private List<BookBaseData> list;

    public BookRecyclerAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void notifyDataChange(List<BookBaseData> list, boolean isLoadMore) {
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
        return viewType == BookBaseData.LIST ? new BookListHolder(LayoutInflater.from(context).inflate(R.layout.item_book, parent, false))
                : new BannerHolder(LayoutInflater.from(context).inflate(R.layout.item_header, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (list.get(position).getType()) {
            case BookBaseData.LIST:
                BookListHolder bookListHolder = (BookListHolder) holder;
                final BookListData listData = (BookListData) list.get(position);
                Glide.with(context).load(listData.getImg()).asBitmap().into(bookListHolder.image);
                bookListHolder.name.setText(listData.getName());
                bookListHolder.grade.setText(String.valueOf(listData.getGrade()));
                bookListHolder.ratingBar.setRating(((float) (Double.parseDouble(listData.getGrade()) / 2)));
                bookListHolder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString("id", listData.getId());
                        ((MainActivity) context).startActivity(BookInfoActivity.class, bundle);
                    }
                });
                break;
            case BookBaseData.BANNER:
                final BookBannerData bannerData = (BookBannerData) list.get(position);
                Banner banner = ((BannerHolder) holder).banner;
                banner.setImageLoader(new GlideImageLoader());
                banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
                banner.setOffscreenPageLimit(1);
                banner.setOnBannerClickListener(new OnBannerClickListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        Bundle bundle = new Bundle();
                        bundle.putString("id", bannerData.getId().get(position - 1));
                        ((MainActivity) context).startActivity(BookInfoActivity.class, bundle);
                    }
                });
                banner.setBannerTitles(bannerData.getTitle());
                banner.setImages(bannerData.getImg());
                banner.start();
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

    private static class BookListHolder extends RecyclerView.ViewHolder {

        private final ImageView image;
        private final TextView name;
        private final TextView grade;
        private final CardView cardView;
        private final RatingBar ratingBar;

        BookListHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            cardView = ((CardView) itemView.findViewById(R.id.cv_item));
            image = ((ImageView) itemView.findViewById(R.id.iv_movie));
            name = ((TextView) itemView.findViewById(R.id.tv_name));
            grade = ((TextView) itemView.findViewById(R.id.tv_grade));
            ratingBar = ((RatingBar) itemView.findViewById(R.id.rb_movies));
        }
    }

    private static class BannerHolder extends RecyclerView.ViewHolder {

        private final Banner banner;

        BannerHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            banner = ((Banner) itemView.findViewById(R.id.banner));
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
