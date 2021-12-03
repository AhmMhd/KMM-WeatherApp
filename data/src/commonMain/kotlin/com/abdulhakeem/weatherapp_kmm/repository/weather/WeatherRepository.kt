package com.abdulhakeem.weatherapp_kmm.repository.weather

import com.abdulhakeem.weatherapp_kmm.model.request.DailyForecastRequest
import com.abdulhakeem.weatherapp_kmm.model.request.WeatherRequest
import com.abdulhakeem.weatherapp_kmm.network.WeatherAPIServices

class WeatherRepository{
    private val remote: WeatherRemoteSource = WeatherRemoteSource()
    private val local: WeatherLocalSource = WeatherLocalSource()

    suspend fun fetchWeather(weatherRequest: WeatherRequest) =
        remote.fetchWeather(weatherRequest)

    suspend fun fetchDailyForecast(dailyForecastRequest: DailyForecastRequest) =
        remote.fetchDailyForecast(dailyForecastRequest)
}