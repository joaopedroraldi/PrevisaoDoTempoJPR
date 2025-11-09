package com.previsaodotempojpr;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {

    private List<Forecast> forecastList;

    public WeatherAdapter(List<Forecast> forecastList) {
        this.forecastList = forecastList;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_list_item, parent, false);
        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        Forecast forecast = forecastList.get(position);
        holder.dateTextView.setText(forecast.getDate());
        holder.descriptionTextView.setText(forecast.getDescription());
        holder.maxTempTextView.setText("Max: " + forecast.getMaxTemp() + "°C");
        holder.minTempTextView.setText("Min: " + forecast.getMinTemp() + "°C");
    }

    @Override
    public int getItemCount() {
        return forecastList.size();
    }

    static class WeatherViewHolder extends RecyclerView.ViewHolder {
        TextView dateTextView;
        TextView descriptionTextView;
        TextView maxTempTextView;
        TextView minTempTextView;

        public WeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.date_textview);
            descriptionTextView = itemView.findViewById(R.id.description_textview);
            maxTempTextView = itemView.findViewById(R.id.max_temp_textview);
            minTempTextView = itemView.findViewById(R.id.min_temp_textview);
        }
    }
}