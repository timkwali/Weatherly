package com.timkwali.weatherly.weather.data.repository

import androidx.room.withTransaction
import com.timkwali.weatherly.core.data.local.db.WeatherDatabase
import com.timkwali.weatherly.weather.data.api.WeatherApi
import com.timkwali.weatherly.weather.data.api.model.response.currentweather.CurrentWeatherResponse
import com.timkwali.weatherly.weather.data.api.model.response.locationsearch.LocationSearchResponse
import com.timkwali.weatherly.weather.data.api.model.response.weatherforecast.WeatherForecastResponse
import com.timkwali.weatherly.weather.data.local.model.currentweather.CurrentWeatherState
import com.timkwali.weatherly.weather.data.local.model.weatherforecast.WeatherForecastState
import com.timkwali.weatherly.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val weatherDatabase: WeatherDatabase
): WeatherRepository {
    private val weatherDao = weatherDatabase.weatherDao()

    override suspend fun getCurrentWeather(
        latitude: String,
        longitude: String
    ): CurrentWeatherResponse {
        return weatherApi.getCurrentWeather(latitude, longitude)
    }

    override suspend fun getWeatherForecast(
        latitude: String,
        longitude: String
    ): WeatherForecastResponse {
        return weatherApi.getWeatherForecast(latitude, longitude)
    }

    override suspend fun searchLocations(searchQuery: String): Flow<LocationSearchResponse> {
        return flowOf(weatherApi.searchLocations(searchQuery))
    }

    override suspend fun insertCurrentWeather(currentWeather: CurrentWeatherState) {
        weatherDatabase.withTransaction {
            clearCurrentWeather()
            weatherDao.insertCurrentWeather(currentWeather)
        }
    }

    override fun getDbCurrentWeather(): Flow<CurrentWeatherState> {
        return weatherDao.getDbCurrentWeather()
    }

    override suspend fun clearCurrentWeather() {
        weatherDao.clearCurrentWeather()
    }

    override suspend fun insertWeatherForecast(weatherForecast: List<WeatherForecastState>) {
        weatherDatabase.withTransaction {
            clearWeatherForecast()
            weatherDao.insertWeatherForecast(weatherForecast)
        }
    }

    override fun getDbWeatherForecast(): Flow<List<WeatherForecastState>> {
        return weatherDao.getDbWeatherForecast()
    }

    override suspend fun clearWeatherForecast() {
        weatherDao.clearWeatherForecast()
    }


}