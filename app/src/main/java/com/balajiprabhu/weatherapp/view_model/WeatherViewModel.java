package com.balajiprabhu.weatherapp.view_model;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import com.balajiprabhu.weatherapp.network.WeatherServiceManager;
import com.balajiprabhu.weatherapp.network.model.BaseWeather;
import com.balajiprabhu.weatherapp.ui.WeatherDetailsActivity;
import com.balajiprabhu.weatherapp.ui.recyclerview.RecyclerViewAdapter;
import com.balajiprabhu.weatherapp.utils.StartActivityEvent;
import com.balajiprabhu.weatherapp.utils.UnboundViewEventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

public class WeatherViewModel implements LifecycleObserver {

    private static final String TAG = "WeatherViewModel";
    public ObservableField<String> cityName = new ObservableField<>();
    public ObservableField<String> formattedDate = new ObservableField<>();
    public ObservableField<String> description = new ObservableField<>();
    public ObservableField<String> iconCode = new ObservableField<>();
    public ObservableField<String> formattedTemperature = new ObservableField<>();
    private List<String> mCityNameList = new ArrayList<>();
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<ItemWeatherViewModel> itemWeatherViewModelList = new ArrayList<>();

    private WeatherServiceManager weatherServiceManager;
    private UnboundViewEventBus unboundViewEventBus;


    @Inject
    public WeatherViewModel(WeatherServiceManager weatherServiceManager, UnboundViewEventBus unboundViewEventBus) {
        this.weatherServiceManager = weatherServiceManager;
        this.unboundViewEventBus = unboundViewEventBus;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void initState() {
        createCityList();
        getWeather();

    }

    public void getWeather() {

        for(String mCityName : mCityNameList){

            weatherServiceManager.getWeather(mCityName,"metric").subscribe(this::bindViews,this::failure);

        }

    }


    public void failure(Throwable throwable){
        Log.e(TAG, "failure: "+throwable.getLocalizedMessage() );
    }


    public void bindViews(BaseWeather baseWeather){

        if(baseWeather != null){

            cityName.set(baseWeather.getName());
            @SuppressLint("SimpleDateFormat")
            String dateAsText = new SimpleDateFormat("EEE | MMMM dd").format(new Date(baseWeather.getDt() * 1000L));
            formattedDate.set(dateAsText);
            description.set(baseWeather.getWeather().get(0).getDescription());
            iconCode.set(baseWeather.getWeather().get(0).getIcon());
            formattedTemperature.set(String.format(baseWeather.getMain().getTemp().toString() + "%s", (char) 0x00B0));

            ItemWeatherViewModel itemWeatherViewModel = new ItemWeatherViewModel();
            itemWeatherViewModel.setCityName(cityName.get());
            itemWeatherViewModel.setFormattedDate(formattedDate.get());
            itemWeatherViewModel.setDescription(description.get().substring(0, 1).toUpperCase() + description.get().substring(1));
            itemWeatherViewModel.setIconCode(iconCode.get());
            itemWeatherViewModel.setFormattedTemperature(formattedTemperature.get());
            itemWeatherViewModelList.add(itemWeatherViewModel);

            recyclerViewAdapter.notifyDataSetChanged();

        }


    }

    public RecyclerViewAdapter setRecyclerViewAdapter(){
        return recyclerViewAdapter = new RecyclerViewAdapter(itemWeatherViewModelList, this);
    }

    private void createCityList() {

        mCityNameList.add("Chennai");
        mCityNameList.add("Coimbatore");
        mCityNameList.add("Madurai");
        mCityNameList.add("Bangalore");
        mCityNameList.add("Kochi");
        mCityNameList.add("Mumbai");
        mCityNameList.add("Selam");
        mCityNameList.add("Ooty");
        mCityNameList.add("Goa");
        mCityNameList.add("Tirupur");
        mCityNameList.add("Hosur");
        mCityNameList.add("Erode");
        mCityNameList.add("Tiruchi");
        mCityNameList.add("Tirupati");

    }

    public void navigate() {
        emitStartActivityEvent(WeatherDetailsActivity.class);
    }

    protected void emitStartActivityEvent(Class startActivityClazz) {
        StartActivityEvent startActivityEvent = StartActivityEvent.build(this).activityName(startActivityClazz);
        unboundViewEventBus.send(startActivityEvent);
    }


}
