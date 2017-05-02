package com.djw.douban.ui.home.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.djw.douban.R;
import com.djw.douban.base.RxActivity;
import com.djw.douban.data.ParamsData;
import com.djw.douban.data.search.SearchBaseData;
import com.djw.douban.ui.home.adapter.SearchAdapter;
import com.djw.douban.ui.home.contract.SearchContract;
import com.djw.douban.ui.home.presenter.SearchPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchActivity extends RxActivity<SearchPresenter> implements SearchContract.View, TextWatcher {

    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.rv_search)
    RecyclerView rvSearch;
    private SearchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showHotSearch(List<SearchBaseData> list) {
        adapter.notifyDataChange(list, false);
    }

    @Override
    public void showSearchData(List<SearchBaseData> list) {
        adapter.notifyDataChange(list, true);
    }

    @Override
    public void initView() {
        etSearch.addTextChangedListener(this);
        rvSearch.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SearchAdapter(this);
        rvSearch.setAdapter(adapter);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void inject() {
        getActivityComponent().inject(this);
        mPresenter.attachView(this);
        mPresenter.getHotSearch(ParamsData.START, ParamsData.COUNT);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (etSearch.getText().toString().trim().equals("")) {
            mPresenter.getHotSearch(ParamsData.START, ParamsData.COUNT);
        } else {
            adapter.clearList();
            mPresenter.getSearchData(etSearch.getText().toString().trim(), ParamsData.START, ParamsData.COUNT_THREE);
        }

    }

    @OnClick(R.id.iv_close)
    void clearText() {
        etSearch.setText("");
    }

    @OnClick(R.id.tv_cancel)
    void closeActivity() {
        finish();
    }

}
