package com.timkwali.weatherly.weather.domain.usecase

import com.timkwali.weatherly.core.utils.NetworkManager
import com.timkwali.weatherly.core.utils.Resource
import com.timkwali.weatherly.core.utils.exceptions.GeneralException
import com.timkwali.weatherly.core.utils.exceptions.NetworkConnectionException
import com.timkwali.weatherly.core.utils.exceptions.handleException
import com.timkwali.weatherly.weather.domain.model.currentweather.CurrentWeatherMapper
import com.timkwali.weatherly.weather.domain.model.currentweather.CurrentWeatherState
import com.timkwali.weatherly.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.delay
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
    ) = flow<Resource<CurrentWeatherState>> {
        try {
            if(!networkManager.isConnected()) {
                emit(Resource.Error(NetworkConnectionException().message))
            }
            emit(Resource.Loading())
            weatherRepository.getCurrentWeather(latitude, longitude)
                .onEmpty { emit(Resource.Error(GeneralException().message)) }
                .collect { response ->
                emit(Resource.Success(CurrentWeatherMapper().mapToDomain(response)))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.handleException().message))
        }
    }
}