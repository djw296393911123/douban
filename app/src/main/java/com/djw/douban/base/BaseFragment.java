package com.djw.douban.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.djw.douban.component.DaggerFragmentComponent;
import com.djw.douban.component.FragmentComponent;
import com.djw.douban.module.FragmentModule;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by JasonDong
 * <p>
 * on 2017/3/23.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView {

    protected boolean isVisible;
    private Unbinder bind;

    /**
     * 在这里实现Fragment数据的缓加载.
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    @Inject
    protected T mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(bindLayout(), container, false);
        bind = ButterKnife.bind(this, view);
        initView(view);
        inject();
        doBusiness();
        return view;
    }

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .appComponent(BaseApplication.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) mPresenter.detachView();
        mPresenter = null;
        bind.unbind();
    }

    protected FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

    public void startActivity(Class<?> clz) {
        startActivity(clz, null);
    }

    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), clz);
        if (bundle != null) {

            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    protected void onVisible() {
        lazyLoad();
    }

    protected abstract void lazyLoad();

    protected abstract void initView(View view);

    protected abstract void doBusiness();

    protected abstract void inject();

    protected abstract int bindLayout();

    protected void onInvisible() {
        System.gc();
    }

}