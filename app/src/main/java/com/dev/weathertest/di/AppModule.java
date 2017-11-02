package com.dev.weathertest.di;

import android.app.Application;

import com.dev.weathertest.AppConstants;
import com.dev.weathertest.data.ApiManager;
import com.dev.weathertest.data.DataManager;
import com.dev.weathertest.data.DataManagerImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    ApiManager provideApiManager() {
        RxJava2CallAdapterFactory rxAdapter = RxJava2CallAdapterFactory.create();
        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(rxAdapter)
                .build();
        return retrofit.create(ApiManager.class);
    }

    @Provides
    @Singleton
    DataManager provideDataManager(DataManagerImpl dataManager) {
    return dataManager;
    }
}
