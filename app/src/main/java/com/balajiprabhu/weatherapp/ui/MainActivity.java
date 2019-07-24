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
import com.balajiprabhu.weatherapp.utils.BaseActivity;
import com.balajiprabhu.weatherapp.utils.UnboundViewEventBus;
import com.balajiprabhu.weatherapp.view_model.ItemWeatherViewModel;
import com.balajiprabhu.weatherapp.view_model.WeatherViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends BaseActivity implements LifecycleOwner {


    @Inject
    UnboundViewEventBus eventBus;

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

    @Override
    protected CompositeDisposable registerUnboundViewEvents() {
        CompositeDisposable events = new CompositeDisposable();
        events.add(eventBus.startActivity(ItemWeatherViewModel.class).subscribe(this::startActivity));
        return events;
    }




}
