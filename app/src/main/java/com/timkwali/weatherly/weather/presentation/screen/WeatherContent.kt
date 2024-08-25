package com.timkwali.weatherly.weather.presentation.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.timkwali.weatherly.R
import com.timkwali.weatherly.core.presentation.components.text.BodyText
import com.timkwali.weatherly.weather.data.local.model.currentweather.CurrentWeatherState
import com.timkwali.weatherly.weather.data.local.model.weatherforecast.WeatherForecastState
import com.timkwali.weatherly.weather.presentation.components.CurrentWeatherSection
import com.timkwali.weatherly.weather.presentation.components.WeatherForecastSection

@Composable
fun WeatherContent(
    currentWeather: CurrentWeatherState?,
    weatherForecast: List<WeatherForecastState>?,
    isLoading: Boolean,
    error: String?,
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier,
    showInitialSearch: Boolean = true,
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = error) {
        error?.let { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() }
    }

    Box(
        modifier = modifier
            .paint(painterResource(id = R.drawable.ic_background), contentScale = ContentScale.FillBounds),
        contentAlignment = Alignment.Center
    ) {
        if(showInitialSearch) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                BodyText(text = stringResource(id = R.string.click_to_search))
                Button(onClick = { onSearchClick() }) { BodyText(text = stringResource(id = R.string.search)) }
            }
        } else {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .paint(
                        painterResource(id = R.drawable.ic_background),
                        contentScale = ContentScale.Fit
                    )
                    .verticalScroll(rememberScrollState())
            ) {
                if(currentWeather != null) {
                    CurrentWeatherSection(
                        currentWeather = currentWeather,
                        onSearchClick = { onSearchClick() }
                    )
                }
                if(weatherForecast != null) {
                    WeatherForecastSection(
                        weatherForecast = weatherForecast,
                        modifier = Modifier
                            .padding(all = 20.dp)
                    )
                }
            }
        }
        if(isLoading) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.secondary)
        }
    }
}