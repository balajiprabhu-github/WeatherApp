package com.balajiprabhu.weatherapp.network;

import com.balajiprabhu.weatherapp.network.model.BaseWeather;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface WeatherService {

    @Headers({
            "X-RapidAPI-Key: 4fc318d903mshf57c0a4b90734bep15668djsn0fa8ed47953a",
    })
    @GET("/weather")
    Observable<BaseWeather> getWeather(@Query("q") String place, @Query("units") String unit);

}
