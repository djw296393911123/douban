package com.djw.douban.ui.mine.fragment;


import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import com.djw.douban.R;
import com.djw.douban.base.BaseFragment;
import com.djw.douban.data.mine.MineItemData;
import com.djw.douban.ui.mine.contract.OnlineContract;
import com.djw.douban.ui.mine.presenter.OnlinePresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseFragment<OnlinePresenter> implements OnlineContract.View {

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void doBusiness() {

    }

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
        mPresenter.attachView(this);
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
}
