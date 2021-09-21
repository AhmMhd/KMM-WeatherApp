package com.abdulhakeem.weatherapp_kmm.model

data class DailyForecast(
    val timezone: String = "",
    val todayWeatherData: WeatherData? = null,
    val dailyWeatherData: List<WeatherData>? = null
)

data class WeatherData(
    val sunrise: Long? = null,
    val sunset: Long? = null,
    val temp: String? = null,
    val feels_like: String? = null,
    val description: String?= null,
    val icon : String?=null,
    var day : String
)