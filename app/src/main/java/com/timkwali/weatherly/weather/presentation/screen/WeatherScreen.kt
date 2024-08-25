package com.timkwali.weatherly.weather.presentation.screen

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.timkwali.weatherly.core.utils.Resource
import com.timkwali.weatherly.weather.data.local.model.currentweather.CurrentWeatherState
import com.timkwali.weatherly.weather.data.local.model.weatherforecast.WeatherForecastState
import com.timkwali.weatherly.weather.presentation.components.SearchBottomSheet
import com.timkwali.weatherly.weather.presentation.viewmodel.WeatherViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen() {
    val weatherViewModel = hiltViewModel<WeatherViewModel>()
    val currentWeather = weatherViewModel.currentWeatherState.collectAsState()
    val weatherForecast = weatherViewModel.weatherForecastState.collectAsState()
    val locations = weatherViewModel.searchLocationState.collectAsState()
    val errorMessage = weatherViewModel.errorMessage.collectAsState(initial = null)
    var isSheetVisible by rememberSaveable { mutableStateOf(false) }
    var searchQuery by rememberSaveable{ mutableStateOf("") }
    val context = LocalContext.current

    LaunchedEffect(key1 = errorMessage.value) {
        errorMessage.value?.let { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() }
    }

    WeatherContent(
        currentWeather = currentWeather.value?.data,
        weatherForecast = weatherForecast.value?.data,
        isLoading = currentWeather.value is Resource.Loading<*> || weatherForecast.value is Resource.Loading,
        showInitialSearch = currentWeather.value?.data == null && currentWeather.value !is Resource.Loading<*>,
        onSearchClick = { isSheetVisible = true },
        modifier = Modifier.fillMaxSize()
    )

    if(isSheetVisible) {
        SearchBottomSheet(
            sheetState = rememberModalBottomSheetState(),
            onDismiss = { isSheetVisible = false },
            searchQuery = searchQuery,
            isLoading = locations.value is Resource.Loading<*>,
            onSearchChange = { newSearchValue ->  searchQuery = newSearchValue },
            searchCity = { sq -> weatherViewModel.searchLocations(sq) },
            locations = locations.value?.data,
            onLocationClick = { latitude, longitude ->
                weatherViewModel.apply {
                    setCoordinates(latitude, longitude)
                    getCurrentWeather()
                    getWeatherForecast()
                }
            }
        )
    }
}

@Composable
@Preview
fun WeatherScreenPreview() {
    val weatherForecastList = listOf(
        WeatherForecastState(0, "Friday", "88", "24", "27", "https://openweathermap.org/img/wn/10d@2x.png"),
        WeatherForecastState(0, "Friday", "88", "24", "27", "https://openweathermap.org/img/wn/10d@2x.png"),
        WeatherForecastState(0, "Friday", "88", "24", "27", "https://openweathermap.org/img/wn/10d@2x.png"),
        WeatherForecastState(0, "Friday", "88", "24", "27", "https://openweathermap.org/img/wn/10d@2x.png"),
        WeatherForecastState(0, "Friday", "88", "24", "27", "https://openweathermap.org/img/wn/10d@2x.png"),
        WeatherForecastState(0, "Friday", "88", "24", "27", "https://openweathermap.org/img/wn/10d@2x.png"),
        WeatherForecastState(0, "Friday", "88", "24", "27", "https://openweathermap.org/img/wn/10d@2x.png"),
    )

    WeatherContent(
        currentWeather = CurrentWeatherState(
            locationName = "Lagos",
            feelsLike = "345",
            temperature = "23",
            humidity = "88",
            windSpeed = "25",
            weatherDescription = "Rainy day",
            weatherIcon = "https://openweathermap.org/img/wn/10d@2x.png"
        ),
        weatherForecast = weatherForecastList,
        isLoading = false,
        showInitialSearch = false,
        onSearchClick = {},
    )
}