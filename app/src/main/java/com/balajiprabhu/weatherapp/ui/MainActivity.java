package com.balajiprabhu.weatherapp.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;

import com.balajiprabhu.weatherapp.R;
import com.balajiprabhu.weatherapp.databinding.ActivityMainBinding;
import com.balajiprabhu.weatherapp.ui.viewpager.ViewPagerAdapter;
import com.balajiprabhu.weatherapp.view_model.WeatherViewModel;

public class MainActivity extends AppCompatActivity implements LifecycleOwner {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        WeatherViewModel weatherViewModel = new WeatherViewModel();
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(weatherViewModel);

        activityMainBinding.viewPager.setAdapter(viewPagerAdapter);
        activityMainBinding.setViewModel(weatherViewModel);

        this.getLifecycle().addObserver(weatherViewModel);

    }


}
