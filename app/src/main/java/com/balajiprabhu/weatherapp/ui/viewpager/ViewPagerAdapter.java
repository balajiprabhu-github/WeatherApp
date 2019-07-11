package com.balajiprabhu.weatherapp.ui.viewpager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.PagerAdapter;

import com.balajiprabhu.weatherapp.R;
import com.balajiprabhu.weatherapp.databinding.ItemLayoutBinding;
import com.balajiprabhu.weatherapp.view_model.WeatherViewModel;

import org.jetbrains.annotations.NotNull;


public class ViewPagerAdapter extends PagerAdapter {


    private WeatherViewModel weatherViewModel;

    public ViewPagerAdapter(WeatherViewModel weatherViewModel) {
        this.weatherViewModel = weatherViewModel;
    }

    @Override
    public void destroyItem(@NotNull ViewGroup container, int position, @NotNull Object object) {

        ItemLayoutBinding itemLayoutBinding = (ItemLayoutBinding) object;
        itemLayoutBinding.setViewModel(null);
        container.removeView(itemLayoutBinding.getRoot());
    }


    @Override
    public boolean isViewFromObject(@NotNull View view, @NotNull Object object) {
        return ((ItemLayoutBinding) object).getRoot() == view;
    }

    @Override
    public int getCount() {
        return 6;
    }


    @NotNull
    @Override
    public Object instantiateItem(@NotNull ViewGroup container, final int position) {

        ItemLayoutBinding itemLayoutBinding = DataBindingUtil.inflate(
                LayoutInflater.from(container.getContext()), R.layout.item_layout, container, true);

        itemLayoutBinding.setViewModel(weatherViewModel);
        itemLayoutBinding.executePendingBindings();

        return itemLayoutBinding;

    }


}
