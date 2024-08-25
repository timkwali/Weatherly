package com.timkwali.weatherly.weather.domain.usecase

import com.timkwali.weatherly.core.utils.NetworkManager
import com.timkwali.weatherly.core.utils.Resource
import com.timkwali.weatherly.core.utils.exceptions.GeneralException
import com.timkwali.weatherly.core.utils.exceptions.NetworkConnectionException
import com.timkwali.weatherly.core.utils.exceptions.handleException
import com.timkwali.weatherly.core.utils.networkBoundResource
import com.timkwali.weatherly.weather.data.local.model.currentweather.CurrentWeatherMapper
import com.timkwali.weatherly.weather.data.local.model.currentweather.CurrentWeatherState
import com.timkwali.weatherly.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEmpty
import javax.inject.Inject

class GetCurrentWeather @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val networkManager: NetworkManager
) {

    suspend operator fun invoke(
        latitude: String,
        longitude: String
    ): Flow<Resource<out CurrentWeatherState>> = networkBoundResource(
        isNetworkConnected = networkManager.isConnected(),
        query = {
            weatherRepository.getDbCurrentWeather()
        },
        fetch = {
            weatherRepository.getCurrentWeather(latitude, longitude)
        },
        saveFetchResult = { currentWeatherResponse ->
            val currentWeatherState = CurrentWeatherMapper().mapToDomain(currentWeatherResponse)
            weatherRepository.insertCurrentWeather(currentWeatherState)
        },
        shouldFetch = { currentWeatherState ->
            latitude != "" && longitude != ""
        }
    )
}