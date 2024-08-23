package com.timkwali.weatherly.weather.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timkwali.weatherly.core.utils.Resource
import com.timkwali.weatherly.weather.domain.model.currentweather.CurrentWeatherState
import com.timkwali.weatherly.weather.domain.model.searchlocation.LocationState
import com.timkwali.weatherly.weather.domain.model.weatherforecast.WeatherForecastState
import com.timkwali.weatherly.weather.domain.usecase.GetCurrentWeather
import com.timkwali.weatherly.weather.domain.usecase.GetWeatherForecast
import com.timkwali.weatherly.weather.domain.usecase.SearchLocation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getCurrentWeather: GetCurrentWeather,
    private val getWeatherForecast: GetWeatherForecast,
    private val searchLocation: SearchLocation
): ViewModel() {

    private var _currentWeatherState: MutableStateFlow<Resource<CurrentWeatherState>> = MutableStateFlow(Resource.Loading())
    val currentWeatherState: StateFlow<Resource<CurrentWeatherState>> get() = _currentWeatherState.asStateFlow()

    private var _weatherForecastState: MutableStateFlow<Resource<List<WeatherForecastState>>> = MutableStateFlow(Resource.Loading())
    val weatherForecastState: StateFlow<Resource<List<WeatherForecastState>>> get() = _weatherForecastState.asStateFlow()

    private var _searchLocationState: MutableStateFlow<Resource<List<LocationState>>> = MutableStateFlow(Resource.Loading())
    val searchLocationState: StateFlow<Resource<List<LocationState>>> get() = _searchLocationState.asStateFlow()

    fun getCurrentWeather(
        latitude: String,
        longitude: String
    ) = viewModelScope.launch(Dispatchers.IO) {
        getCurrentWeather.invoke(latitude, longitude).collect { currentWeatherState ->
            _currentWeatherState.value = currentWeatherState
        }
    }

    fun getWeatherForecast(
        latitude: String,
        longitude: String
    ) = viewModelScope.launch(Dispatchers.IO) {
        getWeatherForecast.invoke(latitude, longitude).collect { weatherForecast ->
            _weatherForecastState.value = weatherForecast
        }
    }

    fun searchLocations(searchQuery: String) = viewModelScope.launch(Dispatchers.IO) {
        searchLocation.invoke(searchQuery).collect { locations ->
            _searchLocationState.value = locations
        }
    }
}