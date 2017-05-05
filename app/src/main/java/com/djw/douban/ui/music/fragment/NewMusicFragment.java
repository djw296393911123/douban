package com.djw.douban.ui.music.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.djw.douban.MainActivity;
import com.djw.douban.R;
import com.djw.douban.base.BaseFragment;
import com.djw.douban.data.ParamsData;
import com.djw.douban.data.newmusic.MusicBaseData;
import com.djw.douban.ui.message.MessageActivity;
import com.djw.douban.ui.music.adapter.NewMusicAdapter;
import com.djw.douban.ui.music.contract.NewMusicContract;
import com.djw.douban.ui.music.presenter.NewMusicPresenter;
import com.djw.douban.ui.search.activity.SearchActivity;
import com.djw.douban.zxing.activity.CaptureActivity;

import java.util.List;

import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewMusicFragment extends BaseFragment<NewMusicPresenter> implements NewMusicContract.View, View.OnClickListener {

    private NewMusicAdapter adapter;

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.tv_search).setOnClickListener(this);
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
        mPresenter.getMusic(ParamsData.START, ParamsData.COUNT);
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
        ((MainActivity) getActivity()).showProgress();
    }

    @Override
    public void dismissProgress() {
        ((MainActivity) getActivity()).dismissProgress();
    }

    @Override
    public void showMusic(List<MusicBaseData> list) {
        adapter.notifyDataChange(list);
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
