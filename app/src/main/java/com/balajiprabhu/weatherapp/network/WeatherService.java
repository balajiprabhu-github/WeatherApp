package com.balajiprabhu.weatherapp.network;

import com.balajiprabhu.weatherapp.model.BaseWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface WeatherService {

    @Headers({
            "X-RapidAPI-Key: 4fc318d903mshf57c0a4b90734bep15668djsn0fa8ed47953a",
    })
    @GET("/weather")
    Call<BaseWeather> getWeather(@Query("q") String place,@Query("units") String unit);

}
