package com.djw.douban.ui.mine.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.djw.douban.R;
import com.djw.douban.base.BaseFragment;
import com.djw.douban.data.mine.MineItemData;
import com.djw.douban.ui.mine.contract.OnlineContract;
import com.djw.douban.ui.mine.presenter.OnlinePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseFragment<OnlinePresenter> implements OnlineContract.View {

    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.tl_base)
    Toolbar tlBase;
    @BindView(R.id.iv_mine)
    ImageView ivMine;

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void initView(View view) {
        tlBase.setTitle("");
        tvToolbarTitle.setText(getString(R.string.mine));
    }

    @Override
    protected void doBusiness() {

    }

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
        mPresenter.attachView(this);
        mPresenter.getUrl();
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_mine;
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
    public void showOnline(MineItemData mineItemData) {
        Log.i("mineItemData", mineItemData.toString());
    }

    @Override
    public void showImg(String url) {
        Glide.with(getActivity()).load(url).asBitmap().into(ivMine);
    }
}
