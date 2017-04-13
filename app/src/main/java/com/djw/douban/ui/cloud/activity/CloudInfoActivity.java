package com.djw.douban.ui.cloud.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.TextView;

import com.djw.douban.R;
import com.djw.douban.base.SimpleActivity;

public class CloudInfoActivity extends SimpleActivity {

    private WebView webView;
    public final static String CSS_STYLE = "<style>* {font-size:16px;line-height:20px;} p {color:#aaa;  padding:3px;} a {color:#3E62A6;} img {max-width:310px;}pre {font-size:9pt;line-height:12pt;font-family:Courier New,Arial;border:1px solid #ddd;border-left:5px solid #6CE26C;background:#f6f6f6;padding:5px;}</style>";
    private Toolbar toolbar;
    private TextView title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud_info);

    }

    @Override
    public void initView() {
        webView = (WebView) findViewById(R.id.wb_cloud);
        toolbar = (Toolbar) findViewById(R.id.tl_base);
        title = (TextView) findViewById(R.id.tv_toolbar_title);
    }

    @Override
    public void doBusiness() {
        toolbar.setTitle("");
        title.setText(getIntent().getExtras().getString("title"));
        webView.loadData(CSS_STYLE + getIntent().getExtras().getString("content"), "text/html; charset=utf-8", "utf-8");
    }
}
