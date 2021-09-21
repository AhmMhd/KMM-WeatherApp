package com.abdulhakeem.weatherapp_kmm.mapper

import com.abdulhakeem.weatherapp_kmm.fetchWeatherIconUrl
import com.abdulhakeem.weatherapp_kmm.getDay
import com.abdulhakeem.weatherapp_kmm.getDayOfWeek
import com.abdulhakeem.weatherapp_kmm.kelvinToCelsius
import com.abdulhakeem.weatherapp_kmm.model.DailyForecast
import com.abdulhakeem.weatherapp_kmm.model.WeatherData
import com.abdulhakeem.weatherapp_kmm.model.response.DailyForecastResponse

fun DailyForecastResponse.mapToPresentationModel(): DailyForecast {
    val today = WeatherData(
        sunrise = current.sunrise,
        sunset = current.sunset,
        temp = current.temp.kelvinToCelsius(),
        feels_like = current.feels_like.kelvinToCelsius(),
        description = current.weather.firstOrNull()?.description ?: "",
        icon = current.weather.firstOrNull()?.icon?.fetchWeatherIconUrl()
            ?: "",
        day = current.dt.getDay()
    )
    val dailyWeatherData = ArrayList<WeatherData>()

    daily.forEach {
        dailyWeatherData.add(
            WeatherData(
                sunrise = it.sunrise,
                sunset = it.sunset,
                temp = it.temp.day.kelvinToCelsius(),
                feels_like = it.feels_like.day.kelvinToCelsius(),
                description = "",
                icon = it.weather.firstOrNull()?.icon?.fetchWeatherIconUrl() ?: "",
                day = it.dt.getDayOfWeek()
            )
        )
    }
    return DailyForecast(timezone, today, dailyWeatherData)
}