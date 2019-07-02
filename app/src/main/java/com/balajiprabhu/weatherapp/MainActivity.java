package com.balajiprabhu.weatherapp;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.balajiprabhu.weatherapp.model.BaseWeather;
import com.balajiprabhu.weatherapp.network.ApiInterface;
import com.balajiprabhu.weatherapp.network.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWeather();

    }


    public void getWeather() {

        ApiInterface apiInterface = new ApiService().getService().create(ApiInterface.class);
        Call<BaseWeather> call = apiInterface.getWeather("Chennai");
        call.enqueue(new Callback<BaseWeather>() {
            @Override
            public void onResponse(Call<BaseWeather> call, Response<BaseWeather> response) {

                Log.e(TAG, "onResponse: " + response.body().toString());

            }

            @Override
            public void onFailure(Call<BaseWeather> call, Throwable throwable) {

                Log.e(TAG, "onFailure: " + throwable.getLocalizedMessage());

            }
        });


    }


}
