package com.djw.douban.ui.movies.fragment;


import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.djw.douban.MainActivity;
import com.djw.douban.R;
import com.djw.douban.base.BaseFragment;
import com.djw.douban.data.ParamsData;
import com.djw.douban.data.newmovies.NewMoviesBaseData;
import com.djw.douban.ui.message.MessageActivity;
import com.djw.douban.ui.movies.activity.TypeActivity;
import com.djw.douban.ui.movies.adapter.NewMoviesAdapter;
import com.djw.douban.ui.movies.contract.NewMoviesContract;
import com.djw.douban.ui.movies.presenter.NewMoviesPresenter;
import com.djw.douban.ui.search.activity.SearchActivity;
import com.djw.douban.zxing.activity.CaptureActivity;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Util;

import java.util.List;

import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewMoviesFragment extends BaseFragment<NewMoviesPresenter> implements NewMoviesContract.View, OnRefreshListener, OnLoadMoreListener, View.OnClickListener {

    private NewMoviesAdapter adapter;

    private SwipeToLoadLayout swipeToLoadLayout;
    private RecyclerView recyclerView;
    private BoomMenuButton bmb;
    private int[] bmb_icon = {R.mipmap.china, R.mipmap.oumei, R.mipmap.riben, R.mipmap.hanguo, R.mipmap.xiaoyuan, R.mipmap.qingchun, R.mipmap.jingdian, R.mipmap.huaijiu};

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void initView(View view) {
        bmb = (BoomMenuButton) view.findViewById(R.id.bmb);
        view.findViewById(R.id.tv_search).setOnClickListener(this);
        swipeToLoadLayout = ((SwipeToLoadLayout) view.findViewById(R.id.stll_movies));
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.swipe_target);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new NewMoviesAdapter();
        recyclerView.setAdapter(adapter);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return adapter.isSpan(position);
            }
        });
    }

    @Override
    protected void doBusiness() {
        final String[] bmb_title = getActivity().getResources().getStringArray(R.array.movies_home_bmb_title);
        for (int i = 0; i < bmb.getButtonPlaceEnum().buttonNumber(); i++) {
            bmb.addBuilder(new TextOutsideCircleButton.Builder()
//                    .normalColorRes(R.color.white)//dialog背景颜色
//                    .pieceColorRes(R.color.white)//dialog背景颜色
                    .isRound(false)
                    .shadowCornerRadius(Util.dp2px(20))
                    .buttonCornerRadius(Util.dp2px(20))
                    .normalText(bmb_title[i])
                    .normalImageRes(bmb_icon[i])
                    .imagePadding(new Rect(50, 50, 50, 50))
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            Bundle bundle = new Bundle();
                            bundle.putString("q", bmb_title[index]);
                            startActivity(TypeActivity.class, bundle);
                        }
                    }));
        }
    }

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
        mPresenter.attachView(this);
        mPresenter.getNewMovies(ParamsData.START, ParamsData.COUNT_NEW_MOVIES, false, true);
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_new_movies;
    }

    @Override
    public void showError(String msg) {
        refreshOrLoadMoreStop();
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        ((MainActivity) getActivity()).showProgress();
    }

    @Override
    public void dismissProgress() {
        ((MainActivity) getActivity()).dismissProgress();
    }

    @Override
    public void showNewMovies(List<NewMoviesBaseData> list, boolean isLoadMore) {
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
    public void onRefresh() {
        mPresenter.getNewMovies(ParamsData.START, ParamsData.COUNT_NEW_MOVIES, false, false);
    }

    @Override
    public void onLoadMore() {
        mPresenter.getNewMovies(adapter.getItemCount() + 1, ParamsData.COUNT, true, false);
    }

    public void scrollToTop() {
        recyclerView.smoothScrollToPosition(0);
    }

    @Override
    public void onClick(View v) {
        startActivity(SearchActivity.class);
    }

    @OnClick(R.id.iv_back)
    void startActivityToQrActivity() {
        startActivity(CaptureActivity.class);
    }

    @OnClick(R.id.iv_message)
    void startActivityToMessageActivity() {
        startActivity(MessageActivity.class);
    }

}
