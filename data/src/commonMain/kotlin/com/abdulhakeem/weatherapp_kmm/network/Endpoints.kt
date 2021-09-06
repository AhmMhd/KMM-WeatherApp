package com.abdulhakeem.weatherapp_kmm.network

object Endpoints {
    private const val BASE_URL = "http://api.openweathermap.org/"
    private const val V2_5 = "${BASE_URL}data/2.5/"
    const val WEATHER = "${V2_5}weather"
    const val DAILY_FORECAST = "${V2_5}onecall"
}