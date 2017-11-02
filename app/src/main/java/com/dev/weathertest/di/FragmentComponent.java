package com.dev.weathertest.di;

import com.dev.weathertest.ui.DetailFragment;

import dagger.Component;

@ScopeFragment
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    void inject(DetailFragment fragment);
}
