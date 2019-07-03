package com.balajiprabhu.weatherapp.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.balajiprabhu.weatherapp.R;
import com.balajiprabhu.weatherapp.model.BaseWeather;
import com.balajiprabhu.weatherapp.network.RetrofitUtils;
import com.balajiprabhu.weatherapp.network.WeatherService;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ViewPager mViewPager;
    private List<String> mCityNameList = new ArrayList<>();
    private List<BaseWeather> mWeatherList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = findViewById(R.id.view_pager);

        createCityList();
        getWeather();

    }

    private void createCityList(){
        mCityNameList.add("Chennai");
        mCityNameList.add("Coimbatore");
        mCityNameList.add("Madurai");
        mCityNameList.add("Bangalore");
        mCityNameList.add("Kochi");
        mCityNameList.add("Mumbai");
    }

    private void getWeather() {

        for(String cityName : mCityNameList){

            WeatherService apiInterface = new RetrofitUtils().getService().create(WeatherService.class);
            Call<BaseWeather> call = apiInterface.getWeather(cityName,"metric");
            call.enqueue(new Callback<BaseWeather>() {
                @Override
                public void onResponse(@NotNull Call<BaseWeather> call, @NotNull Response<BaseWeather> response) {
                    Log.e(TAG, "onResponse: " + response.body());
                    if(response.body() != null){
                        BaseWeather mBaseWeather = response.body();
                        mWeatherList.add(mBaseWeather);

                        if(mCityNameList.size() == mWeatherList.size()){
                            mViewPager.setAdapter(new ViewPagerAdapter(MainActivity.this,mWeatherList));
                        }
                    }
                }

                @Override
                public void onFailure(@NotNull Call<BaseWeather> call, @NotNull Throwable throwable) {
                    Log.e(TAG, "onFailure: " + throwable.getLocalizedMessage());
                }
            });

        }

    }

}
