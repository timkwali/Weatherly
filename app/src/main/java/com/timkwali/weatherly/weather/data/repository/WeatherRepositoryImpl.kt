package com.timkwali.weatherly.weather.data.repository

import android.util.Log
import com.timkwali.weatherly.core.utils.exceptions.NetworkConnectionException
import com.timkwali.weatherly.core.utils.NetworkManager
import com.timkwali.weatherly.core.utils.exceptions.handleException
import com.timkwali.weatherly.weather.data.api.WeatherApi
import com.timkwali.weatherly.weather.data.api.model.response.currentweather.CurrentWeatherResponse
import com.timkwali.weatherly.weather.data.api.model.response.locationsearch.LocationSearchResponse
import com.timkwali.weatherly.weather.data.api.model.response.weatherforecast.WeatherForecastResponse
import com.timkwali.weatherly.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi
): WeatherRepository {

    override suspend fun getCurrentWeather(
        latitude: String,
        longitude: String
    ): Flow<CurrentWeatherResponse> {
        return flowOf(weatherApi.getCurrentWeather(latitude, longitude))
    }

    override suspend fun getWeatherForecast(
        latitude: String,
        longitude: String
    ): Flow<WeatherForecastResponse> {
        return flowOf(weatherApi.getWeatherForecast(latitude, longitude))
    }

    override suspend fun searchLocations(searchQuery: String): Flow<LocationSearchResponse> {
        return flowOf(weatherApi.searchLocations(searchQuery))
    }

}