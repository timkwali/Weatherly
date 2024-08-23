package com.timkwali.weatherly.weather.domain.model.currentweather

import com.timkwali.weatherly.core.utils.DomainMapper
import com.timkwali.weatherly.weather.data.api.model.response.currentweather.CurrentWeatherResponse

class CurrentWeatherMapper: DomainMapper<CurrentWeatherResponse, CurrentWeatherState> {
    override suspend fun mapToDomain(entity: CurrentWeatherResponse): CurrentWeatherState {
        return CurrentWeatherState(
            locationName = entity.name ?: "",
            mainWeather = entity.weather?.get(0)?.main ?: "",
            temperature = "${entity.main?.temp ?: "Unknown"}",
            humidity = "${entity.main?.humidity ?: "Unknown"}",
            windSpeed = "${entity.wind?.speed ?: "Unknown"}",
            weatherDescription = entity.weather?.get(0)?.description ?: "",
            weatherIcon = "https://openweathermap.org/img/wn/${entity.weather?.get(0)?.icon ?: "01d"}@2x.png"
        )
    }
}