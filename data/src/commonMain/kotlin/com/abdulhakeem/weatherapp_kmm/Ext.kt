package com.abdulhakeem.weatherapp_kmm

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.math.roundToInt

fun Double.kelvinToCelsius() = (this - 273.15).roundToInt().toString()

fun String.fetchWeatherIconUrl() = "https://openweathermap.org/img/wn/${this}@2x.png"

fun Long.getDayOfWeek() =
    Instant.fromEpochSeconds(this)
        .toLocalDateTime(TimeZone.currentSystemDefault()).dayOfWeek.toString()

fun Long.getDay() = "${
    Instant.fromEpochSeconds(this).toLocalDateTime(TimeZone.currentSystemDefault()).date.dayOfMonth
} ${
    Instant.fromEpochSeconds(this).toLocalDateTime(TimeZone.currentSystemDefault()).date.month.name
}"