package com.balajiprabhu.weatherapp.ui.recyclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.balajiprabhu.weatherapp.databinding.ItemRecyclerviewBinding;
import com.balajiprabhu.weatherapp.view_model.ItemWeatherViewModel;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<ItemWeatherViewModel> itemWeatherViewModelList;

    public RecyclerViewAdapter(List<ItemWeatherViewModel> itemWeatherViewModelList) {
        this.itemWeatherViewModelList = itemWeatherViewModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemRecyclerviewBinding itemRecyclerviewBinding = ItemRecyclerviewBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(itemRecyclerviewBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ItemWeatherViewModel itemWeatherViewModel = itemWeatherViewModelList.get(position);
        holder.bind(itemWeatherViewModel);

    }

    @Override
    public int getItemCount() {
        return itemWeatherViewModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemRecyclerviewBinding itemRecyclerviewBinding;

        public ViewHolder(@NonNull ItemRecyclerviewBinding itemRecyclerviewBinding) {
            super(itemRecyclerviewBinding.getRoot());
            this.itemRecyclerviewBinding = itemRecyclerviewBinding;
        }

        public void bind(ItemWeatherViewModel itemWeatherViewModel) {

            itemRecyclerviewBinding.setViewModel(itemWeatherViewModel);
            itemRecyclerviewBinding.executePendingBindings();
        }
    }

}
