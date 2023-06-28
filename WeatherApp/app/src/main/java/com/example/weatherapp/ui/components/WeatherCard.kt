package com.example.weatherapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.*
import com.example.weatherapp.utils.doubleToInt
import com.example.weatherapp.utils.gradientBackground

@Composable
fun WeatherCard(
    modifier: Modifier = Modifier,
    currentCity: String,
    cityName: String,
    status: String,
    tempC: Double,
) {
    var isUserCity by remember { mutableStateOf(false) }
    if (currentCity == "Kota $cityName") isUserCity = true

    val icon: Painter
    val background: List<Color>
    when (status) {
        "Sunny" -> {
            icon = painterResource(R.drawable.ic_sunny)
            background = bg_sunny
        }
        "Light rain" -> {
            icon = painterResource(R.drawable.ic_rainy)
            background = bg_rainy
        }
        "Cloudy" -> {
            icon = painterResource(R.drawable.cloudy2)
            background = bg_cloudy
        }
        "Drizzly" -> {
            icon = painterResource(R.drawable.ic_drizzly)
            background = bg_drizzly
        }
        "Light rain shower" -> {
            icon = painterResource(R.drawable.ic_sun_shower)
            background = bg_sun_shower
        }
        "Storm" -> {
            icon = painterResource(R.drawable.ic_storm)
            background = bg_storm
        }
        "Mist" -> {
            icon = painterResource(R.drawable.ic_foggy)
            background = bg_mist
        }
        else -> {
            icon = painterResource(R.drawable.ic_partly_cloudy)
            background = bg_partly_cloudy
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(110.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .height(100.dp),
            elevation = 5.dp,
            border = BorderStroke(1.dp, Color(0x80C5C5C5)),
            shape = RoundedCornerShape(20.dp)
        ) {
            Box(
                modifier = modifier.gradientBackground(background, 90f)
            )

            Column(
                modifier = modifier.padding(horizontal = 20.dp, vertical = 16.dp),
            ) {
                Text(
                    text = cityName,
                    style = MaterialTheme.typography.h5
                )
                Text(
                    text = status,
                    style = MaterialTheme.typography.h6
                )
            }
        }
        if (isUserCity) {
            Image(
                modifier = modifier
                    .size(30.dp)
                    .align(Alignment.TopStart),
                painter = painterResource(R.drawable.location),
                contentDescription = null
            )
        }
        Image(
            modifier = modifier
                .size(100.dp)
                .align(Alignment.TopEnd)
                .offset(x = (-20).dp),
            painter = icon,
            contentDescription = null,
        )
        Card(
            modifier = modifier
                .width(70.dp)
                .height(30.dp)
                .align(Alignment.BottomCenter),
            elevation = 0.dp,
            backgroundColor = Color(0x66FFFFFF),
            border = BorderStroke(0.3.dp,Color(0x80A3A3A3)),
            shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp, bottomStart = 8.dp, bottomEnd = 8.dp)
        ) {
            Text(
                modifier = modifier.padding(top = 5.dp),
                text = "${doubleToInt(tempC)}℃",
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun WeatherCardPreview() {
    WeatherAppTheme {
        WeatherCard(cityName = "Banda Aceh", status = "Sunny", tempC = 28.2, currentCity = "Kota Semarang")
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun WeatherCardPreview2() {
    WeatherAppTheme {
        WeatherCard(cityName = "Semarang", status = "Rainy", tempC = 28.2, currentCity = "Kota Semarang")
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun WeatherCardPreview3() {
    WeatherAppTheme {
        WeatherCard(cityName = "Surabaya", status = "Cloudy", tempC = 28.2, currentCity = "Kota Semarang")
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun WeatherCardPreview4() {
    WeatherAppTheme {
        WeatherCard(cityName = "Makassar", status = "Storm", tempC = 28.2, currentCity = "Kota Semarang")
    }
}
@Preview(showBackground = true, widthDp = 320)
@Composable
fun WeatherCardPreview5() {
    WeatherAppTheme {
        WeatherCard(cityName = "Cirebon", status = "Drizzly", tempC = 28.2, currentCity = "Kota Semarang")
    }
}
@Preview(showBackground = true, widthDp = 320)
@Composable
fun WeatherCardPreview6() {
    WeatherAppTheme {
        WeatherCard(cityName = "Jakarta", status = "Partly Sunny", tempC = 28.2, currentCity = "Kota Semarang")
    }
}
@Preview(showBackground = true, widthDp = 320)
@Composable
fun WeatherCardPreview7() {
    WeatherAppTheme {
        WeatherCard(cityName = "Bali", status = "SunShower", tempC = 28.2, currentCity = "Kota Semarang")
    }
}
@Preview(showBackground = true, widthDp = 320)
@Composable
fun WeatherCardPreview8() {
    WeatherAppTheme {
        WeatherCard(cityName = "Jayapura", status = "Foggy", tempC = 28.2, currentCity = "Kota Semarang")
    }
}