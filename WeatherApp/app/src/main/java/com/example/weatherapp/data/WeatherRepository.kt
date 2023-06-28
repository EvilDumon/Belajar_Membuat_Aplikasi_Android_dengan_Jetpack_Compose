package com.example.weatherapp.data

import androidx.lifecycle.LiveData
import com.example.weatherapp.data.local.room.BookmarkDao
import com.example.weatherapp.data.local.entity.BookmarkEntity
import com.example.weatherapp.data.remote.response.CurrentWeatherResponse
import com.example.weatherapp.data.remote.retrofit.ApiConfig
import com.example.weatherapp.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class WeatherRepository private constructor(
    private val bookmarkDao: BookmarkDao,
    private val appExecutors: AppExecutors
) {
    private val weatherData = mutableListOf<CurrentWeatherResponse>()

    fun clearWeatherData() {
        weatherData.clear()
    }

    suspend fun getWeatherData(
        cityName: String,
        airQuality: String
    ): Flow<CurrentWeatherResponse> {
        val api = ApiConfig.getApiService().getCurrentWeather(cityName, airQuality)
        weatherData.add(api)

        return flowOf(api)
    }

    fun getBookmarkCity(): LiveData<List<BookmarkEntity>> {
        return bookmarkDao.getBookmarkCity()
    }

    fun getBookmarkCity(city: String): LiveData<BookmarkEntity> {
        return bookmarkDao.getBookmarkCity(city)
    }

    fun insertBookmarkCity(city: BookmarkEntity) {
        appExecutors.diskIO.execute {
            bookmarkDao.insertCity(city)
        }
    }

    fun deleteBookmarkCity(city: String) {
        appExecutors.diskIO.execute {
            bookmarkDao.deleteCity(city)
        }
    }

    companion object {
        @Volatile
        private var instance: WeatherRepository? = null

        fun getInstance(
            usersDao: BookmarkDao,
            appExecutors: AppExecutors
        ): WeatherRepository =
            instance ?: synchronized(this) {
                instance ?: WeatherRepository(usersDao, appExecutors)
            }.also { instance = it }
    }
}