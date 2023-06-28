package com.example.weatherapp.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.remote.response.CurrentWeatherResponse
import com.example.weatherapp.data.WeatherRepository
import com.example.weatherapp.data.local.entity.BookmarkEntity
import com.example.weatherapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: WeatherRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<CurrentWeatherResponse>>> = MutableStateFlow(
        UiState.Loading)
    val uiState: StateFlow<UiState<List<CurrentWeatherResponse>>> = _uiState

//    fun getWeatherData(cityName: String, airQuality: String = "no"){
//        viewModelScope.launch {
//            repository.getWeatherData(cityName, airQuality)
//                .catch {
//                    _uiState.value = UiState.Error(it.message.toString())
//                }
//                .collect { data ->
//                    _uiState.value = UiState.Success(data)
//                }
//        }
//    }

    fun isBookmarkCity(cityName: String) = repository.getBookmarkCity(cityName)

    fun saveCity(cityName: BookmarkEntity) = repository.insertBookmarkCity(cityName)

    fun deleteCity(cityName: String) = repository.deleteBookmarkCity(cityName)
}