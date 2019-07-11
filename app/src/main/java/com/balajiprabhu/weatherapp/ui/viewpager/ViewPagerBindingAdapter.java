package com.balajiprabhu.weatherapp.ui.viewpager;

import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.viewpager.widget.ViewPager;

import com.squareup.picasso.Picasso;

public class ViewPagerBindingAdapter {

    @BindingAdapter("onPageChangeListener")
    public static void onPageChange(ViewPager viewPager, final PageSelectionListener pageSelectionListener) {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                pageSelectionListener.onPageSelected(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
    }

    @BindingAdapter("android:loadIconUrl")
    public static void loadWeatherIcon(View view, String iconCode) {
        String iconUrl = "http://openweathermap.org/img/w/" + iconCode + ".png";
        ImageView imageView = (ImageView) view;
        Picasso.get().load(iconUrl).into(imageView);
    }

    @FunctionalInterface
    public interface PageSelectionListener {
        void onPageSelected(int position);
    }
}
