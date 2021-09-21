package com.abdulhakeem.weatherapp_kmm.usecases

import com.abdulhakeem.weatherapp_kmm.fetchWeatherIconUrl
import com.abdulhakeem.weatherapp_kmm.getDay
import com.abdulhakeem.weatherapp_kmm.getDayOfWeek
import com.abdulhakeem.weatherapp_kmm.kelvinToCelsius
import com.abdulhakeem.weatherapp_kmm.mapper.mapToPresentationModel
import com.abdulhakeem.weatherapp_kmm.model.DailyForecast
import com.abdulhakeem.weatherapp_kmm.model.WeatherData
import com.abdulhakeem.weatherapp_kmm.model.request.DailyForecastRequest
import com.abdulhakeem.weatherapp_kmm.model.response.DailyForecastResponse
import com.abdulhakeem.weatherapp_kmm.network.Response
import com.abdulhakeem.weatherapp_kmm.repository.weather.WeatherRepository

class FetchDailyForecastUC {

    private val repository: WeatherRepository = WeatherRepository()

    suspend operator fun invoke(dailyForecastRequest: DailyForecastRequest): Response {
        return when (val response = repository.fetchDailyForecast(dailyForecastRequest)) {
            is Response.Success<*> -> {
                val response = response.response as DailyForecastResponse
                Response.Success(response.mapToPresentationModel())
            }
            else -> {
                response
            }
        }
    }

}