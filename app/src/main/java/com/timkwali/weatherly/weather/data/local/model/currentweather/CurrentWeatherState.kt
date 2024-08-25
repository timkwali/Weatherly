package com.timkwali.weatherly.weather.data.local.model.currentweather

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.timkwali.weatherly.core.utils.Constants.CURRENT_WEATHER_TABLE

@Entity(tableName = CURRENT_WEATHER_TABLE)
data class CurrentWeatherState (
    @PrimaryKey
    val id: Int = 0,
    val locationName: String,
    val feelsLike: String,
    val temperature: String,
    val humidity: String,
    val windSpeed: String,
    val weatherDescription: String,
    val weatherIcon: String
)