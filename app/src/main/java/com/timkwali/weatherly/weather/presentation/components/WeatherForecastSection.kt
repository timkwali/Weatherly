package com.timkwali.weatherly.weather.presentation.components

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.timkwali.weatherly.weather.domain.model.weatherforecast.WeatherForecastState

@Composable
fun WeatherForecastSection(
    weatherForecast: List<WeatherForecastState>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(shape = RoundedCornerShape(20.dp), color = Color.Gray.copy(alpha = 0.3f))
    ) {
        LazyColumn(
            modifier = Modifier.padding(all = 20.dp)
        ) {
            itemsIndexed(weatherForecast) { index, item ->
                WeatherForecastItem(weatherForecastState = item)
            }
        }
    }
}

@Preview
@Composable
fun WeatherForecastSectionPreview() {
    val list = listOf(
        WeatherForecastState("Friday", "88", "24", "27", "Rain", "https://openweathermap.org/img/wn/10d@2x.png"),
        WeatherForecastState("Friday", "88", "24", "27", "Rain", "https://openweathermap.org/img/wn/10d@2x.png"),
        WeatherForecastState("Friday", "88", "24", "27", "Rain", "https://openweathermap.org/img/wn/10d@2x.png"),
        WeatherForecastState("Friday", "88", "24", "27", "Rain", "https://openweathermap.org/img/wn/10d@2x.png"),
        WeatherForecastState("Friday", "88", "24", "27", "Rain", "https://openweathermap.org/img/wn/10d@2x.png"),
        WeatherForecastState("Friday", "88", "24", "27", "Rain", "https://openweathermap.org/img/wn/10d@2x.png"),
        WeatherForecastState("Friday", "88", "24", "27", "Rain", "https://openweathermap.org/img/wn/10d@2x.png"),
    )
    WeatherForecastSection(weatherForecast = list)
}