package com.balajiprabhu.weatherapp.view_model;

import com.balajiprabhu.weatherapp.ui.WeatherDetailsActivity;
import com.balajiprabhu.weatherapp.utils.StartActivityEvent;
import com.balajiprabhu.weatherapp.utils.UnboundViewEventBus;

import androidx.databinding.BaseObservable;

public class ItemWeatherViewModel extends BaseObservable {

    public String cityName ;
    public String formattedDate ;
    public String description ;
    public String iconCode ;
    public String formattedTemperature ;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getFormattedDate() {
        return formattedDate;
    }

    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIconCode() {
        return iconCode;
    }

    public void setIconCode(String iconCode) {
        this.iconCode = iconCode;
    }

    public String getFormattedTemperature() {
        return formattedTemperature;
    }

    public void setFormattedTemperature(String formattedTemperature) {
        this.formattedTemperature = formattedTemperature;
    }


}
