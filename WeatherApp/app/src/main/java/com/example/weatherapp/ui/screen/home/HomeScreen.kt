package com.example.weatherapp.ui.screen.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.data.local.entity.BookmarkEntity
import com.example.weatherapp.data.remote.response.CurrentWeatherResponse
import com.example.weatherapp.di.Injection
import com.example.weatherapp.ui.ViewModelFactory
import com.example.weatherapp.ui.components.WeatherCard
import com.example.weatherapp.ui.common.UiState

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
    ),
    currentCity : String,
    navigateToDetail: (String) -> Unit,
) {
    val items = ArrayList<BookmarkEntity>()
    val bookmarkCity: List<BookmarkEntity> by viewModel.bookmarkCity
    val listResponse: List<CurrentWeatherResponse> by viewModel.listResponse

    LaunchedEffect(Unit){
        viewModel.getBookmarkCity()
    }

    if (bookmarkCity.isNotEmpty()) {
        items.clear()
        UiState.Success(emptyList<CurrentWeatherResponse>())
        viewModel.clearListResponse()
        bookmarkCity.map {
            val item = BookmarkEntity(it.cityName)
            items.add(item)
        }
//        Dah bener
    }

    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.clearWeatherData()
                items.map {
                    viewModel.getWeatherData(it.cityName)
                }
            }

            is UiState.Success -> {
                LazyColumn(
                    contentPadding = PaddingValues(56.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(uiState.data) {
                        WeatherCard(
                            currentCity = currentCity,
                            cityName = it.location.name,
                            status = it.current.condition.text,
                            tempC = it.current.tempC,
                            modifier = modifier.clickable {
                                navigateToDetail(it.location.name)
                            }
                        )
                    }
                }
            }

            is UiState.Error -> {
                Log.i("HomeScreen", "onCreate: UiState.Error")
            }
        }
    }
}