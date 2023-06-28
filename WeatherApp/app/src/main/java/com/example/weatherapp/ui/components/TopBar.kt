package com.example.weatherapp.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.ui.theme.lightmode_secondary_bg

@Preview
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            Text(
                text = "PACU",
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.Medium
                )
            )
        },
        backgroundColor = lightmode_secondary_bg,
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    modifier = modifier.size(30.dp),
                    imageVector = Icons.Rounded.Settings,
                    contentDescription = "Setting"
                )
            }
        }
    )
}
