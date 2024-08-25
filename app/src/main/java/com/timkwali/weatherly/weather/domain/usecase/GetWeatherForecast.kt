package com.timkwali.weatherly.weather.domain.usecase

import android.util.Log
import com.timkwali.weatherly.core.utils.NetworkManager
import com.timkwali.weatherly.core.utils.Resource
import com.timkwali.weatherly.core.utils.networkBoundResource
import com.timkwali.weatherly.weather.data.local.model.weatherforecast.WeatherForecastMapper
import com.timkwali.weatherly.weather.data.local.model.weatherforecast.WeatherForecastState
import com.timkwali.weatherly.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWeatherForecast @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val networkManager: NetworkManager
) {
    suspend operator fun invoke(
        latitude: String,
        longitude: String
    ): Flow<Resource<out List<WeatherForecastState>>> = networkBoundResource(
        isNetworkConnected = networkManager.isConnected(),
        query = {
            weatherRepository.getDbWeatherForecast()
        },
        fetch = {
            weatherRepository.getWeatherForecast(latitude, longitude)
        },
        saveFetchResult = { weatherForecastResponse ->
            val weatherForecastList = weatherForecastResponse.list?.filterNotNull()?.map {
                WeatherForecastMapper().mapToDomain(it)
            }?.groupBy {
                it.date
            }?.map {
                it.value.first()
            } ?: emptyList()
            weatherRepository.insertWeatherForecast(weatherForecastList)
        },
        shouldFetch = { weatherForecastStateList ->
            latitude != "" && longitude != ""
        }
    )
}