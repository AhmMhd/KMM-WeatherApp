package com.abdulhakeem.weatherapp_kmm.android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdulhakeem.weatherapp_kmm.model.DailyForecast
import com.abdulhakeem.weatherapp_kmm.model.request.DailyForecastRequest
import com.abdulhakeem.weatherapp_kmm.network.Response
import com.abdulhakeem.weatherapp_kmm.usecases.FetchDailyForecastUC
import kotlinx.coroutines.launch

class MainActivityViewmodel : ViewModel() {

    private val forecast = MutableLiveData<DailyForecast>()
    val obForecast: LiveData<DailyForecast> = forecast

    fun fetchForecast() {
        viewModelScope.launch {
            val res =
                FetchDailyForecastUC().invoke(DailyForecastRequest(lat = 24.8607, lng = 67.0011))
            when (res) {
                is Response.Success<*> -> {
                    val data = res.response as DailyForecast
                    forecast.value = data
                }
                else -> {
                }
            }
        }
    }
}