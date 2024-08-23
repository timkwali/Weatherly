package com.timkwali.weatherly.weather.domain.usecase

import com.timkwali.weatherly.core.utils.NetworkManager
import com.timkwali.weatherly.core.utils.Resource
import com.timkwali.weatherly.core.utils.exceptions.GeneralException
import com.timkwali.weatherly.core.utils.exceptions.NetworkConnectionException
import com.timkwali.weatherly.core.utils.exceptions.handleException
import com.timkwali.weatherly.weather.domain.model.currentweather.CurrentWeatherMapper
import com.timkwali.weatherly.weather.domain.model.weatherforecast.WeatherForecastMapper
import com.timkwali.weatherly.weather.domain.model.weatherforecast.WeatherForecastState
import com.timkwali.weatherly.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEmpty
import javax.inject.Inject

class GetWeatherForecast @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val networkManager: NetworkManager
) {
    suspend operator fun invoke(
        latitude: String,
        longitude: String
    ) = flow<Resource<List<WeatherForecastState>>>{
        try {
            if(!networkManager.isConnected()) {
                emit(Resource.Error(NetworkConnectionException().message))
                return@flow
            }
            weatherRepository.getWeatherForecast(latitude, longitude)
                .onEmpty { emit(Resource.Error(GeneralException().message)) }
                .collect { response ->
                    val weatherForecastList = response.list?.filterNotNull()?.map {
                        WeatherForecastMapper().mapToDomain(it)
                    } ?: emptyList()
                    emit(Resource.Success(weatherForecastList))
                }
        } catch (e: Exception) {
            emit(Resource.Error(e.handleException().message))
        }
    }
}