package com.djw.douban.ui.home.music.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.djw.douban.MainActivity;
import com.djw.douban.R;
import com.djw.douban.base.BaseFragment;
import com.djw.douban.data.ParamsData;
import com.djw.douban.data.book.BookTypeData;
import com.djw.douban.data.music.MusicRoot;
import com.djw.douban.ui.home.book.adapter.BookTypeAdapter;
import com.djw.douban.ui.home.music.adapter.MusicRecyclerAdapter;
import com.djw.douban.ui.home.music.contract.MusicContract;
import com.djw.douban.ui.home.music.presenter.MusicPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MusicFragment extends BaseFragment<MusicPresenter> implements MusicContract.View, XRecyclerView.LoadingListener {

    private BookTypeAdapter adapter;

    private String[] types = {"华语", "欧美", "日韩", "新歌", "男歌手", "女歌手", "组合", "排行榜", "经典", "老歌", "销量", "薛之谦"};
    private XRecyclerView xRecyclerView;
    private int position;
    private MusicRecyclerAdapter musicRecyclerAdapter;

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void initView(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_book_type);
        xRecyclerView = ((XRecyclerView) view.findViewById(R.id.xrv_book));
        xRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        xRecyclerView.setLoadingListener(this);
        musicRecyclerAdapter = new MusicRecyclerAdapter(getActivity());
        xRecyclerView.setAdapter(musicRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new BookTypeAdapter() {
            @Override
            public void OnItemClick(int position) {
                MusicFragment.this.position = position;
                mPresenter.getMusic(ParamsData.START, ParamsData.COUNT, types[position], false);
            }
        };
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void doBusiness() {
        List<BookTypeData> list = new ArrayList<>();
        list.add(new BookTypeData(types[0], true));
        for (int i = 1; i < types.length; i++) {
            list.add(new BookTypeData(types[i], false));
        }
        adapter.notifyDataChange(list);
    }

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
        mPresenter.attachView(this);
        mPresenter.getMusic(ParamsData.START, ParamsData.COUNT, types[0], false);
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_book;
    }

    @Override
    public void onRefresh() {
        mPresenter.getMusic(ParamsData.START, ParamsData.COUNT, types[position], false);
    }

    @Override
    public void onLoadMore() {
        mPresenter.getMusic(musicRecyclerAdapter.getItemCount() + 1, ParamsData.COUNT, types[position], true);
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        ((MainActivity) getActivity()).showProgress();
    }

    @Override
    public void dismissProgress() {
        ((MainActivity) getActivity()).dismissProgress();
        xRecyclerView.loadMoreComplete();
        xRecyclerView.refreshComplete();
    }

    @Override
    public void showMusic(MusicRoot musicRoot, boolean isLoadMore) {
        musicRecyclerAdapter.notifyDataChange(musicRoot.getMusics(), isLoadMore);
    }

}
