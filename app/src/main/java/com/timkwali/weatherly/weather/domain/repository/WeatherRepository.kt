package com.timkwali.weatherly.weather.domain.repository

import com.timkwali.weatherly.weather.data.api.model.response.currentweather.CurrentWeatherResponse
import com.timkwali.weatherly.weather.data.api.model.response.locationsearch.LocationSearchResponse
import com.timkwali.weatherly.weather.data.api.model.response.weatherforecast.WeatherForecastResponse
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getCurrentWeather(latitude: String, longitude: String): Flow<CurrentWeatherResponse>

    suspend fun getWeatherForecast(latitude: String, longitude: String): Flow<WeatherForecastResponse>

    suspend fun searchLocations(searchQuery: String): Flow<LocationSearchResponse>
}