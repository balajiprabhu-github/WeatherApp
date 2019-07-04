package com.balajiprabhu.weatherapp.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import com.balajiprabhu.weatherapp.R;
import com.balajiprabhu.weatherapp.model.BaseWeather;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class ViewPagerAdapter extends PagerAdapter {

    private Context mContext;
    private List<BaseWeather> mWeatherList;


    ViewPagerAdapter(Context context, List<BaseWeather> mWeatherList) {
        mContext = context;
        this.mWeatherList = mWeatherList;
    }

    @Override
    public void destroyItem(@NotNull ViewGroup container, int position, @NotNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return mWeatherList.size();
    }

    @Override
    public boolean isViewFromObject(@NotNull View view, @NotNull Object object) {
        return view == object;
    }

    @NotNull
    @Override
    public Object instantiateItem(@NotNull ViewGroup container, final int position) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_city, container, false);

        TextView mDataTime = view.findViewById(R.id.tv_date_time);
        TextView mTempature = view.findViewById(R.id.tv_temperature);
        TextView mCityName = view.findViewById(R.id.tv_city_name);
        TextView mDescription = view.findViewById(R.id.tv_weather_description);
        ImageView mWeatherIcon = view.findViewById(R.id.img_weather_icon);

        Integer currentDate = mWeatherList.get(position).getDt();
        String iconCode = mWeatherList.get(position).getWeather().get(0).getIcon();
        String iconUrl = "http://openweathermap.org/img/w/" + iconCode + ".png";
        String description = mWeatherList.get(position).getWeather().get(0).getDescription().substring(0, 1).toUpperCase() + mWeatherList.get(position).getWeather().get(0).getDescription().substring(1);
        Double temp = mWeatherList.get(position).getMain().getTemp();
        String cityName = mWeatherList.get(position).getName();
        @SuppressLint("SimpleDateFormat") String dateAsText = new SimpleDateFormat("EEE | MMMM dd").format(new Date(currentDate * 1000L));

        mDataTime.setText(dateAsText);
        mCityName.setText(cityName);
        mDescription.setText(description);
        mTempature.setText(String.format(temp.toString() + "%s", (char) 0x00B0));
        Picasso.get().load(iconUrl).into(mWeatherIcon);

        container.addView(view);

        return view;
    }




}
