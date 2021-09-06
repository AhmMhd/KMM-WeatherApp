package com.abdulhakeem.weatherapp_kmm.usecases

import com.abdulhakeem.weatherapp_kmm.model.request.WeatherRequest
import com.abdulhakeem.weatherapp_kmm.repository.weather.WeatherRepository

class FetchWeatherUC {

    private val repository: WeatherRepository = WeatherRepository()

    suspend operator fun invoke(weatherRequest: WeatherRequest) =
        repository.fetchWeather(weatherRequest)

}