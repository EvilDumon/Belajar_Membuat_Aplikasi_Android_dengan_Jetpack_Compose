package com.example.weatherapp.ui.screen.detail

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.ui.components.ForecastDaysCard
import com.example.weatherapp.ui.components.ForecastTodayCard
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.example.weatherapp.ui.theme.bg_cloudy
import com.example.weatherapp.utils.gradientBackground

@Composable
fun DetailScreen(
    cityName: String,
    currentCity : String,
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
) {
    Log.i("DetailWeather", "DetailWeather: $cityName")

    var isForecastToday by remember { mutableStateOf(true) }
    var isBookmarked by remember { mutableStateOf(false) }
    var isUserCity by remember { mutableStateOf(false) }

    if (currentCity == "Kota $cityName") isUserCity = true
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        MainCard(
            navigateBack = navigateBack,
            isUserCity = isUserCity,
            isBookmarked = isBookmarked,
            onCheckChanged = { isBookmarked = it }
        )
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(40.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = modifier.clickable { isForecastToday = true },
                text = "Today",
                style = MaterialTheme.typography.h6
            )
            Text(
                modifier = modifier.clickable { isForecastToday = false },
                text = "7 Days",
                style = MaterialTheme.typography.h6
            )
        }
        LazyColumn(
            modifier = modifier,
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (isForecastToday) {
                items(5) {
                    ForecastTodayCard()
                }
            } else {
                items(5) {
                    ForecastDaysCard()
                }
            }
        }
    }
}

@Composable
fun MainCard(
    modifier: Modifier = Modifier,
    isUserCity: Boolean,
    isBookmarked: Boolean,
    navigateBack: () -> Unit,
    onCheckChanged: (Boolean) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(570.dp),
        elevation = 5.dp,
        border = BorderStroke(1.dp, Color(0x80C5C5C5)),
        shape = RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp)
    ) {
        Box(modifier = modifier.gradientBackground(bg_cloudy, 45F))
        Column(
            modifier = modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Header(
                isUserCity = isUserCity,
                navigateBack = navigateBack,
                isBookmarked = isBookmarked,
                onCheckChanged = onCheckChanged
            )
            WeatherInCard()
            Text(
                text = "20°",
                style = MaterialTheme.typography.h3.copy(fontStyle = FontStyle.Italic)
            )
            GridWeatherComponent()
        }
    }
}

@Composable
fun Header(
    modifier: Modifier = Modifier,
    isUserCity: Boolean,
    isBookmarked: Boolean,
    navigateBack: () -> Unit,
    onCheckChanged: (Boolean) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = modifier.clickable { navigateBack() },
            imageVector = Icons.Rounded.ArrowBack,
            contentDescription = "Back",
        )
        Row(
            modifier = modifier.weight(1F),
            horizontalArrangement = Arrangement.Center
        ){
            if (isUserCity) {
                Image(
                    modifier = modifier.size(30.dp),
                    painter = painterResource(R.drawable.location),
                    contentDescription = null
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Semarang",
                    style = MaterialTheme.typography.body1
                )
                Text(
                    text = "Jawa Tengah, Indonesia",
                    style = MaterialTheme.typography.body2
                )
            }
        }
        IconToggleButton(
            checked = isBookmarked,
            onCheckedChange = { onCheckChanged(it) }
        ) {
            Icon(
                modifier = modifier.size(32.dp),
                imageVector = if (isBookmarked) {
                    Icons.Rounded.Bookmark
                } else {
                    Icons.Rounded.BookmarkBorder
                },
                contentDescription = "Bookmark"
            )
        }
    }
}

@Composable
fun WeatherInCard(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = modifier.size(280.dp),
            painter = painterResource(R.drawable.ic_cloudy),
            contentDescription = null,
        )
        Text(
            modifier = modifier,
            text = "Cloudy",
            style = MaterialTheme.typography.h4,
        )
    }
}

@Composable
fun GridWeatherComponent(modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        modifier = modifier.fillMaxSize(),
        columns = GridCells.Fixed(4),
        verticalArrangement = Arrangement.Center
    ) {
        item {
            GridComponents(icon = R.drawable.ic_barometer, text = "air pressure", value = "20")
        }
        item {
            GridComponents(icon = R.drawable.ic_humidity, text = "humidity", value = "20")
        }
        item {
            GridComponents(icon = R.drawable.ic_wind_speed, text = "wind speed", value = "20")
        }
        item {
            GridComponents(icon = R.drawable.ic_sunny, text = "uv index", value = "5")
        }
    }
}

@Composable
fun GridComponents(
    modifier: Modifier = Modifier,
    icon: Int,
    text: String,
    value: String
) {
    Column(
        modifier = modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = value, style = MaterialTheme.typography.h6)
        Image(
            modifier = modifier.size(36.dp),
            painter = painterResource(icon),
            contentDescription = null
        )
        Text(text = text, style = MaterialTheme.typography.body1)
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = 0)
@Composable
fun DetailWeatherPreview() {
    WeatherAppTheme {
        DetailScreen(cityName = "Semarang", currentCity = "Kota Semarang") {}
    }
}
@Preview(showBackground = true, showSystemUi = true, uiMode = 0)
@Composable
fun DetailWeatherPreview2() {
    WeatherAppTheme {
        DetailScreen(cityName = "Semarang", currentCity = "Kota Solo") {}
    }
}