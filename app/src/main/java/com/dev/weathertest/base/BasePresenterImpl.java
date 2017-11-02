package com.dev.weathertest.base;

import android.support.annotation.Nullable;

import com.dev.weathertest.data.DataManager;

import javax.inject.Inject;

public class BasePresenterImpl<V extends BaseMvpView> implements BasePresenter<V> {

    private final DataManager dataManager;

    @Inject
    public BasePresenterImpl(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Nullable
    private V mvpView;

    @Override
    public void onCreate() {

    }

    @Override
    public void onAttachView(V mvpView) {
        this.mvpView = mvpView;
    }

    @Override
    public void onDetachView() {
        mvpView = null;
    }

    @Override
    public void onDestroy() {

    }


    public DataManager getDataManager() {
        return dataManager;
    }

    @Nullable
    public V getMvpView() {
        return mvpView;
    }
}
