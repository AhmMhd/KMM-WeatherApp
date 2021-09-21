package com.abdulhakeem.weatherapp_kmm.android

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.abdulhakeem.weatherapp_kmm.android.databinding.ActivityMainBinding
import com.abdulhakeem.weatherapp_kmm.model.DailyForecast
import com.abdulhakeem.weatherapp_kmm.model.WeatherData
import com.abdulhakeem.weatherapp_kmm.model.request.DailyForecastRequest
import com.abdulhakeem.weatherapp_kmm.network.Response
import com.abdulhakeem.weatherapp_kmm.usecases.FetchDailyForecastUC
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        GlobalScope.launch(Dispatchers.Main) {
            val res =
                FetchDailyForecastUC().invoke(DailyForecastRequest(lat = 24.8607, lng = 67.0011))
            when (res) {
                is Response.Success<*> -> {
                    val data = res.response as DailyForecast
                    binding.data = data
                    daysAdapter(data.dailyWeatherData ?: emptyList())
                }
                else -> {
                }
            }
        }
    }

    private fun daysAdapter(list: List<WeatherData>) {
        binding.list.adapter = DailyForecastAdapter(list)
    }
}
