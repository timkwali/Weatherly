package com.timkwali.weatherly.weather.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timkwali.weatherly.core.utils.Resource
import com.timkwali.weatherly.weather.data.local.model.currentweather.CurrentWeatherState
import com.timkwali.weatherly.weather.data.local.model.weatherforecast.WeatherForecastState
import com.timkwali.weatherly.weather.domain.model.searchlocation.LocationState
import com.timkwali.weatherly.weather.domain.usecase.GetCurrentWeather
import com.timkwali.weatherly.weather.domain.usecase.GetWeatherForecast
import com.timkwali.weatherly.weather.domain.usecase.SearchLocation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getCurrentWeather: GetCurrentWeather,
    private val getWeatherForecast: GetWeatherForecast,
    private val searchLocation: SearchLocation
): ViewModel() {

    private var currentCoordinates = Coordinates(latitude = "", longitude = "")

    private var _currentWeatherState: MutableStateFlow<Resource<out CurrentWeatherState>?> = MutableStateFlow(null)
    val currentWeatherState: StateFlow<Resource<out CurrentWeatherState>?> get() = _currentWeatherState.asStateFlow()

    private var _weatherForecastState: MutableStateFlow<Resource<out List<WeatherForecastState>>?> = MutableStateFlow(null)
    val weatherForecastState: StateFlow<Resource<out List<WeatherForecastState>>?> get() = _weatherForecastState.asStateFlow()

    private var _searchLocationState: MutableStateFlow<Resource<List<LocationState>>?> = MutableStateFlow(null)
    val searchLocationState: StateFlow<Resource<List<LocationState>>?> get() = _searchLocationState.asStateFlow()

    private var _errorMessage: MutableSharedFlow<String?> = MutableSharedFlow()
    val errorMessage: SharedFlow<String?> get() = _errorMessage.asSharedFlow()


    init {
        getCurrentWeather()
        getWeatherForecast()
    }

    fun setCoordinates(latitude: String, longitude: String) {
        currentCoordinates = currentCoordinates.copy(latitude = latitude, longitude = longitude)
    }

    fun getCurrentWeather() = viewModelScope.launch(Dispatchers.IO) {
        getCurrentWeather.invoke(currentCoordinates.latitude, currentCoordinates.longitude).collect { currentWeatherState ->
            setError(currentWeatherState.message)
            _currentWeatherState.value = currentWeatherState
        }
    }

    fun getWeatherForecast() = viewModelScope.launch(Dispatchers.IO) {
        getWeatherForecast.invoke(currentCoordinates.latitude, currentCoordinates.longitude).collect { weatherForecast ->
            setError(weatherForecast.message)
            _weatherForecastState.value = weatherForecast
        }
    }

    fun searchLocations(searchQuery: String) = viewModelScope.launch(Dispatchers.IO) {
        searchLocation.invoke(searchQuery).collect { locations ->
            setError(locations.message)
            _searchLocationState.value = locations
        }
    }

    private suspend fun setError(message: String?) {
        _errorMessage.emit(message)
    }
}

data class Coordinates(
    val latitude: String,
    val longitude: String
)