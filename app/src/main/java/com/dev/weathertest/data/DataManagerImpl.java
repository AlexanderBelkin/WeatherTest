package com.dev.weathertest.data;

import com.dev.weathertest.AppConstants;
import com.dev.weathertest.model.WeatherResp;

import javax.inject.Inject;

import io.reactivex.Single;

public class DataManagerImpl implements DataManager {

    private final ApiManager apiManager;

    @Inject
    public DataManagerImpl(ApiManager apiManager) {
        this.apiManager = apiManager;
    }

    @Override
    public Single<WeatherResp> getWeatherSingle(double lat, double lng) {
        return apiManager.getWeather(lat, lng, AppConstants.WEATHER_API_KEY);
    }
}
