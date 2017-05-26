package com.djw.douban.ui.girl.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.djw.douban.R;
import com.djw.douban.data.girl.GirlBaseData;
import com.djw.douban.data.girl.GirlNormalData;
import com.djw.douban.ui.girl.activity.GirlActivity;
import com.djw.douban.ui.girl.activity.GirlLargeActivity;
import com.djw.douban.util.EasyTransition;
import com.djw.douban.util.EasyTransitionOptions;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JasonDong
 * <p>
 * on 2017/5/26.
 */

public abstract class GirlAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<GirlBaseData> list;

    private Context context;

    protected GirlAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void notifyDataChange(List<GirlBaseData> list, boolean isLoadMore) {
        if (isLoadMore) {
            this.list.remove(this.list.size() - 1);
            this.list.addAll(list);
            notifyDataSetChanged();
        } else {
            this.list.clear();
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case GirlBaseData.NORMAL:
                return new GirlHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_girls, parent, false));
            case GirlBaseData.MORE:
                return new MoreHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_girls, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        switch (list.get(position).getType()) {
            case GirlBaseData.NORMAL:
                final GirlHolder girlHolder = (GirlHolder) holder;
                final GirlNormalData normalData = (GirlNormalData) list.get(position);
                Glide.with(context).load(normalData.getUrl()).asBitmap().into(new SimpleTarget<Bitmap>() {

                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        Bitmap reflectedImage = createReflectedImage(resource);
                        girlHolder.img.setImageBitmap(reflectedImage);
                    }

                });
                girlHolder.img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, GirlLargeActivity.class);
                        Bundle extras = new Bundle();
                        extras.putString("url", normalData.getUrl());
                        intent.putExtras(extras);
                        EasyTransitionOptions options = EasyTransitionOptions.makeTransitionOptions(((GirlActivity) context), girlHolder.img);
                        EasyTransition.startActivity(intent, options);
                    }
                });
                break;
            case GirlBaseData.MORE:
                MoreHolder moreHolder = (MoreHolder) holder;
                Glide.with(context).load(R.mipmap.look_more).into(moreHolder.img);
                moreHolder.img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loadMore();
                    }
                });
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

    private static class GirlHolder extends RecyclerView.ViewHolder {

        private final ImageView img;

        GirlHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            img = ((ImageView) itemView.findViewById(R.id.iv_img));
        }
    }

    private static class MoreHolder extends RecyclerView.ViewHolder {

        private final ImageView img;

        MoreHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            img = ((ImageView) itemView.findViewById(R.id.iv_img));
            img.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }
    }

    private Bitmap createReflectedImage(Bitmap originalImage) {

        final int reflectionGap = 4;

        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        Matrix matrix = new Matrix();
        matrix.preScale(1, -1);

        Bitmap reflectionImage = Bitmap.createBitmap(originalImage, 0,
                height / 2, width, height / 2, matrix, false);

        Bitmap bitmapWithReflection = Bitmap.createBitmap(width,
                (height + height / 2), Bitmap.Config.RGB_565);

        Canvas canvas = new Canvas(bitmapWithReflection);

        canvas.drawBitmap(originalImage, 0, 0, null);

        Paint defaultPaint = new Paint();
        canvas.drawRect(0, height, width, height + reflectionGap, defaultPaint);

        canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);

        Paint paint = new Paint();
        LinearGradient shader = new LinearGradient(0,
                originalImage.getHeight(), 0, bitmapWithReflection.getHeight()
                + reflectionGap, 0x70ffffff, 0x00ffffff,
                Shader.TileMode.MIRROR);

        paint.setShader(shader);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

        canvas.drawRect(0, height, width, bitmapWithReflection.getHeight()
                + reflectionGap, paint);

        return bitmapWithReflection;
    }

    public abstract void loadMore();

}
