package com.example.weatherapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.lightmode_primary_bg
import com.example.weatherapp.ui.theme.lightmode_secondary_bg

@Preview(widthDp = 320)
@Composable
fun ForecastTodayCard(
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        elevation = 1.dp,
        border = BorderStroke(1.dp, Color(0x80C5C5C5)),
        backgroundColor = lightmode_secondary_bg,
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "12.00 PM",
                style = MaterialTheme.typography.body1
            )
            Row (
                verticalAlignment = Alignment.CenterVertically
                    ){
                Image(
                    modifier = modifier.size(40.dp),
                    painter = painterResource(R.drawable.ic_sunny),
                    contentDescription = null,
                )
                Text(
                    text = "Sunny",
                    style = MaterialTheme.typography.body1
                )
            }
            Text(
                text = "20°",
                style = MaterialTheme.typography.body1
            )
        }
    }
}

@Preview(widthDp = 320)
@Composable
fun ForecastDaysCard(
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        elevation = 1.dp,
        border = BorderStroke(1.dp, Color(0x80C5C5C5)),
        backgroundColor = lightmode_secondary_bg,
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Monday",
                style = MaterialTheme.typography.body1
            )
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(
                    modifier = modifier.size(40.dp),
                    painter = painterResource(R.drawable.ic_sunny),
                    contentDescription = null,
                )
                Text(
                    text = "Sunny",
                    style = MaterialTheme.typography.body1
                )
            }
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(3.dp)
                ){
                    Text(
                        text = "30°",
                        color = Color(0xFFC34242),
                        style = MaterialTheme.typography.body1
                    )
                    Icon(
                        modifier = modifier.size(16.dp),
                        imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_up),
                        contentDescription = null,
                        tint = Color(0xFFC34242)
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(3.dp)
                ){
                    Text(
                        text = "20°",
                        color = Color(0xFF64C342),
                        style = MaterialTheme.typography.body1
                    )
                    Icon(
                        modifier = modifier.size(16.dp),
                        imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_down),
                        contentDescription = null,
                        tint = Color(0xFF64C342)
                    )
                }
            }
        }
    }
}