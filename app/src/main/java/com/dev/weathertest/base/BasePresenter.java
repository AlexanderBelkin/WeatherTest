package com.dev.weathertest.base;

public interface BasePresenter<V extends BaseMvpView> {

    void onCreate();

    void onAttachView(V mvpView);

    void onDetachView();

    void onDestroy();
}
