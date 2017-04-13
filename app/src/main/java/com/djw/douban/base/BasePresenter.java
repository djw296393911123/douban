package com.djw.douban.base;


public interface BasePresenter<T extends BaseView>{

    void attachView(T view);

    void detachView();
}
