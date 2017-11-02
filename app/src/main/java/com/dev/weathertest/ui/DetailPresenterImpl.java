package com.dev.weathertest.ui;

import android.util.Log;

import com.dev.weathertest.base.BasePresenterImpl;
import com.dev.weathertest.data.DataManager;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DetailPresenterImpl<V extends DetailMvpView> extends BasePresenterImpl<V> implements DetailPresenter<V> {

    private static final String TAG = "detail";

    private Disposable disposable;

    @Inject
    public DetailPresenterImpl(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onAttachView(V mvpView) {
        super.onAttachView(mvpView);
        loadWeather();
    }

    private void loadWeather() {

        Double[] location = getMvpView().getLocation();
        if (location == null) {
            return;
        }

        getMvpView().showLoading();

        disposable = getDataManager().getWeatherSingle(location[0], location[1])
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(weather -> {
                    if (getMvpView() != null) {
                        getMvpView().setData(weather);
                        getMvpView().hideLoading();
                    }
                }, throwable -> {
                    Log.e(TAG, "loadWeather: ", throwable);
                    if (getMvpView() != null) {
                        getMvpView().hideLoading();
                        getMvpView().showError();
                    }
                });
    }

    @Override
    public void onDetachView() {
        super.onDetachView();
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
