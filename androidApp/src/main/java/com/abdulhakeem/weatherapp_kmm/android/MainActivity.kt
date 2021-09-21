package com.abdulhakeem.weatherapp_kmm.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.abdulhakeem.weatherapp_kmm.android.databinding.ActivityMainBinding
import com.abdulhakeem.weatherapp_kmm.model.WeatherData

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainActivityViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainActivityViewmodel::class.java)
        viewModel.fetchForecast()
        viewModel.obForecast.observe(this) {
            binding.data = it
            daysAdapter(it.dailyWeatherData ?: emptyList())

        }
    }

    private fun daysAdapter(list: List<WeatherData>) {
        binding.list.adapter = DailyForecastAdapter(list)
    }
}
