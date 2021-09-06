package com.abdulhakeem.weatherapp_kmm.repository.weather

import com.abdulhakeem.weatherapp_kmm.model.request.DailyForecastRequest
import com.abdulhakeem.weatherapp_kmm.model.request.WeatherRequest
import com.abdulhakeem.weatherapp_kmm.network.WeatherAPIServices

class WeatherRemoteSource {

    suspend fun fetchWeather(weatherRequest: WeatherRequest) =
        WeatherAPIServices.fetchWeather(weatherRequest)

    suspend fun fetchDailyForecast(dailyForecastRequest: DailyForecastRequest) =
        WeatherAPIServices.fetchDailyForecast(dailyForecastRequest)
}