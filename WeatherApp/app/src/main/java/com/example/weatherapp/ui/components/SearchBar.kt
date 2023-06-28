package com.example.weatherapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    state: MutableState<TextFieldValue>,
) {
    TextField(
        modifier = modifier.fillMaxWidth(),
        value = state.value ,
        onValueChange = { state.value = it },
        shape = RoundedCornerShape(10.dp)
    )
}