package com.example.weatherapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R

val Ubuntu = FontFamily(
    Font(R.font.ubuntu_regular),
    Font(R.font.ubuntu_medium, Medium),
    Font(R.font.ubuntu_bold, Bold),
    Font(R.font.ubuntu_italic, style = Italic),
)

val LightTypography = Typography(
    h1 = TextStyle(
        fontFamily = Ubuntu,
        fontWeight = Bold,
        fontSize = 60.sp,
        color = lightmode_primary_text
    ),
    h2 = TextStyle(
        fontFamily = Ubuntu,
        fontWeight = Bold,
        fontSize = 52.sp,
        color = lightmode_primary_text
    ),
    h3 = TextStyle(
        fontFamily = Ubuntu,
        fontWeight = Bold,
        fontSize = 44.sp,
        color = lightmode_primary_text
    ),
    h4 = TextStyle(
        fontFamily = Ubuntu,
        fontWeight = Bold,
        fontSize = 36.sp,
        color = lightmode_primary_text
    ),
    h5 = TextStyle(
        fontFamily = Ubuntu,
        fontWeight = Bold,
        fontSize = 28.sp,
        color = lightmode_primary_text
    ),
    h6 = TextStyle(
        fontFamily = Ubuntu,
        fontWeight = Bold,
        fontSize = 20.sp,
        color = lightmode_primary_text
    ),
    body1 = TextStyle(
        fontFamily = Ubuntu,
        fontWeight = Normal,
        fontSize = 16.sp,
        color = lightmode_primary_text
    ),
    body2 = TextStyle(
        fontFamily = Ubuntu,
        fontStyle = Italic,
        fontSize = 12.sp,
        color = lightmode_primary_text
    ),
)

val DarkTypography = Typography(
    h1 = TextStyle(
        fontFamily = Ubuntu,
        fontWeight = Bold,
        fontSize = 60.sp,
        color = darkmode_primary_text
    ),
    h2 = TextStyle(
        fontFamily = Ubuntu,
        fontWeight = Bold,
        fontSize = 52.sp,
        color = darkmode_primary_text
    ),
    h3 = TextStyle(
        fontFamily = Ubuntu,
        fontWeight = Bold,
        fontSize = 44.sp,
        color = darkmode_primary_text
    ),
    h4 = TextStyle(
        fontFamily = Ubuntu,
        fontWeight = Bold,
        fontSize = 36.sp,
        color = darkmode_primary_text
    ),
    h5 = TextStyle(
        fontFamily = Ubuntu,
        fontWeight = Bold,
        fontSize = 28.sp,
        color = darkmode_primary_text
    ),
    h6 = TextStyle(
        fontFamily = Ubuntu,
        fontWeight = Bold,
        fontSize = 20.sp,
        color = darkmode_primary_text
    ),
    body1 = TextStyle(
        fontFamily = Ubuntu,
        fontWeight = Normal,
        fontSize = 16.sp,
        color = darkmode_primary_text
    ),
    body2 = TextStyle(
        fontFamily = Ubuntu,
        fontStyle = Italic,
        fontSize = 12.sp,
        color = darkmode_primary_text
    ),
)