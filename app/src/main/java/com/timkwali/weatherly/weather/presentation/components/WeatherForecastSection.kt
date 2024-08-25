package com.timkwali.weatherly.weather.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.timkwali.weatherly.weather.data.local.model.weatherforecast.WeatherForecastState

@Composable
fun WeatherForecastSection(
    weatherForecast: List<WeatherForecastState>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(shape = RoundedCornerShape(20.dp), color = MaterialTheme.colorScheme.surface.copy(alpha = 0.3f))
    ) {
        Column(
            modifier = Modifier
                .padding(all = 20.dp)
        ) {
            repeat(weatherForecast.size) {
                if(it < 6) {
                    WeatherForecastItem(weatherForecastState = weatherForecast[it])
                }
            }
        }
    }
}

@Preview
@Composable
fun WeatherForecastSectionPreview() {
    val list = listOf(
        WeatherForecastState(0, "Friday", "88", "24", "27", "https://openweathermap.org/img/wn/10d@2x.png"),
        WeatherForecastState(0, "Friday", "88", "24", "27", "https://openweathermap.org/img/wn/10d@2x.png"),
        WeatherForecastState(0, "Friday", "88", "24", "27", "https://openweathermap.org/img/wn/10d@2x.png"),
        WeatherForecastState(0, "Friday", "88", "24", "27", "https://openweathermap.org/img/wn/10d@2x.png"),
        WeatherForecastState(0, "Friday", "88", "24", "27", "https://openweathermap.org/img/wn/10d@2x.png"),
        WeatherForecastState(0, "Friday", "88", "24", "27", "https://openweathermap.org/img/wn/10d@2x.png"),
        WeatherForecastState(0, "Friday", "88", "24", "27", "https://openweathermap.org/img/wn/10d@2x.png"),
    )
    WeatherForecastSection(weatherForecast = list)
}