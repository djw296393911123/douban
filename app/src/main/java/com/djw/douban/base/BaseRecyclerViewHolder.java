package com.djw.douban.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhy.autolayout.utils.AutoUtils;

/**
 * Created by JasonDong on 2017/5/24.
 */

public class BaseRecyclerViewHolder extends RecyclerView.ViewHolder {
    public BaseRecyclerViewHolder(View itemView) {
        super(itemView);
        AutoUtils.autoSize(itemView);
    }
}
