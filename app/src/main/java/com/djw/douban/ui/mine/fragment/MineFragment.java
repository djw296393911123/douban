package com.djw.douban.ui.mine.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.djw.douban.R;
import com.djw.douban.base.BaseFragment;
import com.djw.douban.data.ParamsData;
import com.djw.douban.data.mine.MineListData;
import com.djw.douban.data.movies.MoviesItemData;
import com.djw.douban.ui.mine.adapter.MineAdapter;
import com.djw.douban.ui.mine.contract.OnlineContract;
import com.djw.douban.ui.mine.presenter.OnlinePresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseFragment<OnlinePresenter> implements OnlineContract.View, XRecyclerView.LoadingListener {


    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.tl_base)
    Toolbar tlBase;
    @BindView(R.id.xrv_mine)
    RecyclerView xrvMine;
    private MineAdapter adapter;

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void initView(View view) {
        tlBase.setTitle("");
        tvToolbarTitle.setText(getString(R.string.mine));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        xrvMine.setLayoutManager(gridLayoutManager);
        adapter = new MineAdapter(getActivity());
        xrvMine.setAdapter(adapter);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return adapter.span(position);
            }
        });

    }

    public void scrollToTop() {
        xrvMine.scrollToPosition(0);
    }

    @Override
    protected void doBusiness() {

    }

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
        mPresenter.attachView(this);
        mPresenter.getUrl(ParamsData.START, ParamsData.COUNT, false);
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {
//        xrvMine.refreshComplete();
//        xrvMine.loadMoreComplete();
    }

    @Override
    public void onRefresh() {
        mPresenter.getUrl(ParamsData.START, ParamsData.COUNT, false);
    }

    @Override
    public void onLoadMore() {
        mPresenter.getUrl(ParamsData.START, ParamsData.COUNT, true);
    }

    @Override
    public void showImg(List<MineListData> url, boolean isLoadMore) {

        adapter.notifyDataChange(url, isLoadMore);

    }
}
