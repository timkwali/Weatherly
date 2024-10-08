package com.timkwali.weatherly.weather.domain.repository

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.timkwali.weatherly.weather.data.api.model.response.currentweather.CurrentWeatherResponse
import com.timkwali.weatherly.weather.data.api.model.response.locationsearch.LocationSearchResponse
import com.timkwali.weatherly.weather.data.api.model.response.weatherforecast.WeatherForecastResponse
import com.timkwali.weatherly.weather.data.local.model.currentweather.CurrentWeatherState
import com.timkwali.weatherly.weather.data.local.model.weatherforecast.WeatherForecastState
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getCurrentWeather(latitude: String, longitude: String): CurrentWeatherResponse

    suspend fun getWeatherForecast(latitude: String, longitude: String): WeatherForecastResponse

    suspend fun searchLocations(searchQuery: String): Flow<LocationSearchResponse>


    suspend fun insertCurrentWeather(currentWeather: CurrentWeatherState)

    fun getDbCurrentWeather(): Flow<CurrentWeatherState>

    suspend fun clearCurrentWeather()


    suspend fun insertWeatherForecast(weatherForecast: List<WeatherForecastState>)

    fun getDbWeatherForecast(): Flow<List<WeatherForecastState>>

    suspend fun clearWeatherForecast()
}