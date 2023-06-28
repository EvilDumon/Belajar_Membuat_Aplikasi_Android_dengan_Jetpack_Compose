package com.example.weatherapp.di

import android.content.Context
import com.example.weatherapp.data.WeatherRepository
import com.example.weatherapp.data.local.room.BookmarkDatabase
import com.example.weatherapp.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): WeatherRepository {
        val database = BookmarkDatabase.getInstance(context)
        val dao = database.BookmarkDao()
        val appExecutors = AppExecutors()
        return WeatherRepository.getInstance(dao, appExecutors)
    }
}