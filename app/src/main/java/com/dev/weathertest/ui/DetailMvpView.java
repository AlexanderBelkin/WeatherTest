package com.dev.weathertest.ui;

import android.support.annotation.Nullable;

import com.dev.weathertest.base.BaseMvpView;
import com.dev.weathertest.model.WeatherResp;

public interface DetailMvpView extends BaseMvpView {

    @Nullable
    Double[] getLocation();

    void setData(WeatherResp weatherResp);
}
