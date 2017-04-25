package com.djw.douban.util;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.djw.douban.R;
import com.zhy.autolayout.utils.AutoUtils;


/**
 * Created by JasonDong on 2017/3/17.
 */

public abstract class CustomPopWindows extends PopupWindow implements View.OnClickListener {

    private final Context context;
    private EditText etSearch;

    public CustomPopWindows(Context context) {
        super(context);
        this.context = context;
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pop_custiom, null);
        etSearch = (EditText) view.findViewById(R.id.et_custom);
        TextView sure = (TextView) view.findViewById(R.id.tv_sure);
        sure.setOnClickListener(this);
        AutoUtils.autoSize(view);
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

    public abstract void onItemClicks(String title);

    @Override
    public void onClick(View v) {
        if (etSearch.getText().toString().trim().equals(""))
            Toast.makeText(context, "不能为空", Toast.LENGTH_SHORT).show();
        else onItemClicks(etSearch.getText().toString().trim());
    }
}
