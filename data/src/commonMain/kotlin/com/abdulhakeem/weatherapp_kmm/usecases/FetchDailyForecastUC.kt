package com.abdulhakeem.weatherapp_kmm.usecases

import com.abdulhakeem.weatherapp_kmm.fetchWeatherIconUrl
import com.abdulhakeem.weatherapp_kmm.getDay
import com.abdulhakeem.weatherapp_kmm.getDayOfWeek
import com.abdulhakeem.weatherapp_kmm.kelvinToCelsius
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
                val today = WeatherData(
                    response.current.sunrise,
                    response.current.sunset,
                    response.current.temp.kelvinToCelsius(),
                    response.current.feels_like.kelvinToCelsius(),
                    description = response.current.weather.firstOrNull()?.description ?: "",
                    icon = response.current.weather.firstOrNull()?.icon?.fetchWeatherIconUrl()
                        ?: "",
                    day = response.current.dt.getDay()
                )
                val dailyWeatherData = ArrayList<WeatherData>()
                response.daily.forEach {
                    dailyWeatherData.add(
                        WeatherData(
                            it.sunrise,
                            it.sunset,
                            it.temp.day.kelvinToCelsius(),
                            it.feels_like.day.kelvinToCelsius(),
                            description = "",
                            icon = it.weather.firstOrNull()?.icon?.fetchWeatherIconUrl() ?: "",
                            day = it.dt.getDayOfWeek()
                        )
                    )
                }
                val forecast = DailyForecast(response.timezone, today, dailyWeatherData)

                Response.Success(forecast)
            }
            else -> {
                response
            }
        }
    }

}