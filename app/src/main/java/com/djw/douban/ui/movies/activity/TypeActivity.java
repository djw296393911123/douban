package com.djw.douban.ui.movies.activity;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.djw.douban.R;
import com.djw.douban.base.RxToolbarActivity;
import com.djw.douban.data.ParamsData;
import com.djw.douban.data.movies.TypeData;
import com.djw.douban.ui.movies.adapter.TypeAdapter;
import com.djw.douban.ui.movies.contract.TypeContract;
import com.djw.douban.ui.movies.presenter.TypePresenter;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;

import java.util.List;

import butterknife.BindView;

public class TypeActivity extends RxToolbarActivity<TypePresenter> implements TypeContract.View, OnRefreshListener, OnLoadMoreListener {

    @BindView(R.id.swipe_target)
    RecyclerView rvType;
    @BindView(R.id.stll_movies)
    SwipeToLoadLayout swipeToLoadLayout;
    @BindView(R.id.bmb)
    BoomMenuButton bmb;
    private TypeAdapter adapter;
    private String q;
    private int[] bmb_icon = {R.mipmap.shengxu, R.mipmap.jiangxu, R.mipmap.renshu, R.mipmap.niandai};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);
    }

    @Override
    public void showError(String msg) {
        refreshOrLoadMoreStop();
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showType(List<TypeData.SubjectsBean> list, boolean isLoadMore) {
        refreshOrLoadMoreStop();
        adapter.notifyDataChange(list, isLoadMore);
    }

    private void refreshOrLoadMoreStop() {
        if (swipeToLoadLayout.isRefreshing()) {
            swipeToLoadLayout.setRefreshing(false);
        }
        if (swipeToLoadLayout.isLoadingMore()) {
            swipeToLoadLayout.setLoadingMore(false);
        }
    }

    @Override
    public void initView() {
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        rvType.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TypeAdapter(this);
        rvType.setAdapter(adapter);
    }

    @Override
    protected void scrollToTop() {
        rvType.smoothScrollToPosition(0);
    }

    @Override
    public void doBusiness() {
        Resources resources = getResources();
        String[] bmb_title = resources.getStringArray(R.array.movies_type_bmb_title);
        String[] bmb_subTitle = resources.getStringArray(R.array.movies_type_bmb_subTitle);

        for (int i = 0; i < bmb.getButtonPlaceEnum().buttonNumber(); i++) {
            bmb.addBuilder(new HamButton.Builder()
                    .normalText(bmb_title[i])
                    .subNormalText(bmb_subTitle[i])
                    .normalImageRes(bmb_icon[i])
                    .imagePadding(new Rect(50, 50, 50, 50))
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            mPresenter.getRule(index, adapter.getList());
                        }
                    }));
        }
    }

    @Override
    public void inject() {
        getActivityComponent().inject(this);
        mPresenter.attachView(this);
        q = getIntent().getExtras().getString("q");
        setToolBarTitle(q);
        mPresenter.getType(q, ParamsData.START, ParamsData.COUNT, false, true);
    }

    @Override
    public void onRefresh() {
        mPresenter.getType(q, ParamsData.START, ParamsData.COUNT, false, false);
    }

    @Override
    public void onLoadMore() {
        mPresenter.getType(q, adapter.getItemCount() + 1, ParamsData.COUNT, true, false);
    }
}
