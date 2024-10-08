package com.timkwali.weatherly.weather.data.local.model.weatherforecast

import com.timkwali.weatherly.core.utils.DomainMapper
import com.timkwali.weatherly.weather.data.api.model.response.weatherforecast.AverageWeather
import com.timkwali.weatherly.weather.data.api.model.response.weatherforecast.WeatherForecastResponse
import java.text.SimpleDateFormat
import java.util.Locale

class WeatherForecastMapper: DomainMapper<AverageWeather, WeatherForecastState> {
    override suspend fun mapToDomain(entity: AverageWeather): WeatherForecastState {
        return WeatherForecastState(
            date = getDayOfWeekFromDate(entity.dt ?: 0),
            humidity = "${entity.main?.humidity ?: ""}",
            minimumTemperature = "${entity.main?.tempMin?.toInt() ?: ""}",
            maximumTemperature = "${entity.main?.tempMax?.toInt() ?: ""}",
            weatherIcon = "https://openweathermap.org/img/wn/${entity.weather?.get(0)?.icon ?: "01d"}@2x.png"
        )
    }

    private fun getDayOfWeekFromDate(date: Long): String {
        return try {
            SimpleDateFormat("EEEE", Locale.ENGLISH).format(date * 1000)
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }
}