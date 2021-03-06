package com.llf.lib.mvp;

/**
 * Created by llf on 2016/7/26.
 */
public interface BaseView<T> {
    void setPresenter(T presenter);
    void showLoading();
    void hideLoading();
    void showResult(String data);
}
