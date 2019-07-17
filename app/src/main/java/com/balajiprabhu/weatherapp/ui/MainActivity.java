package com.balajiprabhu.weatherapp.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.balajiprabhu.weatherapp.R;
import com.balajiprabhu.weatherapp.databinding.ActivityMainBinding;
import com.balajiprabhu.weatherapp.ui.recyclerview.RecyclerViewAdapter;
import com.balajiprabhu.weatherapp.view_model.WeatherViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity implements LifecycleOwner {

    @Inject
    WeatherViewModel weatherViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        this.getLifecycle().addObserver(weatherViewModel);

//        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(weatherViewModel);
//        activityMainBinding.viewPager.setAdapter(viewPagerAdapter);

        RecyclerViewAdapter recyclerViewAdapter = weatherViewModel.setRecyclerViewAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        activityMainBinding.recyclerView.setLayoutManager(linearLayoutManager);
        activityMainBinding.recyclerView.setHasFixedSize(true);

        activityMainBinding.recyclerView.setAdapter(recyclerViewAdapter);
        activityMainBinding.setViewModel(weatherViewModel);


    }


}
