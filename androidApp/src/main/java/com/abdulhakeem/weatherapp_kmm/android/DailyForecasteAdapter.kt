package com.abdulhakeem.weatherapp_kmm.android

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.abdulhakeem.weatherapp_kmm.android.databinding.ItemDailyWeatherBinding
import com.abdulhakeem.weatherapp_kmm.model.WeatherData

class DailyForecastAdapter(private val list: List<WeatherData>) :
    RecyclerView.Adapter<DailyForecastAdapter.DailyForecastViewHolder>() {

    inner class DailyForecastViewHolder(private val binding: ItemDailyWeatherBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: WeatherData) {
            binding.data = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyForecastViewHolder =
        DailyForecastViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_daily_weather,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: DailyForecastViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}