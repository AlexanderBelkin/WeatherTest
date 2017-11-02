package com.dev.weathertest.di;

import android.support.v4.app.Fragment;

import com.dev.weathertest.ui.DetailMvpView;
import com.dev.weathertest.ui.DetailPresenter;
import com.dev.weathertest.ui.DetailPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {

    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @ScopeFragment
    DetailPresenter<DetailMvpView> provideDetailPresenter(DetailPresenterImpl<DetailMvpView> presenter) {
        return presenter;
    }
}
