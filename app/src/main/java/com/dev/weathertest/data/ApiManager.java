package com.dev.weathertest.data;

import com.dev.weathertest.AppConstants;
import com.dev.weathertest.model.WeatherResp;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiManager {

    @GET(AppConstants.QUERY_LOCATION)
    Single<WeatherResp> getWeather(@Query(AppConstants.LAT_QEURY) double lat,
                                   @Query(AppConstants.LON_QEURY) double lng,
                                   @Query(AppConstants.APP_ID_QEURY) String appid);
}
