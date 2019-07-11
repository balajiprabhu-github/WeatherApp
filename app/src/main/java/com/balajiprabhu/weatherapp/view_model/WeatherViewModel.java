package com.balajiprabhu.weatherapp.view_model;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.balajiprabhu.weatherapp.network.RetrofitUtils;
import com.balajiprabhu.weatherapp.network.WeatherService;
import com.balajiprabhu.weatherapp.network.model.BaseWeather;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherViewModel implements LifecycleObserver {

    private static final String TAG = "WeatherViewModel";
    public ObservableField<String> cityName = new ObservableField<>();
    public ObservableField<String> formattedDate = new ObservableField<>();
    public ObservableField<String> description = new ObservableField<>();
    public ObservableField<String> iconCode = new ObservableField<>();
    public ObservableField<String> formattedTemperature = new ObservableField<>();
    public ObservableField<Integer> citySize = new ObservableField<>();
    public List<String> mCityNameList = new ArrayList<>();

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void initState() {
        createCityList();
        getWeather(0);
    }

    public void getWeather(int position) {

        WeatherService apiInterface = new RetrofitUtils().getService().create(WeatherService.class);
        Call<BaseWeather> call = apiInterface.getWeather(mCityNameList.get(position), "metric");
        call.enqueue(new Callback<BaseWeather>() {
            @Override
            public void onResponse(@NotNull Call<BaseWeather> call, @NotNull Response<BaseWeather> response) {
                Log.e(TAG, "onResponse: " + response.body());

                BaseWeather baseWeather = response.body();

                cityName.set(baseWeather.getName());
                @SuppressLint("SimpleDateFormat")
                String dateAsText = new SimpleDateFormat("EEE | MMMM dd").format(new Date(baseWeather.getDt() * 1000L));
                formattedDate.set(dateAsText);
                description.set(baseWeather.getWeather().get(0).getDescription());
                iconCode.set(baseWeather.getWeather().get(0).getIcon());
                formattedTemperature.set(String.format(baseWeather.getMain().getTemp().toString() + "%s", (char) 0x00B0));


            }

            @Override
            public void onFailure(@NotNull Call<BaseWeather> call, @NotNull Throwable throwable) {
                Log.e(TAG, "onFailure: " + throwable.getLocalizedMessage());
            }
        });

    }

    private void createCityList() {
        mCityNameList.add("Chennai");
        mCityNameList.add("Coimbatore");
        mCityNameList.add("Madurai");
        mCityNameList.add("Bangalore");
        mCityNameList.add("Kochi");
        mCityNameList.add("Mumbai");

        citySize.set(mCityNameList.size());
    }


}
