package com.balajiprabhu.weatherapp.network;

import com.balajiprabhu.weatherapp.network.model.BaseWeather;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class WeatherServiceManager {

    WeatherService weatherService;

    @Inject
    public WeatherServiceManager(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    public Observable<BaseWeather> getWeather(String cityName, String metric) {
        return weatherService.getWeather(cityName,metric).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

}
