package com.example.weatherapp.data.remote.retrofit

import com.example.weatherapp.data.remote.response.CurrentWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("current.json?key=5770daaac41b4a6ab8031239232305")
    suspend fun getCurrentWeather(
        @Query("q") q: String,
        @Query("aqi") aqi: String,
        @Query("days") days: Int? = null,
        @Query("alerts") alerts: String? = null
    ) : CurrentWeatherResponse
}