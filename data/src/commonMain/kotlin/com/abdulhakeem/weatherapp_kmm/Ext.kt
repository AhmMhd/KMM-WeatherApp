package com.abdulhakeem.weatherapp_kmm

import kotlin.math.roundToInt

fun Double.kelvinToCelsius() = (this - 273.15).roundToInt().toString()

fun String.fetchWeatherIconUrl() = "https://openweathermap.org/img/wn/${this}@2x.png"