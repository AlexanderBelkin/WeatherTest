package com.dev.weathertest.di;

import com.dev.weathertest.data.DataManager;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    DataManager getDataManager();
}
