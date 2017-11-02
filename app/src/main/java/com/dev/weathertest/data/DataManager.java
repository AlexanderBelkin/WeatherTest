package com.dev.weathertest.data;

import com.dev.weathertest.model.WeatherResp;

import io.reactivex.Single;

public interface DataManager {

    Single<WeatherResp> getWeatherSingle(double lat, double lng);
}
