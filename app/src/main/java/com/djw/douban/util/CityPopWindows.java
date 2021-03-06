package com.djw.douban.util;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.djw.douban.R;
import com.djw.douban.data.cloud.CityData;
import com.djw.douban.ui.cloud.adapter.CityAdapter;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;


/**
 * Created by JasonDong
 * <p>
 * on 2017/3/17.
 */

public abstract class CityPopWindows extends PopupWindow {

    public CityPopWindows(Context context, List<CityData> list) {
        super(context);
        initView(context, list);
    }

    private void initView(Context context, List<CityData> list) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_city, null);
        AutoUtils.autoSize(view);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_city);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new CityAdapter(list) {
            @Override
            public void onItemClick(String id, String title) {
                onItemClicks(id, title);
            }
        });
        this.setContentView(view);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setTouchable(true);
        ColorDrawable colorDrawable = new ColorDrawable(0xffffff);
        this.setBackgroundDrawable(colorDrawable);
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
            }
        });
    }

    public abstract void onItemClicks(String id, String title);

}
