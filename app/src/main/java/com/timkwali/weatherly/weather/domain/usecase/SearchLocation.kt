package com.timkwali.weatherly.weather.domain.usecase

import com.timkwali.weatherly.core.utils.NetworkManager
import com.timkwali.weatherly.core.utils.Resource
import com.timkwali.weatherly.core.utils.exceptions.EmptyResultException
import com.timkwali.weatherly.core.utils.exceptions.GeneralException
import com.timkwali.weatherly.core.utils.exceptions.NetworkConnectionException
import com.timkwali.weatherly.core.utils.exceptions.handleException
import com.timkwali.weatherly.weather.domain.model.currentweather.CurrentWeatherMapper
import com.timkwali.weatherly.weather.domain.model.currentweather.CurrentWeatherState
import com.timkwali.weatherly.weather.domain.model.searchlocation.LocationMapper
import com.timkwali.weatherly.weather.domain.model.searchlocation.LocationState
import com.timkwali.weatherly.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEmpty
import javax.inject.Inject

class SearchLocation @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val networkManager: NetworkManager
) {
    suspend operator fun invoke(
        searchQuery: String
    ) = flow<Resource<List<LocationState>>> {
        try {
            if(!networkManager.isConnected()) {
                emit(Resource.Error(NetworkConnectionException().message))
                return@flow
            }
            emit(Resource.Loading())
            weatherRepository.searchLocations(searchQuery)
                .onEmpty { emit(Resource.Error(GeneralException().message)) }
                .collect { response ->
                    val searchResults = response.filterNotNull().map {
                        LocationMapper().mapToDomain(it)
                    }
                    if(searchResults.isEmpty()) {
                        emit(Resource.Error(EmptyResultException().message))
                    } else {
                        emit(Resource.Success(searchResults))
                    }
                }
        } catch (e: Exception) {
            emit(Resource.Error(e.handleException().message))
        }
    }
}