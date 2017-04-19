package com.djw.douban.ui.home.music.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.djw.douban.R;
import com.djw.douban.base.BaseFragment;
import com.djw.douban.data.ParamsData;
import com.djw.douban.data.newmusic.MusicBaseData;
import com.djw.douban.ui.home.music.adapter.NewMusicAdapter;
import com.djw.douban.ui.home.music.contract.NewMusicContract;
import com.djw.douban.ui.home.music.presenter.NewMusicPresenter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewMusicFragment extends BaseFragment<NewMusicPresenter> implements NewMusicContract.View {

    private NewMusicAdapter adapter;

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void initView(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_new_music);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return adapter.isSpan(position);
            }
        });
        adapter = new NewMusicAdapter(getActivity());
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void doBusiness() {

    }

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
        mPresenter.attachView(this);
        mPresenter.getMusic("", ParamsData.START, ParamsData.COUNT);
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_new_music;
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {

    }

    @Override
    public void showMusic(List<MusicBaseData> list) {
        adapter.notifyDataChange(list);
    }
}
