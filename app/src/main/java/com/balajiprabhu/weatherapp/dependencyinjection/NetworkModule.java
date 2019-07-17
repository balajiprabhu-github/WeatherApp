package com.balajiprabhu.weatherapp.dependencyinjection;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NetworkModule {

    //Retrofit
    //RxJavaFactory
    //Gson

    @Provides
    @Singleton
    Retrofit (Gson, RxJava){
    }

    @Provides
    Gson

}
