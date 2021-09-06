package com.abdulhakeem.weatherapp_kmm.network

import com.abdulhakeem.weatherapp_kmm.model.request.DailyForecastRequest
import com.abdulhakeem.weatherapp_kmm.model.request.WeatherRequest
import com.abdulhakeem.weatherapp_kmm.model.response.DailyForecastResponse
import com.abdulhakeem.weatherapp_kmm.model.response.WeatherResponse
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.flow.flow

object WeatherAPIServices {
    private val client = HttpClient() {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    @Throws(Exception::class)
    suspend fun fetchWeather(weatherRequest: WeatherRequest): Response {
        return try {
            val response = client.request<WeatherResponse>(Endpoints.WEATHER) {
                method = HttpMethod.Get
                parameter("q", weatherRequest.city)
                parameter("appid", "635d420636f26f8d801ec8bf19c47cde")
            }
            Response.Success(response)
        } catch (e: Throwable) {
            Response.Failure(e)
        }
    }

    @Throws(Exception::class)
    suspend fun fetchDailyForecast(dailyForecastRequest: DailyForecastRequest): Response {
        return try {
            val response = client.request<DailyForecastResponse>(Endpoints.DAILY_FORECAST) {
                method = HttpMethod.Get
                parameter("lat", dailyForecastRequest.lat)
                parameter("lon", dailyForecastRequest.lng)
                parameter("exclude", "hourly,minutely")
                parameter("appid", "635d420636f26f8d801ec8bf19c47cde")
            }
            Response.Success(response)
        } catch (e: Throwable) {
            Response.Failure(e)
        }
    }

}