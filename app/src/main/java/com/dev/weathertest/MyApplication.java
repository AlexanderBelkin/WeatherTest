package com.dev.weathertest;

import android.app.Application;

import com.dev.weathertest.di.AppComponent;
import com.dev.weathertest.di.AppModule;
import com.dev.weathertest.di.DaggerAppComponent;

public class MyApplication extends Application {

    public static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }
}
