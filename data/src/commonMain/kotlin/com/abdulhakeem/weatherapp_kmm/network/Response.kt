package com.abdulhakeem.weatherapp_kmm.network

sealed class Response {
    class Success<T>(val response: T) : Response()
    class Failure(val exception: Throwable) : Response()
}
