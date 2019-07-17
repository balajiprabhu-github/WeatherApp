package com.balajiprabhu.weatherapp.dependencyinjection;

import com.balajiprabhu.weatherapp.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {
    @ContributesAndroidInjector
    abstract MainActivity providesMainActivity();
}
